package com.monteiro.webservicesproxypoc.config;

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

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "webservicesproxypoc")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema combinedSchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("WebServicesProxyPocPort");
        definition.setLocationUri("/ws");
        definition.setTargetNamespace("http://www.monteiro.com/webservicesproxypoc");
        definition.setSchema(combinedSchema);
        return definition;
    }

    @Bean
    public XsdSchema combinedSchema() {
        return new SimpleXsdSchema(new ClassPathResource("webservicesproxypoc.xsd"));
    }
}
