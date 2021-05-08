package com.ssafy.happyhouse.model.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.dto.EnvInfoDto;

public class EnvGuideCheckDAOImpl implements EnvGuideCheckDAO{

	private static EnvGuideCheckDAO envGuideCheckDAO;
	
	private EnvGuideCheckDAOImpl() {}
	
	public static EnvGuideCheckDAO getEnvGuideCheckDAO()
	{
		if(envGuideCheckDAO == null)
			envGuideCheckDAO = new EnvGuideCheckDAOImpl();
		return envGuideCheckDAO;
	}
	
	@Override
	public List<EnvInfoDto> list() {
		
		List<EnvInfoDto> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * \n";
			sql += "from envinfo_view \n";
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				EnvInfoDto e = new EnvInfoDto(rs.getString("code"), rs.getString("name"), rs.getString("type"), rs.getString("address"));
				result.add(e);
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

	@Override
	public List<EnvInfoDto> listByDong(String code) {
		
		List<EnvInfoDto> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * \n";
			sql += "from envinfo_view \n";
			sql += "where code=?";
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				EnvInfoDto e = new EnvInfoDto(rs.getString("code"), rs.getString("name"), rs.getString("type"), rs.getString("address"));
				result.add(e);
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
