package com.zhidisoft.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.zhidisoft.base.BaseException;
import com.zhidisoft.base.ResponseResult;
import com.zhidisoft.exception.DataAccessException;
import com.zhidisoft.exception.NoSuchActionException;
import com.zhidisoft.exception.NoSuchParameterException;

/**
 * 统一异常处理过滤器
 * @author 卢健良
 *
 */
public class ExceptionFilter implements Filter {

	@Override
	public void destroy() {
		
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest)request;
		HttpServletResponse httpResp = (HttpServletResponse)response;
		
		ResponseResult result = null;
		//通过try-catch捕捉请求过程中出现的异常
		try{
			chain.doFilter(httpReq, httpResp);
		}catch(DataAccessException e){
			e.printStackTrace();
			result = new ResponseResult(false, e.getMessage());
		}catch(NoSuchParameterException e){
			e.printStackTrace();
			result = new ResponseResult(false, e.getMessage());
		}catch(NoSuchActionException e){
			e.printStackTrace();
			result = new ResponseResult(false,e.getMessage());
		}catch(BaseException e){
			e.printStackTrace();
			result = new ResponseResult(false, e.getMessage());
			
		}catch(Exception e){
			e.printStackTrace();
			result = new ResponseResult(false,e.getMessage());
		}
		
		//如果result对象不为null值，说明应用程序出现异常信息
		if(result!=null){
			String requestType = httpReq.getHeader("x-requested-with");
			//判断本次请求是否为ajax请求，如果是将result对象转换为json格式并且输出给客户端,否则重定向到500或者404页面
			if("XMLHttpRequest".equals(requestType)){
				JSONObject json = JSONObject.fromObject(result);
				PrintWriter out = httpResp.getWriter();
				out.print(json.toString());
				out.flush();
				out.close();
				return ;
			}
			
			if(httpResp.getStatus() == 404){
				httpResp.sendRedirect(httpReq.getContextPath()+"/404.html");
				return;
			}
			
			if(httpResp.getStatus() >= 500 ){
				httpResp.sendRedirect(httpReq.getContextPath()+"/500.html");
				return;
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		
	}

}
