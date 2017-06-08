package com.zhidisoft.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zhidisoft.exception.NoSuchActionException;
import com.zhidisoft.exception.NoSuchParameterException;

public class BaseServlet extends HttpServlet{

	
	private static final long serialVersionUID = -155258685468332583L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取请求参数中action参数，该参数用于表示本次请求处理的方法
		String action = request.getParameter("action");
		if(action == null || action.isEmpty()){
			throw new NoSuchParameterException("本次请求未包含action参数");
		}
		
		Class<?> servletClass = this.getClass();
		try {
			//根据action值获取处理对应请求的方法
			Method actionMethod = servletClass.getDeclaredMethod(action, HttpServletRequest.class,HttpServletResponse.class);
			//调用对应方法处理用户请求
			actionMethod.invoke(this, request,response);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new NoSuchActionException("服务器端确少处理本次请求的"+action+"(HttpServletRequest request, HttpServletResponse response)方法");
		} catch (Exception e){
			e.printStackTrace();
			throw new BaseException("服务器端出错："+e.getMessage());
		}
	}
	
	/**
	 * 处理添加数据请求的方法
	 * @param request
	 * @param response
	 */
	protected void save(HttpServletRequest request,HttpServletResponse response){
		
	}
	
	/**
	 * 处理更新数据请求的方法
	 * @param request
	 * @param response
	 */
	protected void update(HttpServletRequest request,HttpServletResponse response){
		
	}
	
	/**
	 * 处理修改数据前获取单条信息的方法
	 * @param request
	 * @param response
	 */
	protected void edit(HttpServletRequest request,HttpServletResponse response){
		
	}
	
	/**
	 * 处理删除某条信息的请求方法
	 * @param request
	 * @param response
	 */
	protected void remove(HttpServletRequest request,HttpServletResponse response){
		
	}
	
	/**
	 * 处理获取单条信息请求的方法
	 * @param request
	 * @param response
	 */
	protected void get(HttpServletRequest request,HttpServletResponse response){
		
	}
	
	/**
	 * 处理分页请求的方法
	 * @param request
	 * @param response
	 */
	protected void page(HttpServletRequest request,HttpServletResponse response){
		
	}
	
	/**
	 * 处理获取所有列表信息的请求方法
	 * @param request
	 * @param response
	 */
	protected void list(HttpServletRequest request,HttpServletResponse response){
		
	}
	
	/**
	 * 输出字符串
	 * @param content
	 * @param response
	 * @throws IOException
	 */
	protected void print(String content,HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		out.append(content);
		out.flush();
	}
	
	protected void printJSONObject(JSONObject json,HttpServletResponse response) throws IOException{
		response.setContentType("application/json; charset="+response.getCharacterEncoding());
		print(json.toString(), response);
	}
	
	protected void printJSONArray(JSONArray json,HttpServletResponse response) throws IOException{
		response.setContentType("application/json; charset="+response.getCharacterEncoding());
		print(json.toString(), response);
	}
}
