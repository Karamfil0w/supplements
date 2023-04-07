package com.example.supplements.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class AppConfig implements WebMvcConfigurer {

    private final LoggingInterceptor loggingInterceptor;
    private final MaintenanceInterceptor maintenanceInterceptor;

    public AppConfig(LoggingInterceptor loggingInterceptor, MaintenanceInterceptor maintenanceInterceptor) {
        this.loggingInterceptor = loggingInterceptor;
        this.maintenanceInterceptor = maintenanceInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor);
        registry.addInterceptor(maintenanceInterceptor);
    }
}
