//package com.ssafy.happyhouse.model.mapper;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.ssafy.model.Member;
//import com.ssafy.util.DBUtil;
//
//
//public class MemberDAOImpl implements MemberDAO {
//
//	@Override
//	public void insertMember(Member member) {
//		System.out.println("회원등록DAO");
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = DBUtil.getConnection();
//			StringBuffer sql = new StringBuffer();
//			sql.append("insert into members values (?,?,?,?,?); ");
//
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, member.getUserId());
//			pstmt.setString(2, member.getUserPwd());
//			pstmt.setString(3, member.getUserName());
//			pstmt.setString(4, member.getUserAddress());
//			pstmt.setString(5, member.getUserPhone());
//
//			int res = pstmt.executeUpdate();
//			System.out.println(res);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBUtil.close(pstmt);
//			DBUtil.close(conn);
//		}
//		
//	}
//
//	@Override
//	public boolean login(String id, String password) {
//		System.out.println("로그인DAO실행");
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			conn = DBUtil.getConnection();
//			StringBuffer sql = new StringBuffer();
//			sql.append("select * ");
//			sql.append(" from members ");
//			sql.append(" where userId = ? and userPwd = ? ");
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, id);
//			pstmt.setString(2, password);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				return true;
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBUtil.close(pstmt);
//			DBUtil.close(conn);
//		}
//		return false;
//	}
//
//	@Override
//	public void update(Member member) {
//		System.out.println("업데이트메소드 실행");
//		System.out.println(member.getUserId());
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		
//		try {
//			conn = DBUtil.getConnection();
//			StringBuffer sql = new StringBuffer();
//			
//			sql.append("update members set userPwd = ?, ")
//			.append(" userName =  ?, ")
//			.append(" userAddress = ?, ")
//			.append(" userPhone = ?")
//			.append(" where userId = ?;");
//			
//			pstmt = conn.prepareStatement(sql.toString());
//			
//			pstmt.setString(1, member.getUserPwd());
//			pstmt.setString(2, member.getUserName());
//			pstmt.setString(3, member.getUserAddress());
//			pstmt.setString(4, member.getUserPhone());
//			pstmt.setString(5, member.getUserId());
//			
//			int res = pstmt.executeUpdate();
//			System.out.println(res);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBUtil.close(pstmt);
//			DBUtil.close(conn);
//		}
//		
//	}
//
//	@Override
//	public void deleteMember(String id) {
//		System.out.println("딜리트DAO실행");
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		Member m = null;
//		try {
//			conn = DBUtil.getConnection();
//			StringBuffer sql = new StringBuffer();
//			sql.append("delete ");
//			sql.append(" from members ");
//			sql.append(" where userId = ?");
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, id);
//			int res = pstmt.executeUpdate();
//			
//			System.out.println(res);
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBUtil.close(pstmt);
//			DBUtil.close(conn);
//		}
//		
//	}
//
//	@Override
//	public Member lookupmember(String id) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		Member m = null;
//		try {
//			conn = DBUtil.getConnection();
//			StringBuffer sql = new StringBuffer();
//			sql.append("select * ");
//			sql.append(" from members ");
//			sql.append(" where userId = ?");
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, id);
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				m = new Member(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
//						rs.getString(5));
//			}
//			
//			
//			return m;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBUtil.close(pstmt);
//			DBUtil.close(conn);
//		}
//		return null;
//		
//	}
//
//	@Override
//	public String findPassword(String id, String name, String phoneNumber) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		Member m = null;
//		try {
//			conn = DBUtil.getConnection();
//			StringBuffer sql = new StringBuffer();
//			sql.append("select userPwd from members where userId=? and userName=? and userPhone=?;");
//			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setString(1, id);
//			pstmt.setString(2, name);
//			pstmt.setString(3, phoneNumber);
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				return rs.getString(1);
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			DBUtil.close(pstmt);
//			DBUtil.close(conn);
//		}
//		return null;
//	}
//	
//	
//}
