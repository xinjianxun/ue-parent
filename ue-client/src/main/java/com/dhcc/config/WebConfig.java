/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.dhcc.config;

import com.dhcc.servlet.UEServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc配置
 *
 * @author Mark sunlightcs@gmail.com
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Value("${ueditor.server.remoteUrl}")
    private String  ueditorServerRemoteUrl;




    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new UEServlet(), "/ueditor");
        servletRegistrationBean.addInitParameter("ueditorServerRemoteUrl",ueditorServerRemoteUrl);
        return servletRegistrationBean;
    }

}