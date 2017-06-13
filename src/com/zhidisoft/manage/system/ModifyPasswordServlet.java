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
import com.zhidisoft.manage.dao.impl.UserDaoImpl;
import com.zhidisoft.manage.entity.User;
import com.zhidisoft.util.EncryptUtil;

import net.sf.json.JSONObject;
@WebServlet("/modifypassword.do")
public class ModifyPasswordServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String oldPassword = req.getParameter("oldPassword");
		String newPassword = req.getParameter("newPassword");
		User user = (User) session.getAttribute("user");
		String username = user.getUsername();
		ResponseResult result = new ResponseResult(false, "原密码错误！");
		UserDaoImpl dao = new UserDaoImpl();
		String s = EncryptUtil.encryptMD5(oldPassword);
		if (s.equals(user.getPassword())) {
			user.setPassword(EncryptUtil.encryptMD5(newPassword));
			if (dao.update(user)) {
				result.setMsg("修改成功");
				result.setSuccess(true);
			}
		}
		PrintWriter writer = resp.getWriter();
		JSONObject json = JSONObject.fromObject(result);
		writer.print(json.toString());
		writer.flush();
		writer.close();
	}

	
}
