package com.ssafy.happyhouse.model.mapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.model.CoronaDto;
import com.ssafy.model.Hospital;
import com.ssafy.model.HouseDealDto;
import com.ssafy.util.DBUtil;


public class HousedealDAOImpl implements HousedealDAO {
	private static HousedealDAO housedealDao;
	
	private HousedealDAOImpl() {}
	
	public static HousedealDAO getHousedealDAO()
	{
		if(housedealDao == null)
			housedealDao = new HousedealDAOImpl();
		return housedealDao;
	}
	
	@Override
	public List<String> gungu(String city) {
		List<String> result = new ArrayList();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select gugun \n";
			sql += "from baseaddress \n";
			sql += "where city = ?";
			sql += "group by gugun";
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, city);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				result.add(rs.getString("gugun"));
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
	public List<String> dong(String gu) {
		List<String> result = new ArrayList();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();

			String sql = "select dong \n";
			sql += "from baseaddress \n";
			sql += "where gugun = ?";
			sql += "group by dong";
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, gu);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				result.add(rs.getString("dong"));
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
	public List<HouseDealDto> treadInfo(String dong) {
		List<HouseDealDto> result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();

			String sql = "select a.*, b.lat, b.lng \n";
			sql += "from housedeal a left join houseinfo b on a.AptName = b.AptName \n";
			sql += "where a.dong = ?";
			sql += "group by a.jibun";
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dong);
			rs = pstmt.executeQuery();
			
			result = new ArrayList();
			
			while (rs.next()) {
				HouseDealDto temp = new HouseDealDto();
				temp.setHno(rs.getString("no"));
				temp.setDong(rs.getString("dong"));
				temp.setAptname(rs.getString("AptName"));
				temp.setCode(rs.getString("code"));
				temp.setDealAmount(rs.getString("dealAmount"));
				temp.setDealYear(rs.getString("dealYear"));
				temp.setDealMonth(rs.getString("dealMonth"));
				temp.setDealDay(rs.getString("dealDay"));
				temp.setArea(rs.getString("area"));
				temp.setLat(rs.getString("lat"));
				temp.setLng(rs.getString("lng"));
				temp.setJibun((rs.getString("jibun")));
				
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

	@Override
	public List<HouseDealDto> getReal(String name) {
		List<HouseDealDto> result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();

			String sql = "select AptName, dealAmount, area, type, dealYear, dealMonth, dealDay \n";
			sql += "from housedeal \n";
			sql += "where AptName = ?";
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			result = new ArrayList();
			
			while (rs.next()) {
				HouseDealDto temp = new HouseDealDto();
				temp.setAptname(rs.getString("AptName"));
				temp.setDealAmount(rs.getString("dealAmount"));
				temp.setDealYear(rs.getString("dealYear"));
				temp.setDealMonth(rs.getString("dealMonth"));
				temp.setDealDay(rs.getString("dealDay"));
				temp.setArea(rs.getString("area"));
				
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

	@Override
	public List<CoronaDto> getCorona(String gu) {
		List<CoronaDto> result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();

			String sql = "select * \n";
			sql += "from corona \n";
			sql += "where gungu = ?";
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, gu);
			rs = pstmt.executeQuery();
			
			result = new ArrayList();
			
			while (rs.next()) {
				CoronaDto temp = new CoronaDto();
				temp.setCname(rs.getString("cname"));
				temp.setGungu(rs.getString("gungu"));
				temp.setAddress(rs.getString("address"));
				temp.setOtime(rs.getString("otime"));
				temp.setStime(rs.getString("stime"));
				temp.setHtime(rs.getString("htime"));
				temp.setPhone(rs.getString("phone"));
				
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

	@Override
	public List<Hospital> getHospital(String gu) {
		List<Hospital> result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();

			String sql = "select * \n";
			sql += "from hospital \n";
			sql += "where gungu = ?";
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, gu);
			rs = pstmt.executeQuery();
			
			result = new ArrayList();
			
			while (rs.next()) {
				Hospital temp = new Hospital();
				temp.setSido(rs.getString("sido"));
				temp.setGungu(rs.getString("gungu"));
				temp.setHname(rs.getString("hname"));
				temp.setAddress(rs.getString("address"));
				temp.setType(rs.getString("type"));
				temp.setPhone(rs.getString("phone"));
				
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
