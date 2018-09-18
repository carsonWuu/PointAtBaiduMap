package net.rs.servlet.getdata;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;


import com.tonetime.commons.database.helper.DbHelper;
import com.tonetime.commons.database.helper.JdbcCallback;

import net.rs.bean.Position.mapPosition;
import net.rs.bean.user.User;
import net.rs.dao.impl.UserDaoImpl;
import net.rs.init.mysql.DataSourceBuilder;

public class GetData extends HttpServlet {

	/*
	 * 
	 *  
	 *  */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.print("接收:");
		request.setCharacterEncoding("utf-8");  
        //response.setContentType("text/html;charset=utf-8");  
		String param=request.getParameter("data"); 
		//Object center=request.getParameter("center");
		System.out.println(param);
		Gson json=new Gson();
		mapPosition mp=json.fromJson(param,mapPosition.class);  
        
   
		List<Map<String, Object>> list=null;
		try {
			list = UserDaoImpl.outputData(mp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	    PrintWriter out = response.getWriter();
	   
	        
	    Gson gson = new Gson();
	    out.println(gson.toJson(list));
	    out.flush();
		out.close();    
	   
	}

	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		doGet(request,response);
			

	}
	
	
		

}
