package cl.tcadv.urlshortener.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cl.tcadv.urlshortener.service.UrlMetricService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UrlMetricInterceptor implements HandlerInterceptor {

	@Autowired
	private UrlMetricService urlMetricService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
			ModelAndView model) {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) {
		if (response.getStatus() == HttpStatus.FOUND.value()) {
			long urlId = Long
					.parseLong(request.getServletPath().substring(request.getServletPath().lastIndexOf("/") + 1));
			this.urlMetricService.saveMetric(urlId, request.getHeader("user-agent"), request.getRemoteAddr());
		}
	}
}
