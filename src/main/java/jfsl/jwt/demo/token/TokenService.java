package jfsl.jwt.demo.token;

import jfsl.jwt.demo.model.JwtModel;

/**
 * <pre>
 *
 * </pre>
 *
 * @author JFSL
 * @version v1.0.0
 * @date 2019-06-19 18:52
 */
public interface TokenService {

    /**
     * <pre>
     * 从请求头中获取userId
     * </pre>
     *
     * @return userId
     */
    String getUserId();

    /**
     * 根据身份令牌字符串，解析用户身份校验信息
     *
     * @param stoken 会话令牌
     * @return AuthSession用户身份会话信息
     */
    /**
     * <pre>
     * token转换成jwtModel
     * </pre>
     *
     * @param token token字符串
     * @return jwt数据类
     */
    JwtModel token2jwtModel(String token);

    /**
     * <pre>
     * 校验token字符串是否正确
     * </pre>
     *
     * @param token token字符串
     * @return true || false
     */
    boolean check(String token);

    /**
     * <pre>
     * 生成token字符串并设置到请求头中
     * </pre>
     *
     * @param userId 用户id
     * @return token字符串
     */
    String generate(String userId);

    /**
     * <pre>
     * 创建token字符串
     * </pre>
     *
     * @param userId 用户id
     * @return token
     */
    String create(String userId);

}

