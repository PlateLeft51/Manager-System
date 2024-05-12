package com.manager.systemmanger.intercepter;

import com.manager.systemmanger.User.req.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RoleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
               HttpServletResponse response, Object handler) throws Exception {
            String encodedHeader = request.getHeader("X-Role-Info");
            if(encodedHeader != null){
                User user = User.decode(encodedHeader);
                request.setAttribute("user", user);
            }

            return true;
    }
}
