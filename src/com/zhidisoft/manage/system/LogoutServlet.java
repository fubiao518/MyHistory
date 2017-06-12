package com.zhidisoft.manage.system;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zhidisoft.base.ResponseResult;
import com.zhidisoft.manage.entity.User;

import net.sf.json.JSONObject;

@WebServlet("/logout.do")
public class LogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		ResponseResult result = new ResponseResult(false, "退出系统失败！");
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			result.setSuccess(true);
			result.setMsg("退出成功！");
			session.invalidate();
		}
		JSONObject json = JSONObject.fromObject(result);
		PrintWriter out = res.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();

	}

}
