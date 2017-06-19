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
import com.zhidisoft.manage.entity.Taxer;

import net.sf.json.JSONObject;
@WebServlet("/editPayer.do")
public class EditPayerServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		//获取到请求传过来的参数
		String id = req.getParameter("id");
		ResponseResult result = new ResponseResult(false, "修改失败！");
		PayerDaoImpl dao = new PayerDaoImpl();
		Payer payer = dao.getById(Integer.parseInt(id));
		if (payer != null) {
			result.setMsg("修改成功！");
			result.setSuccess(true);
			req.setAttribute("taxer", payer);
			req.getRequestDispatcher("manage/editTaxpayer.jsp").forward(req, res);
		}
		
		PrintWriter writer = res.getWriter();
		JSONObject json = JSONObject.fromObject(result);
		writer.print(json);
		writer.flush();
		writer.close();
	}

	
}
