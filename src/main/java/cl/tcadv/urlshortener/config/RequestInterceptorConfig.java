package cl.tcadv.urlshortener.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cl.tcadv.urlshortener.interceptor.UrlMetricInterceptor;

@Configuration()
public class RequestInterceptorConfig implements WebMvcConfigurer {
	@Autowired
	private UrlMetricInterceptor urlMetricInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(urlMetricInterceptor).addPathPatterns("/url/expand/**");
	}
}
