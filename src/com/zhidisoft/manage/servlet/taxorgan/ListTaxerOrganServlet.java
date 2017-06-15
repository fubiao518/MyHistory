package com.zhidisoft.manage.servlet.taxorgan;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.manage.dao.impl.TaxOrganDaoImpl;
import com.zhidisoft.manage.entity.TaxOrgan;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/allTaxOrgan.do")
public class ListTaxerOrganServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		TaxOrganDaoImpl  dao = new TaxOrganDaoImpl();
		//获得表的所有数据
		List<TaxOrgan> list = dao.getAll();
		JSONArray json = JSONArray.fromObject(list);
		//返回数据
		PrintWriter writer = res.getWriter();
		writer.print(json);
		writer.flush();
		writer.close();
		
	}

	
}
