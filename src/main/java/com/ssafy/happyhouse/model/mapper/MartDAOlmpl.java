package com.ssafy.happyhouse.model.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.model.Mart;
import com.ssafy.util.DBUtil;

public class MartDAOlmpl implements MartDAO {

	@Override
	public List<Mart> getinfo(String code) {
		
		List<Mart> result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();

			String sql = "select * ";
			sql += "from mart ";
//			sql += "where s = ?";
			// s 말고 r 로 해야 전국기준으로 안겹침
			sql += "where r = ?";
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			
			result = new ArrayList();
			
			while (rs.next()) {
				Mart temp = new Mart();
				temp.setDong(rs.getString("s"));
				temp.setLat(rs.getString("mm"));	//헷갈려서 둘이 바꿈 ㅎ
				temp.setLng(rs.getString("ll"));	//
				temp.setBictitle(rs.getString("e"));
				temp.setDoro(rs.getString("ff"));
				temp.setMartname(rs.getString("b"));
				result.add(temp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return result;
		
	}

}
