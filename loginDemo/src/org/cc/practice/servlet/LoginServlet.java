package org.cc.practice.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cc.practice.entity.User;
import org.cc.practice.service.UserService;
import org.cc.practice.service.impl.UserServiceImpl;

public class LoginServlet extends HttpServlet {

	private UserService userService;
	
	public LoginServlet() {
		userService=new UserServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req,resp);
	}
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		String username=req.getParameter("username");
		String passwd=req.getParameter("password");
		
		boolean errorHappened=false;
		if(username==null || "".equals(username.trim())){
			req.setAttribute("usernameError","用户名不能为空");
			errorHappened=true;
		}
		if(passwd==null || "".equals(passwd.trim())){
			req.setAttribute("passwdError","密码不能为空");
			errorHappened=true;
		}
		if(errorHappened){
			req.getRequestDispatcher("login.jsp").forward(req,resp);
			return;
		}
		
		User user=new User();
		user.setUsername(username);
		user.setPasswd(passwd);
		
		user=userService.login(user);
		
		if(user==null){
			req.setAttribute("loginError","用户名或密码错误");
			req.getRequestDispatcher("login.jsp").forward(req,resp);
			return;
		}else{
			req.getSession().setAttribute("user",user);
//			req.getRequestDispatcher("index.jsp").forward(req,resp);
			resp.sendRedirect("showMain.jsp");
			return ;
		}
		
	}
	
}
