package com.ssafy.happyhouse.model.mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.model.Notice;
import com.ssafy.util.DBUtil;

public class NoticeDaoImpl implements NoticeDao {

	private static NoticeDao noticeDao;
	
	private NoticeDaoImpl() {}
	
	public static NoticeDao getNoticeDao()
	{
		if(noticeDao == null)
			noticeDao = new NoticeDaoImpl();
		return noticeDao;
	}

	@Override
	public List<Notice> searchAll() {

		ArrayList<Notice> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * ");
			sql.append(" from notices");
			
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Notice n = new Notice(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), Integer.parseInt(rs.getString(4)));
				list.add(n);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return null;
	}
	
	

	@Override
	public Notice searchById(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * ");
			sql.append(" from notices ");
			sql.append(" where id = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Notice(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), Integer.parseInt(rs.getString(4)));
			}
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
	public List<Notice> searchByTitle(String str) {
		ArrayList<Notice> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * from notices where title LIKE ?;");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, "%"+str+"%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Notice n = new Notice(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), Integer.parseInt(rs.getString(4)));
				list.add(n);
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
	public boolean insertNotice(Notice notice) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert into notices(title,content,views) values (?,?,?); ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getContent());
			pstmt.setInt(3, notice.getViews());

			int res = pstmt.executeUpdate();
			if(res!=0) return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return false;
	}

	@Override
	public boolean deleteNoticeById(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("delete from notices where id=?");

			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, id);
			
			int res = pstmt.executeUpdate();
			if(res!=0) return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return false;
	}

	@Override
	public boolean updateNotice(Notice notice) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("update notices set title=?, content=?, views=?  where id=?;");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getContent());
			pstmt.setInt(3, notice.getViews());
			pstmt.setInt(4, notice.getId());

			int res = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(pstmt);
			DBUtil.close(conn);
		}
		return false;
	}

	
}
