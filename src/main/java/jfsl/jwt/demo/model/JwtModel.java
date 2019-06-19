package jfsl.jwt.demo.model;

/**
 * <pre>
 * Jwt数据类
 * </pre>
 *
 * @author JFSL
 * @version v1.0.0
 * @date 2019-06-19 18:55
 */
public class JwtModel {

    /**
     * 用户id
     */
    private String userId;
    /**
     * 校验字符串
     */
    private String sign;
    /**
     * 时间戳
     */
    private Long timestamp;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}

