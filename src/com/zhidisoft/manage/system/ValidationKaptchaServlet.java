package com.zhidisoft.manage.system;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.code.kaptcha.Constants;
import com.zhidisoft.base.ResponseResult;

import net.sf.json.JSONObject;
@WebServlet("/validation.do")
public class ValidationKaptchaServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json;charset=UTF-8");
		HttpSession session = req.getSession();
		ResponseResult result = new ResponseResult(false, "验证码错误");
		String kaptcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		String code = req.getParameter("captcha");
		if (kaptcha.equalsIgnoreCase(code)) {
			result.setMsg("");
			result.setSuccess(true);
		}
		JSONObject json= JSONObject.fromObject(result);
		PrintWriter out = res.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	
}
