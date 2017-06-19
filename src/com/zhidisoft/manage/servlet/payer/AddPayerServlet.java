package com.zhidisoft.manage.servlet.payer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.base.ResponseResult;
import com.zhidisoft.manage.dao.impl.PayerDaoImpl;
import com.zhidisoft.manage.entity.Payer;
import com.zhidisoft.util.BeanUtil;

import net.sf.json.JSONObject;
@WebServlet("/addPayer.do")
public class AddPayerServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		Map<String, String[]> args = req.getParameterMap();
		PayerDaoImpl dao = new PayerDaoImpl();
		Payer payer = new Payer();
		BeanUtil.mapToBean(payer, args);
		ResponseResult result = new ResponseResult(false, "添加失败");
		 //调用操作实现类的添加的方法
		 if (dao.add(payer)) {
			result.setSuccess(true);
			result.setMsg("添加成功");
		}
		 PrintWriter writer = res.getWriter();
		 JSONObject json = JSONObject.fromObject(result);
		 writer.print(json);
		 writer.flush();
		 writer.close();
	}

	
}
