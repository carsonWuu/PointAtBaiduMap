package org.cc.practice.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.cc.practice.entity.User;

public class LoginFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		//鍦ㄨ繃婊ゅ櫒涓鏌ユ槸鍚﹀凡缁忕櫥褰�
		
		HttpServletRequest req=(HttpServletRequest) request;
		
		User user=(User) req.getSession().getAttribute("user");
		
		if(user==null && !req.getRequestURI().endsWith("login.jsp") && !req.getRequestURI().endsWith("loginServlet")){
			req.getRequestDispatcher("login.jsp").forward(request,response);
		}else{
			chain.doFilter(request,response);
		}
		
	}

	@Override
	public void destroy() {

	}

}
