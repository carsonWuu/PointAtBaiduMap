package net.sp.programme.data;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sp.programme.dao.impl.UserDaoImpl;
import net.sp.programme.entity.User;






import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.tonetime.commons.database.helper.DbHelper;
import com.tonetime.commons.database.helper.JdbcCallback;

public class setMap extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	class Out{
		int id;
		String str;
		public Out(int id,String str){
			this.id=id;
			this.str=str;
		}
		public void setId(int id){
			this.id=id;
		}
		public void setStr(String str){
			this.str=str;
		}
		public int getId(){
			return id;
		}
		public String getStr(){
			return str;
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			request.setCharacterEncoding("utf-8");  
	        //response.setContentType("text/html;charset=utf-8");  
		 
	   
			List<Map<String, Object>> list=null;
			try {
				list = UserDaoImpl.initOutputData();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		    PrintWriter out = response.getWriter();
		   
		        
		    Gson gson = new Gson();
		    out.println(gson.toJson(list));
		        
		    System.out.println("数据发送完毕！");

	}
	
	
		

}
