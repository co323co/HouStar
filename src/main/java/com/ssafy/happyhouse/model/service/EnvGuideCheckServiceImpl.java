package com.ssafy.happyhouse.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.model.EnvInfoDto;
import com.ssafy.model.Notice;
import com.ssafy.model.dao.HousedealDAO;
import com.ssafy.model.dao.HousedealDAOImpl;
import com.ssafy.model.service.EnvGuideCheckService;
import com.ssafy.util.DBUtil;

public class EnvGuideCheckServiceImpl implements EnvGuideCheckService{

	private static EnvGuideCheckService envGuideCheckService;
	
	private EnvGuideCheckServiceImpl() {}
	
	public static EnvGuideCheckService getEnvGuideCheckService()
	{
		if(envGuideCheckService == null)
			envGuideCheckService = new EnvGuideCheckServiceImpl();
		return envGuideCheckService;
	}
	
	@Override
	public List<EnvInfoDto> list() {
		
		ArrayList<EnvInfoDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * from envinfo_view;");
			
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EnvInfoDto e = new EnvInfoDto(rs.getString("code"), rs.getString("name"), rs.getString("type"), rs.getString("address"));
				list.add(e);
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

	@Override
	public List<EnvInfoDto> listByDong(String code) {
		
		
		ArrayList<EnvInfoDto> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			String address = (new BcodeServicelmpl()).findBnameFromBcode(code);
			sql.append("select * from envinfo_view where address Like ?;");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, "%"+address+"%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EnvInfoDto e = new EnvInfoDto(rs.getString("code"), rs.getString("name"), rs.getString("type"), rs.getString("address"));
				list.add(e);
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
