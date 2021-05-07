package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.util.DBUtil;

public class HouseMapDaoImpl implements HouseMapDao {
	
	private static HouseMapDao houseMapDao;
	
	private HouseMapDaoImpl() {}
	
	public static HouseMapDao getHouseMapDao() {
		if(houseMapDao == null)
			houseMapDao = new HouseMapDaoImpl();
		return houseMapDao;
	}

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SidoGugunCodeDto> list = new ArrayList<SidoGugunCodeDto>();
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT left(sido_code,2) sido_code, sido_name FROM sidocode \n");
			sql.append("ORDER BY sido_code");
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SidoGugunCodeDto dto = new SidoGugunCodeDto();
				dto.setSidoCode(rs.getString("sido_code"));
				dto.setSidoName(rs.getString("sido_name"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return list;
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SidoGugunCodeDto> list = new ArrayList<SidoGugunCodeDto>();
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT left(gugun_code,5) gugun_code, gugun_name FROM guguncode \n");
			sql.append("where left(gugun_code,2) = ?");
			sql.append("ORDER BY gugun_code");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, sido);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				SidoGugunCodeDto dto = new SidoGugunCodeDto();
				dto.setGugunCode(rs.getString("gugun_code"));
				dto.setGugunName(rs.getString("gugun_name"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return list;
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<HouseInfoDto> list = new ArrayList<HouseInfoDto>();
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT distinct dong, code FROM houseinfo \n");
			sql.append("where code = ? \n");
			sql.append("ORDER BY dong");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, gugun);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HouseInfoDto dto = new HouseInfoDto();
				dto.setCode(rs.getString("code"));
				dto.setDong(rs.getString("dong"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return list;
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<HouseInfoDto> list = new ArrayList<HouseInfoDto>();
		try {
			conn = DBUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT no,dong,AptName,code,jibun FROM houseinfo WHERE dong = ? \n");
			sql.append("ORDER BY AptName");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dong);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HouseInfoDto dto = new HouseInfoDto();
				dto.setNo(rs.getString("no"));
				dto.setDong(rs.getString("dong"));
				dto.setAptName(rs.getString("AptName"));
				dto.setCode(rs.getString("code"));
				dto.setJibun(rs.getString("jibun"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return list;
	}

}
