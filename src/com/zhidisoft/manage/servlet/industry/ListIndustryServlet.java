package com.zhidisoft.manage.servlet.industry;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.manage.dao.impl.IndustryDaoImpl;
import com.zhidisoft.manage.dao.impl.PayerDaoImpl;
import com.zhidisoft.manage.entity.Industry;
import com.zhidisoft.manage.entity.Payer;

import net.sf.json.JSONArray;


@WebServlet("/allIndustry.do")
public class ListIndustryServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		IndustryDaoImpl dao = new IndustryDaoImpl();
		List<Industry> list = dao.getAll();
		JSONArray json = JSONArray.fromObject(list);
		//返回数据的集合
		PrintWriter writer = res.getWriter();
		writer.print(json);
		writer.flush();
		writer.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	
}
