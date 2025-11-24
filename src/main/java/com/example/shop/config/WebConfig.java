package com.example.shop.config;

import com.example.shop.aop.UnAuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Enumeration;

@Configuration
public class WebConfig {
    public WebMvcConfigurer corsConfig(){
        return new WebMvcConfigurer() {
            //cors origin 설정
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/v3/**");//스웨거 같은 주소도 받아줄 수 있다.
                registry.addMapping("/api/**")
                        .allowedOriginPatterns("*")
                        .allowedMethods("GET", "POST", "PUT", "FETCH", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }


            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                WebMvcConfigurer.super.addInterceptors(registry);
                registry.addInterceptor(new HandlerInterceptor() {
                    //로깅
                    @Override
                    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                        System.out.println("preHandle");
                        System.out.println(request.getContextPath());
                        System.out.println(request.getHeader(HttpHeaders.AUTHORIZATION));
                        return HandlerInterceptor.super.preHandle(request, response, handler);
                    }

                    //인증실패 예외 설정
           //        @Override
           //        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
           //            request.getContextPath();
           //            Enumeration<String> aaa = request.getHeaders(HttpHeaders.AUTHORIZATION);
           //            String token = aaa.nextElement().replaceAll("Bearer ", "");
           //            new UnAuthException("unauthorize user");
           //            return true;
           //        }

                    @Override
                    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
                        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
                    }

                });
            }
        };
    }
}
