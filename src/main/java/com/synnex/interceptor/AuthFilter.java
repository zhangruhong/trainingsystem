package com.synnex.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.synnex.model.User;

/**
 * Servlet Filter implementation class AuthFilter
 */
public class AuthFilter implements Filter {

	private Logger log = LoggerFactory.getLogger(AuthInterceptor.class);

	/**
	 * Default constructor.
	 */
	public AuthFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 获得路径
		String path = req.getRequestURI().substring(req.getContextPath().length());
		log.info("请求的url" + path);
		if (path.equals("/login.html") || path.equals("/login")) {
			// 这些页面不需要判断登录直接放行
			chain.doFilter(request, response);
			return;
		}
		User user = (User) req.getSession().getAttribute("USER_IN_SESSION");
		// 判断如果没有取到用户信息,就跳转到登陆页面
		if (user == null) {
			// 跳转到登陆页面
			res.sendRedirect("/trainingsystem/login.html");
		} else {
			// 已经登陆,继续此次请求
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
