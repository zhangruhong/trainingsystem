package com.synnex.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.synnex.model.User;

/**
 * 
 * @author hiramh 检查是否登录的拦截
 */
public class AuthInterceptor implements HandlerInterceptor {
	private static final String[] IGNORE_URI = { "/login.html", "/trainingsystem/login" };
	private Logger log = LoggerFactory.getLogger(AuthInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean flag = false;
		String url = request.getRequestURL().toString();
		log.info("请求的url" + url);
		System.out.println(123);
		for (String s : IGNORE_URI) {
			if (url.contains(s)) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			User user = (User) request.getSession().getAttribute("USER_IN_SESSION");
			if (user != null)
				flag = true;
		}
		return flag;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		response.sendRedirect("/login.html");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub
	}
}
