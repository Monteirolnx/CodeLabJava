package com.monteiro.healthcheckservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {

    private static final Logger logger = LoggerFactory.getLogger(WebServiceConfig.class);

    public WebServiceConfig() {
        System.out.println("WebServiceConfig is initialized");
    }

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        logger.info("MessageDispatcherServlet registered at /ws/*");
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "healthcheck")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema healthcheckSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("HealthCheckPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://monteiro.com/healthcheck");
        wsdl11Definition.setSchema(healthcheckSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema healthcheckSchema() {
        logger.info("Loading healthcheck.xsd schema");
        return new SimpleXsdSchema(new ClassPathResource("healthcheck.xsd"));
    }
}
