package com.prueba_backend.config;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ApplicationStartupConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(ApplicationStartupConfig.class);

    @EventListener
    public void onApplicationStarted(ApplicationStartedEvent event) {
        String port = event.getApplicationContext().getEnvironment().getProperty("server.port", "8080");
        String contextPath = event.getApplicationContext().getEnvironment().getProperty("server.servlet.context-path", "/");
        
        logger.info("========================================================");
        logger.info("🚀 Application is running! Access URLs:");
        logger.info("📚 Swagger UI: http://localhost:{}{}swagger-ui.html", port, contextPath);
        logger.info("📝 API Docs: http://localhost:{}{}api-docs", port, contextPath);
        logger.info("💾 H2 Console: http://localhost:{}{}h2-console", port, contextPath);
        logger.info("📊 JaCoCo Coverage: file://{}/target/site/jacoco/index.html", System.getProperty("user.dir"));
        logger.info("========================================================");
    }
} 