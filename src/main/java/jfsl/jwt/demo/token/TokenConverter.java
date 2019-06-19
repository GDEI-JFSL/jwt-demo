package jfsl.jwt.demo.token;

/**
 * <pre>
 * token转换器
 * </pre>
 *
 * @author JFSL
 * @version v1.0.0
 * @date 2019-06-19 18:43
 */
public interface TokenConverter {
    /**
     * 请求头
     */
    String HEADER_TOKEN = "token";

    /**
     * <pre>
     * 获取token
     * </pre>
     *
     * @return token字符串
     */
    String getToken();

    /**
     * <pre>
     * 设置token
     * </pre>
     *
     * @param token token字符串
     */
    void setToken(String token);
}

