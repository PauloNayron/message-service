package com.lb.messageservice;

import com.lb.messageservice.app.config.CorrelationHeaderFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MessageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageServiceApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<CorrelationHeaderFilter> correlationHeaderFilter() {
		var filterRegistrationBean = new FilterRegistrationBean<CorrelationHeaderFilter>();
		filterRegistrationBean.setFilter(new CorrelationHeaderFilter());
		filterRegistrationBean.setUrlPatterns(List.of("/*"));
		return filterRegistrationBean;
	}
}
