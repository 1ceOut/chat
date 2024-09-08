<<<<<<< HEAD
//package com.example.chat.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:8080")
//                .allowedMethods("*")
//                .allowedHeaders("*")
//                .allowCredentials(true);
//    }
//}
=======
package com.example.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// @Configuration
// public class WebConfig implements WebMvcConfigurer {

//     @Override
//     public void addCorsMappings(CorsRegistry registry) {
//         registry.addMapping("/**")
//                 .allowedOrigins("https://api.icebuckwheat.kro.kr","http://localhost:8080")
//                 .allowedMethods("*")
//                 .allowedHeaders("*")
//                 .allowCredentials(true);
        
//         // WebSocket에 대한 CORS 설정 추가
//         registry.addMapping("/ws/**")
//                 .allowedOrigins("https://api.icebuckwheat.kro.kr", "http://localhost:8080")
//                 .allowedMethods("GET", "POST")
//                 .allowCredentials(true);
//     }
// }
>>>>>>> dd38870a851c0e8a6fa596ca32914fa0a404e306
