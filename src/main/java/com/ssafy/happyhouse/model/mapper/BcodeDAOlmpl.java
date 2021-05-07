package com.ssafy.happyhouse.model.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.ssafy.util.DBUtil;

public class BcodeDAOlmpl implements BcodeDAO {

	@Override
	public String findDongName(String code) {

		System.out.println("코드로 동 이름 찾기 DAO");		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//
		
		try {			
			conn = DBUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select dong from baseaddress where code = ?;");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, code);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("동 이름 찾기 성공");
			}
			return rs.getString(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return null;
	}

	@Override
	public String findCode(String dong) {
		System.out.println("동으로 코드 찾기 DAO");		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//
		
		try {			
			conn = DBUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select code from baseaddress where dong = ?;");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dong);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("코드 찾기 성공");
			}
			return rs.getString(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return null;
	}

	@Override
	public String findBcodeFromBname(String bname) {
		System.out.println("주소로 법정동코드 찾기  DAO");		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringTokenizer st = new StringTokenizer(bname," ");
		
		
		try {			
			conn = DBUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select bcode from bcodeset where bname like %?%?;");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, st.nextToken());
			pstmt.setString(2, st.nextToken());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("주소로 코드 찾기 성공" + rs.getString(1));
			}
			return rs.getString(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return null;
	}

	@Override
	public String findBnameFromBcode(String bcode) {
		System.out.println("코드로 주소  찾기  DAO");		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {			
			conn = DBUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select bname from bcodeset where bcode = ?;");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, bcode);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("코드로 주소 찾기 성공" + rs.getString(1));
				 
			}
			return rs.getString(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return null;
	}

	@Override
	public List<String> findDongNames(List<String> codeList) {
		// TODO Auto-generated method stub
		return null;
	}

}
