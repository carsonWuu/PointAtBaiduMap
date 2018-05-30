package org.cc.practice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.cc.practice.dao.UserDao;
import org.cc.practice.entity.User;
import org.cc.practice.util.DbUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public User login(User user) {
		
		String sql="SELECT id,username,password FROM user_login WHERE username=? and password=?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		User u=null;
		
		try {
			conn=DbUtil.getConnection();
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1,user.getUsername());
			pstmt.setString(2,user.getPasswd());
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				u=new User();
				u.setId(rs.getString("id"));
				u.setUsername(rs.getString("username"));
				u.setPasswd(rs.getString("password"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
		
		return u;
	}

	@Override
	public User findUserById(String id) {
		
		String sql="SELECT id,username,password FROM user_login WHERE id=?";
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		User u=null;
		
		try {
			conn=DbUtil.getConnection();
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1,id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				u=new User();
				u.setId(rs.getString("id"));
				u.setUsername(rs.getString("username"));
				u.setPasswd(rs.getString("password"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
		
		return null;
	}

}
