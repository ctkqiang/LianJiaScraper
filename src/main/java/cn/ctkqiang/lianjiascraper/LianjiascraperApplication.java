package cn.ctkqiang.lianjiascraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * 链家房源数据爬虫系统主应用程序类
 * 
 * 该类是整个应用程序的入口点，负责配置和启动Spring Boot应用。
 * 实现了WebMvcConfigurer接口以自定义Web MVC配置。
 * 
 * 主要功能：
 * 1. 配置Thymeleaf模板引擎
 * 2. 设置视图解析器
 * 3. 配置Web服务器
 * 4. 提供应用程序启动日志
 * 
 * @author 钟智强
 * @version 1.0
 */
@SpringBootApplication
public class LianjiascraperApplication implements WebMvcConfigurer {
    /** 日志记录器实例 */
    private static final Logger logger = LoggerFactory.getLogger(LianjiascraperApplication.class);

    /**
     * 应用程序主入口方法
     * 
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(LianjiascraperApplication.class);
        app.run(args);
    }

    /**
     * 配置Thymeleaf模板解析器
     * 
     * @return ClassLoaderTemplateResolver Thymeleaf模板解析器实例
     */
    @Bean
    public ClassLoaderTemplateResolver templateResolver() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML");
        resolver.setCacheable(false);
        return resolver;
    }

    /**
     * 配置Thymeleaf模板引擎
     * 
     * @return SpringTemplateEngine 模板引擎实例
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        return engine;
    }

    /**
     * 配置Thymeleaf视图解析器
     * 
     * @return ThymeleafViewResolver 视图解析器实例
     */
    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    /**
     * 注册视图解析器到Spring MVC
     * 
     * @param registry 视图解析器注册表
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(viewResolver());
    }

    /**
     * 配置Web服务器
     * 设置端口号和上下文路径
     * 
     * @return WebServerFactoryCustomizer Web服务器定制器
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
        return factory -> {
            factory.setPort(8080);
            factory.setContextPath("");
        };
    }

    /**
     * 应用程序启动后的命令行运行器
     * 输出应用程序启动信息和可用的API端点
     * 
     * @return CommandLineRunner 命令行运行器实例
     */
    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            logger.info("应用程序启动成功！");
            logger.info("网页界面: http://localhost:8080/");
            logger.info("API接口列表:");
            logger.info("- 测试接口: http://localhost:8080/api/test");
            logger.info("- 房源列表: http://localhost:8080/api/house");
            logger.info("- JSON数据: http://localhost:8080/api/house/json/{province}");
            logger.info("- 数据可视化: http://localhost:8080/api/show");
            logger.info("- 导出CSV: http://localhost:8080/api/house/export/csv");
            logger.info("- 导出Excel: http://localhost:8080/api/house/export/excel");
        };
    }
}
