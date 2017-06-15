package com.zhidisoft.manage.servlet.taxsource;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.manage.dao.impl.TaxSourceDaoImpl;
import com.zhidisoft.manage.entity.TaxSource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/allTaxSour.do")
public class ListTaxSourceServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		TaxSourceDaoImpl dao = new TaxSourceDaoImpl();
		//获取所有表数据
		List<TaxSource> list = dao.getAll();
		JSONArray json = JSONArray.fromObject(list);
		//返回数据的集合
		PrintWriter writer = res.getWriter();
		writer.print(json);
		writer.flush();
		writer.close();
	}
	
	
}
