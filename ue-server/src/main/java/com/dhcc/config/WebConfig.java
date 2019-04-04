package com.dhcc.config;

import com.dhcc.servlet.UEServerServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig  {

    @Value("${spring.ueditor.configPath}")
    private String ueditorConfigPath;
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new UEServerServlet(), "/ueditor");
        servletRegistrationBean.addInitParameter("ueditorConfigPath",ueditorConfigPath);
        return servletRegistrationBean;
    }






}
