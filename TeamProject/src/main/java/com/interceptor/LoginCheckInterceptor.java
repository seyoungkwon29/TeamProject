package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/*
 * 회원전용처리를 위한 Interceptor extends HandlerInterceptorAdapter
 * Interceptor 주소처리는 servlet-context.xml에서 (/loginCheck/**)
 */
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
	
	private final static Logger log = LoggerFactory.getLogger(LoginCheckInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug("RequestURI={}",request.getRequestURI());
		HttpSession session = request.getSession();
		
		if (session.getAttribute("login") == null) {

			String contextPath = request.getContextPath();
			String redirectPath = "/login";
			String redirectURI = contextPath + redirectPath;
			log.debug("RedirectURI={}", redirectURI);
			response.sendRedirect(redirectURI);
			
			return false;
		}	
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	
}
