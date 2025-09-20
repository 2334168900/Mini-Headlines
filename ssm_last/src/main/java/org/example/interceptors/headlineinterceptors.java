package org.example.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.utils.JwtHelper;
import org.example.utils.Result;
import org.example.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class headlineinterceptors implements HandlerInterceptor {


    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        boolean expiration = jwtHelper.isExpiration(token);
        if(!expiration) {
            return true;
        }
        Result build = Result.build(null, ResultCodeEnum.NOTLOGIN);

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(build);
        response.getWriter().print(s);

        return false;
    }
}
