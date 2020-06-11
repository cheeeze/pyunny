package com.ssafy.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author 이채은 사진(정적데이터) 저장
 * 
 */

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    private final String uploadImagesPath;

    public WebMvcConfiguration(@Value("${custom.path.upload-images}") String uploadImagesPath) {
        this.uploadImagesPath = uploadImagesPath;
        System.out.println(this.uploadImagesPath);
    }

    // @Value("${resource.uploads.root}")
    // private String uploadsRoot;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/upload/**").addResourceLocations("file:///" + uploadImagesPath)
                .setCachePeriod(31536000);

        // registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

    }

}
