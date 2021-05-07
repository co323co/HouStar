package com.ssafy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ssafy.model.EnvInfoDto;
import com.ssafy.model.service.EnvGuideCheckServiceImpl;

@WebServlet("/envguidecheck")
public class EnvGuideCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EnvGuideCheckServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		String root = request.getContextPath();
		if("mvEnvGuideCheck".equals(act)) {
			mvEnvGuidCheck(request, response);
		}
		else if("selectFavDong".equals(act)) {
			selectFavDong(request, response );
		}
	}

	private void selectFavDong(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		System.out.println("들어왔어");
		String code = request.getParameter("code");
		
			//반드시 바꿔 준 다음에 Writer를 만들어야 해!!
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			System.out.println("여기양");
			List<EnvInfoDto> envinfoList = EnvGuideCheckServiceImpl.getEnvGuideCheckService().listByDong(code);
			JSONArray arr = new JSONArray(); //JSON 오브젝트 사용하려면 library에 json-simple을 꼭 넣어줘야한다! 
			for(EnvInfoDto dto : envinfoList) {
				JSONObject obj = new JSONObject();
				obj.put("name", dto.getName());
				obj.put("type", dto.getType());
				obj.put("address", dto.getAddress());
				arr.add(obj);
			}
			System.out.println(arr.toJSONString());
			out.write(arr.toJSONString());
	}

	private void mvEnvGuidCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		String boxidx = (String) request.getAttribute("boxidx");
		List<EnvInfoDto> envinfoList = (List<EnvInfoDto>) request.getAttribute("envinfoList");
		if(boxidx!=null) request.setAttribute("boxidx", boxidx);
		if(envinfoList!=null) request.setAttribute("envinfoList", envinfoList);
		RequestDispatcher disp = request.getRequestDispatcher("inter_air.jsp");
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}
	
	

}
