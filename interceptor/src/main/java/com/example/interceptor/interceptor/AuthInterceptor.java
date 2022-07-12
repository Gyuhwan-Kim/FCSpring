package com.example.interceptor.interceptor;

import com.example.interceptor.annotation.Auth;
import com.example.interceptor.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        log.info("request url : {}", url);

        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("has annotation : {}", hasAnnotation);

        URI uri = UriComponentsBuilder.fromUriString(url)
                .query(request.getQueryString())
                .build().toUri();

        // 나의 server는 모두 public으로 동작하는데
        // 단, Auth 권한을 가진 요청(session, cookie) 에 대해서는
        if(hasAnnotation){
            // 권한 check
            String query = uri.getQuery();
            log.info("query : {}", query);
            if(query.equals("name=lueble")){
                return true;
            }
            throw new AuthException();
        }

        return true;
    }

    private boolean checkAnnotation(Object handler, Class clazz){
        // resource(html, javascript)
        if(handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        // annotation check
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        if(null != handlerMethod.getMethodAnnotation(clazz)
                || null != handlerMethod.getBeanType().getAnnotation(clazz)){
            // Auth annotation이 있을 때는 true
            return true;
        }

        return false;
    }
}
