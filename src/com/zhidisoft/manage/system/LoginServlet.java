package com.zhidisoft.manage.system;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.bcel.classfile.Constant;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.zhidisoft.base.ResponseResult;
import com.zhidisoft.manage.dao.impl.UserDaoImpl;
import com.zhidisoft.manage.entity.User;
import com.zhidisoft.util.EncryptUtil;

import net.sf.json.JSONObject;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json;charset=UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String rem = req.getParameter("rem");
		UserDaoImpl dao = new UserDaoImpl();
		ResponseResult result = new ResponseResult(false, "用户名或密码错误");
		User user = dao.queryByName(username);
		if (user != null) {
			String encryptPassword = user.getPassword();
			String s = EncryptUtil.encryptMD5(password);

			if (encryptPassword.equals(s)) {
				// 记住密码，将密码设置到cookie中
				if ("on".equals(rem)) {
					Cookie cook = new Cookie("username", username);
					cook.setMaxAge(60 * 60 * 24 * 10);
					res.addCookie(cook);
				}
				HttpSession session = req.getSession();
				session.setAttribute("user", user);

				result.setSuccess(true);
				result.setMsg("登录成功！");
			}
		}
		PrintWriter out = res.getWriter();
		JSONObject json = JSONObject.fromObject(result);
		out.print(json.toString());
		out.flush();
		out.close();
	}

}
