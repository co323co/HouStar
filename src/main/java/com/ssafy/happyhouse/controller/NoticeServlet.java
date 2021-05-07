package com.ssafy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.model.Notice;
import com.ssafy.model.service.NoticeServiceImpl;

@WebServlet("/notice")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String act = request.getParameter("act");
		String root = request.getContextPath();
		if("post_notice".equals(act)) {
			postNotice(request, response);
		}
		else if("mvnotice".equals(act)) {
			mvNotice(request,response);
		} 
		else if("mvnoticeview".equals(act)) {
			mvNoticeView(request,response);
		}
		else if("delete_notice".equals(act)) {
			delteNotice(request,response);
		}
		else if("mv_noticeupdate".equals(act)) {
			mvNoticeUpdate(request,response);
		}
		else if("update_notice".equals(act)) {
			updateNotice(request,response);
		}
			
	
	}

	private void mvNoticeUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher disp = request.getRequestDispatcher("noticeupdate.jsp");
		disp.forward(request, response);
	}
	
	private void updateNotice(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int id = Integer.parseInt(request.getParameter("nid"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Notice notice = NoticeServiceImpl.getNoticeService().searchById(id);
		notice.setTitle(title);
		notice.setContent(content);
		NoticeServiceImpl.getNoticeService().updateNotice(notice);
		
		request.setAttribute("notice", notice);
		RequestDispatcher disp = request.getRequestDispatcher("noticeview.jsp");
		disp.forward(request, response);	
		
		}
		

	private void delteNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("nid"));
		NoticeServiceImpl.getNoticeService().deleteNoticeById(id);
		mvNotice(request, response);
	}

	private void mvNoticeView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("nid"));
		Notice notice = NoticeServiceImpl.getNoticeService().searchById(id);
		notice.setViews(notice.getViews()+1);
		NoticeServiceImpl.getNoticeService().updateNotice(notice);
		request.setAttribute("notice", notice);
		
		RequestDispatcher disp = request.getRequestDispatcher("noticeview.jsp");
		disp.forward(request, response);
	}

	private void mvNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Notice> noticeList;
		
		String search_value = request.getParameter("search_value");
		
		//제목 검색 없이 들어옴. 목차 전부 보여줌
		if(search_value==null) {
			noticeList = NoticeServiceImpl.getNoticeService().searchAll();
		}
		//제목 검색으로 들어옴
		else {
			noticeList = NoticeServiceImpl.getNoticeService().searchByTitle(search_value);
		}

		request.setAttribute("noticeList", noticeList);
		RequestDispatcher disp = request.getRequestDispatcher("notice.jsp");
		disp.forward(request, response);
	}

	private void postNotice(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
		String root = request.getContextPath();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		NoticeServiceImpl.getNoticeService().insertNotice(new Notice(title, content));
		mvNotice(request,response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
