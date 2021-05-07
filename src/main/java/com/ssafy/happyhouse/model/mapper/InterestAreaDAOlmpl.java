package com.ssafy.happyhouse.model.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.model.InterestArea;
import com.ssafy.util.DBUtil;

public class InterestAreaDAOlmpl implements InterestAreaDAO {

	@Override
	public void insertArea(InterestArea interestArea) {
		System.out.println("관심지역 등록 DAO");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			

			
			//여기서 카운트 3개 하는쿼리 날리면 가능

			conn = DBUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert into interestarea values ((select code from baseaddress where dong = ?),?); ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, interestArea.getDong());
			pstmt.setString(2, interestArea.getUserid());

			int res = pstmt.executeUpdate();

			//일단 추가는 됨
			//에러 페이지 만들어야 되나 고민
			
			
			
			System.out.println(res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		
		

	}

	@Override
	public void deleteArea(String code, String userId) {
		System.out.println("관심지역 삭제 DAO");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			
			conn = DBUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("delete from interestarea where userid = ? and code = ?;");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);
			pstmt.setString(2, code);
			int res = pstmt.executeUpdate();
			
			System.out.println(res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}

	}


	@Override
	public List<String> findArea(String userId) {
		System.out.println("관심지역 찾기 DAO");		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<>();
		//
		
		try {			
			conn = DBUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select code\r\n" + 
					"from baseaddress\r\n" + 
					"where code in (select code from interestarea where userid = ?);");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("관심지역 찾기 성공 성공");
				list.add(rs.getString(1));
			}
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return null;
	}

}
