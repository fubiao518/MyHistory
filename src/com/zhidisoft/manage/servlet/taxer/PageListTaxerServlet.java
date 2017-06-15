package com.zhidisoft.manage.servlet.taxer;

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

import com.zhidisoft.manage.dao.impl.TaxerDaoImpl;

import net.sf.json.JSONObject;
@WebServlet("/taxerList.do")
public class PageListTaxerServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		String page = req.getParameter("page");
		String rows = req.getParameter("rows");
		String taxerName = req.getParameter("taxerName");
		TaxerDaoImpl dao = new TaxerDaoImpl();
		List<Map<String, String>> list = dao.getListByMap(Integer.parseInt(page), Integer.parseInt(rows), taxerName);
		//获取总条数
		int count = dao.getCount();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("rows", list);
		result.put("total", count);
		PrintWriter writer = res.getWriter();
		JSONObject json = JSONObject.fromObject(result);
		writer.print(json.toString());
		writer.flush();
		writer.close();
	}

	
}
