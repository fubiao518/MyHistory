package com.zhidisoft.manage.servlet.payer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.manage.dao.impl.PayerDaoImpl;

import net.sf.json.JSONObject;

@WebServlet("/taxPayerList.do")
public class PayerListServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		String page = req.getParameter("page");
		String rows = req.getParameter("rows");
		String payerName = req.getParameter("payerName");
		String payerCode = req.getParameter("payerCode");
		PayerDaoImpl dao = new PayerDaoImpl();
		List<Map<String, String>> list = dao.getListByMap(Integer.parseInt(page), Integer.parseInt(rows), payerName,payerCode);
		Map<String, Object> map = new HashMap<String, Object>();
		int count = dao.getCount();
		map.put("total", count);
		map.put("rows", list);
		PrintWriter writer = res.getWriter();
		JSONObject json = JSONObject.fromObject(map);
		writer.print(json);
		writer.flush();
		writer.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	
}
