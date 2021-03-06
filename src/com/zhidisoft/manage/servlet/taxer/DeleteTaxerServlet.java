package com.zhidisoft.manage.servlet.taxer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.base.ResponseResult;
import com.zhidisoft.manage.dao.impl.TaxerDaoImpl;
import com.zhidisoft.manage.entity.Taxer;

import net.sf.json.JSONObject;
@WebServlet("/deleteTaxer.do")
public class DeleteTaxerServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		TaxerDaoImpl dao = new TaxerDaoImpl();
		//获得参数
		String id = req.getParameter("id");
		//调用删除方法
		ResponseResult result = new ResponseResult(false, "删除失败！");
		//判断是否有任务 有任务删除不了
		if (dao.isHaveTaxOrgan(Integer.parseInt(id))) {
			result.setMsg("还有任务未完成，不能删除");
			
		}else {
			boolean state = dao.deleteById(Integer.parseInt(id));
			
			if (state) {
				result.setMsg("删除成功");
				result.setSuccess(true);
			}
		}
		JSONObject json = JSONObject.fromObject(result);
		PrintWriter writer = res.getWriter();
		writer.print(json.toString());
		writer.flush();
		writer.close();
	}

	
}
