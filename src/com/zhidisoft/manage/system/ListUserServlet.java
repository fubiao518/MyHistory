package com.zhidisoft.manage.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.manage.dao.impl.UserDaoImpl;
import com.zhidisoft.manage.entity.User;

import net.sf.json.JSONArray;


@WebServlet("/allUser.do")
public class ListUserServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		UserDaoImpl dao = new UserDaoImpl();
		List<User> list = dao.getAll();
		JSONArray json = JSONArray.fromObject(list);
		//返回数据的集合
		PrintWriter writer = res.getWriter();
		writer.print(json);
		writer.flush();
		writer.close();
	}

	
}
