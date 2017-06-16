package com.zhidisoft.manage.servlet.taxer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.base.ResponseResult;
import com.zhidisoft.manage.dao.impl.TaxerDaoImpl;
import com.zhidisoft.manage.entity.Taxer;
import com.zhidisoft.util.BeanUtil;

import net.sf.json.JSONObject;
@WebServlet("/addTaxer.do")
public class AddTaxerServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json;charset=UTF-8");
		//获取到从ajx请求中格式化表单信息的集合数据
		Map<String, String[]> parameterMap = req.getParameterMap();
		Taxer taxer = new Taxer();
		TaxerDaoImpl dao = new TaxerDaoImpl();
		//将map集合转化为实体类
		 BeanUtil.mapToBean(taxer, parameterMap);
		 ResponseResult result = new ResponseResult(false, "添加失败");
		 //调用操作实现类的添加的方法
		 if (dao.update(taxer)) {
			result.setSuccess(true);
			result.setMsg("添加成功");
		}
		 PrintWriter writer = resp.getWriter();
		 JSONObject json = JSONObject.fromObject(result);
		 writer.print(json);
		 writer.flush();
		 writer.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json;charset=UTF-8");
		//获取到从ajx请求中格式化表单信息的集合数据
		Map<String, String[]> parameterMap = req.getParameterMap();
		Taxer taxer = new Taxer();
		TaxerDaoImpl dao = new TaxerDaoImpl();
		//将map集合转化为实体类
		 BeanUtil.mapToBean(taxer, parameterMap);
		 ResponseResult result = new ResponseResult(false, "添加失败");
		 //调用操作实现类的添加的方法
		 if (dao.add(taxer)) {
			result.setSuccess(true);
			result.setMsg("添加成功");
		}
		 PrintWriter writer = resp.getWriter();
		 JSONObject json = JSONObject.fromObject(result);
		 writer.print(json);
		 writer.flush();
		 writer.close();
	}

	
}
