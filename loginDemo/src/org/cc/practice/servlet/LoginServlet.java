package org.cc.practice.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cc.practice.dao.impl.UserDaoImpl;
import org.cc.practice.entity.User;
import org.cc.practice.service.UserService;


public class LoginServlet extends HttpServlet {

	private UserDaoImpl userDaoImpl;
	
	public LoginServlet() {
		userDaoImpl=new UserDaoImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			process(req,resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			process(req,resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws Exception{

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
		
		
		
		List<Map<String,Object>> list=userDaoImpl.checkLogin(username, passwd);
		
		if(list.size()==0){//用户名密码错误。
			req.setAttribute("loginError","用户名或密码错误");
			req.getRequestDispatcher("login.jsp").forward(req,resp);
			return;
		}else{//登录成功进行跳转至地图展示页
			User user=new User();
			user.setUsername(username);
			user.setPasswd(passwd);
			req.getSession().setAttribute("user",user);
//			req.getRequestDispatcher("index.jsp").forward(req,resp);
			resp.sendRedirect("showMain.jsp");
			return ;
		}
		
	}
	
}
