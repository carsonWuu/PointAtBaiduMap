package net.sp.programme.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sp.programme.entity.User;

public class LoginFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		/*
		 * 转发未登录的用户回到登录界面login.jsp
		 */
		
		HttpServletRequest req=(HttpServletRequest) request;
		
//		HttpServletResponse res=(HttpServletResponse) response;
		
		User user=(User) req.getSession().getAttribute("user");
		
		if(user==null && req.getRequestURI().endsWith("showMain.jsp") ){
		
			req.getRequestDispatcher("login.jsp").forward(request,response);
			
//		if(user==null&& !req.getRequestURI().endsWith("login.jsp")&& !req.getRequestURI().endsWith("showMain.jsp")&& !req.getRequestURI().endsWith("loginServlet")){
//			res.sendRedirect("login.jsp");
		}else{
			chain.doFilter(request,response);
		}
		
	}

	@Override
	public void destroy() {

	}

}
