package com.zhidisoft.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求字符编码过滤器
 * @author 
 *
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {
	
	private String encoding = "UTF-8";

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest)request;
		HttpServletResponse httpResp = (HttpServletResponse)response;
		
		httpReq.setCharacterEncoding(encoding);
		httpResp.setCharacterEncoding(encoding);
		httpResp.setContentType("text/html; charset="+encoding);
		
		chain.doFilter(httpReq, httpResp);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String encoding = filterConfig.getInitParameter("encoding");
		if(encoding!=null && !encoding.isEmpty()){
			this.encoding = encoding;
		}
	}

	
}
