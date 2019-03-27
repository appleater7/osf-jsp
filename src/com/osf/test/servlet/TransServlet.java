package com.osf.test.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.osf.test.service.CommonCodeService;
import com.osf.test.service.TransService;
import com.osf.test.service.impl.CommonCodeServiceImpl;
import com.osf.test.service.impl.TransServiceImpl;

public class TransServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommonCodeService ccs = new CommonCodeServiceImpl();
	private TransService ts = new TransServiceImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/transper/trans.jsp");
		request.setAttribute("ccList", ccs.selectCommonCodeList("trans"));
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 소스와 타겟이 올바르게 들어왔는지 유효성 검사를 해줘야 한다.
		request.setCharacterEncoding("utf-8");
		String source = request.getParameter("source");
		String target = request.getParameter("target");
		String text = request.getParameter("text");
		request.setAttribute("rMap", ts.transperText(source, target, text));
		doGet(request, response);
	}
}
