package com.zhidisoft.manage.servlet.taxer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.manage.dao.impl.TaxerDaoImpl;
import com.zhidisoft.manage.entity.Taxer;

import net.sf.json.JSONArray;
@WebServlet("/allTaxer.do")
public class ListTaxerServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		TaxerDaoImpl dao = new TaxerDaoImpl();
		List<Taxer> list = dao.getAll();
		JSONArray json = JSONArray.fromObject(list);
		PrintWriter writer = res.getWriter();
		writer.print(json);
		writer.flush();
		writer.close();
	}
	
	
}
