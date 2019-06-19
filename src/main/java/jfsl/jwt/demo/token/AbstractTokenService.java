package jfsl.jwt.demo.token;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jfsl.jwt.demo.model.JwtModel;

/**
 * <pre>
 *  抽象token服务类
 * </pre>
 *
 * @author JFSL
 * @version v1.0.0
 * @date 2019-06-19 19:06
 */
public abstract class AbstractTokenService extends HttpTokenConverter implements TokenService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String getUserId() {
        String token = getToken();
        if (token == null) {
            return null;
        }
        JwtModel jwtModel = token2jwtModel(token);
        if (jwtModel != null && isValid(jwtModel)) {
            return jwtModel.getUserId();
        }
        return null;
    }

    @Override
    public boolean check(String token) {
        return Optional.ofNullable(getToken()).map(t -> token2jwtModel(t)).map(jwt -> isValid(jwt)).orElse(false);
    }

    @Override
    public String generate(String userId) {
        String token = create(userId);
        if (token != null) {
            setToken(token);
        } else {
            logger.warn("生成token失败,userId={}", userId);
        }
        return token;
    }

    /**
     * <pre>
     * 是否有效
     * </pre>
     *
     * @param jwtModel token解析后的model信息
     * @return true || false
     */
    protected abstract boolean isValid(JwtModel jwtModel);
}

