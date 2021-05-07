package com.ssafy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.tribes.util.Arrays;

import com.ssafy.model.CoronaDto;
import com.ssafy.model.Hospital;
import com.ssafy.model.HouseDealDto;
import com.ssafy.model.Member;
import com.ssafy.model.service.HousedealService;
import com.ssafy.model.service.HousedealServiceImpl;

/**
 * Servlet implementation class KMainServlet
 */
@WebServlet("/KMain")
public class HouseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		String root = request.getContextPath();
		System.out.println(act);
		
		if("gungu".equals(act)) {
			gungu(request,response);
		}
		else if("dong".equals(act))
		{
			dong(request,response);
		}
		else if("trade".equals(act))
		{
			trade(request,response);
		}
		else if("RealTrade".equals(act))
		{
			Realtrade(request,response);
		}
	}
	private void Realtrade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		System.out.println(name);
		
		ArrayList<HouseDealDto> real = (ArrayList<HouseDealDto>) HousedealServiceImpl.getHousedealService().getReal(name);
		String path = "/around_search.jsp";
		
		for(int i=0;i<real.size();i++)
			System.out.println(real.get(i).toString());
		
		HttpSession session = request.getSession();
		session.setAttribute("real", real);
		
		RequestDispatcher disp = request.getRequestDispatcher(path);
		disp.forward(request, response);
		
	}
	private void trade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dong = request.getParameter("dong");
		String gu = request.getParameter("dongcode");
		
		System.out.println(dong);
		
		ArrayList<HouseDealDto> house = (ArrayList<HouseDealDto>) HousedealServiceImpl.getHousedealService().treadInfo(dong);
		List<CoronaDto> corona = HousedealServiceImpl.getHousedealService().getCorona(gu);
		List<Hospital> hospital = HousedealServiceImpl.getHousedealService().getHospital(gu);
		
//		for(int i=0;i<house.size();i++)
//			System.out.println(house.get(i).toString());
		
		
		String path = "/around_search.jsp";
		
		
		
		HttpSession session = request.getSession();
		session.setAttribute("tradeinfo", house);
		session.setAttribute("dd", dong);
		
		session.setAttribute("corona", corona);
		session.setAttribute("hospital", hospital);
		
		request.setAttribute("tlist", house);
		
		RequestDispatcher disp = request.getRequestDispatcher(path);
		disp.forward(request, response);
	}

	private void dong(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gu = request.getParameter("gu");
		String gu2 = null;
		
		if(gu!=null)
		{
		List<String> dong = HousedealServiceImpl.getHousedealService().dong(gu);
		
		
		System.out.println(dong);

		String path = "/index.jsp";
		
		HttpSession session = request.getSession();
		session.setAttribute("gungu", gu);	//현재 구
		session.setAttribute("dong", dong);	//동 리스트
		
		RequestDispatcher disp = request.getRequestDispatcher(path);
		disp.forward(request, response);
		}
		else
		{
			System.out.println("구2");
			gu2 = request.getParameter("gu2");
			
			List<String> dong = HousedealServiceImpl.getHousedealService().dong(gu2);
			
			System.out.println(dong);
			String path = "/around_search.jsp";
			
			HttpSession session = request.getSession();
			session.setAttribute("gungu", gu2);	//현재 구
			session.setAttribute("dong", dong);	//동 리스트
			
			
			RequestDispatcher disp = request.getRequestDispatcher(path);
			disp.forward(request, response);
			
		}
		
	}

	private void gungu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String city = request.getParameter("city");
		String city2 =null;
		if (city == null) {
			System.out.println("시2");
			city2 = request.getParameter("city2");

			List<String> gu = HousedealServiceImpl.getHousedealService().gungu(city2);

//			System.out.println(gu);
			String path = "/around_search.jsp";

			HttpSession session = request.getSession();
			session.setAttribute("ct", city2); // 현재 시
			session.setAttribute("gu", gu); // 구리스트

			RequestDispatcher disp = request.getRequestDispatcher(path);
			disp.forward(request, response);
		} else {
			
			List<String> gu = HousedealServiceImpl.getHousedealService().gungu(city);

//			System.out.println(gu);
			String path = "/index.jsp";

			HttpSession session = request.getSession();
			session.setAttribute("ct", city); // 현재 시
			session.setAttribute("gu", gu); // 구리스트

			RequestDispatcher disp = request.getRequestDispatcher(path);
			disp.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request,response);
	}

}
