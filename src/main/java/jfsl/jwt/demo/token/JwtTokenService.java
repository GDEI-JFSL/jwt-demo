package jfsl.jwt.demo.token;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import jfsl.jwt.demo.model.JwtModel;

/**
 * <pre>
 *  JwtToken服务类
 * </pre>
 *
 * @author JFSL
 * @version v1.0.0
 * @date 2019-06-19 19:23
 */
@Component
public class JwtTokenService extends AbstractTokenService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String SALT = "jfsl";
    private static final String KEY = "13232***551";

    @Override
    protected boolean isValid(JwtModel jwtModel) {
        if (jwtModel == null) {
            return false;
        }
        Long timestamp = jwtModel.getTimestamp();
        String userId = jwtModel.getUserId();
        String sign = jwtModel.getSign();
        if (sign != null) {
            String genSign = getSign(userId, timestamp);
            return sign.equals(genSign);
        }
        return false;
    }

    @Override
    public JwtModel token2jwtModel(String token) {
        JwtModel jwtModel = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            jwtModel = new JwtModel();
            jwtModel.setUserId(jwt.getClaim("userId").asString());
            jwtModel.setSign(jwt.getClaim("sign").asString());
            jwtModel.setTimestamp(jwt.getClaim("timestamp").asLong());
        } catch (Exception ex) {
            logger.error("token转换成model失败,token={},ex=", token, ex);
        }
        return jwtModel;
    }

    @Override
    public String create(String userId) {
        long timestamp = getCurrentMillis();
        String sign = getSign(userId, timestamp);
        String stoken = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            stoken = JWT.create().withClaim("userId", userId)
                    .withClaim("sign", sign)
                    .withClaim("timestamp", timestamp)
                    .sign(algorithm);
        } catch (Exception ex) {
            logger.error("生成token失败,userId={},ex=", userId, ex);
            throw new RuntimeException("生成token失败");
        }
        return stoken;
    }

    // ---------------private---------------

    /**
     * <pre>
     * 获取签名
     * </pre>
     *
     * @param userId 用户id
     * @param timestamp 时间戳
     * @return 签名
     */
    private String getSign(String userId, long timestamp) {
        StringBuffer sb = new StringBuffer().append(userId).append(SALT).append(timestamp);
        String sid = "";
        try {
            sid = DigestUtils.md5Hex(sb.toString());
        } catch (Exception e) {
            logger.error("生成签名失败,userId={},timestamp={}", userId, timestamp);
            throw new RuntimeException("生成签名失败");
        }
        return sid;
    }

    /**
     * <pre>
     * 获取当前时间戳
     * </pre>
     *
     * @return 时间戳
     */
    private long getCurrentMillis() {
        return System.currentTimeMillis();
    }
}

