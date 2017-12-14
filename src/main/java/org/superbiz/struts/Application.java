package org.superbiz.struts;


import com.opensymphony.module.sitemesh.filter.PageFilter;
import org.apache.struts2.dispatcher.ActionContextCleanUp;
import org.apache.struts2.dispatcher.FilterDispatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

@SpringBootApplication
public class Application {


    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public FilterRegistrationBean strutscleanupFilter() {
        return createFilterRegistration(new ActionContextCleanUp(),1);
    }

    @Bean
    public FilterRegistrationBean sitemeshFilter() {
        return createFilterRegistration(new PageFilter(),2);
    }

    @Bean
    public FilterRegistrationBean struts2Filter() {
        return createFilterRegistration(new FilterDispatcher(),0);
    }


    private FilterRegistrationBean createFilterRegistration(Filter filter, int order) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.addUrlPatterns("/*");
        registration.setOrder(order);

        //registration.addInitParameter("a", "alpha");
        return registration;
    }
}
