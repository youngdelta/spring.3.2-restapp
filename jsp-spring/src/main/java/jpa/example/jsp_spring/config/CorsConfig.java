package jpa.example.jsp_spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
//    	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>CORS   ##############################################################");
        registry.addMapping("/**") // 모든 경로에 대해 CORS 적용
//                .allowedOrigins("http://example.com") // 특정 도메인 허용
                .allowedOrigins("*") // 특정 도메인 허용
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
                .allowedHeaders("*") // 모든 헤더 허용
//                .allowCredentials(true) // 쿠키 인증 요청 허용
                ;
    }
}