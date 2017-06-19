package com.zhidisoft.manage.servlet.payer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.base.ResponseResult;
import com.zhidisoft.manage.dao.impl.PayerDaoImpl;
import com.zhidisoft.manage.dao.impl.TaxerDaoImpl;
import com.zhidisoft.manage.entity.Payer;

import net.sf.json.JSONObject;
@WebServlet("/deletePayer.do")
public class DeletePayerByIdServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		PayerDaoImpl dao = new PayerDaoImpl();
		//获得参数
		String id = req.getParameter("id");
		//调用删除方法
		ResponseResult result = new ResponseResult(false, "删除失败！");
		boolean state = dao.deleteById(Integer.parseInt(id));
		if (state) {
			result.setMsg("删除成功");
			result.setSuccess(true);
		}
		JSONObject json = JSONObject.fromObject(result);
		PrintWriter writer = res.getWriter();
		writer.print(json.toString());
		writer.flush();
		writer.close();
	}

	
}
