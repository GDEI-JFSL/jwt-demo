package jfsl.jwt.demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jfsl.jwt.demo.dto.ResponeDTO;
import jfsl.jwt.demo.token.TokenService;

/**
 * <pre>
 *  测试
 *
 *  eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9
 *  .eyJzaWduIjoiM2Q3NDkzYzA0YTQ4ZGMwMjdiNmM3Y2JiYWYyNTc1MjQiLCJ1c2VySWQiOiIxMzIzMjcwNzU1MSIsInRpbWVzdGFtcCI6MTU2MDk0NTkzNzIyNX0
 *  .yL0H395k6bNnG7pJ6RNjwVxF4omM-ceQLwI46Yf2czI
 * </pre>
 *
 * @author JFSL
 * @version v1.0.0
 * @date 2019-06-19 18:33
 */
@RestController
@RequestMapping(value = "/jwt_demo/test", method = RequestMethod.POST)
public class TestController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/get")
    public ResponeDTO get(String param) {
        logger.warn("请求参数:{}", param);
        String token = tokenService.generate(param);
        logger.info("token:{}", token);
        String userId = tokenService.getUserId();
        logger.info("header -> userId:{}", userId);
        return new ResponeDTO(token);
    }
}

