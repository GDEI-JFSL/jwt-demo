package jfsl.jwt.demo.token;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * <pre>
 *  http token
 * </pre>
 *
 * @author JFSL
 * @version v1.0.0
 * @date 2019-06-19 18:44
 */
public class HttpTokenConverter implements TokenConverter {

    @Override
    public String getToken() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader(HEADER_TOKEN);
    }

    @Override
    public void setToken(String token) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (sra != null) {
            sra.getResponse().setHeader(HEADER_TOKEN, token);
        }
    }
}

