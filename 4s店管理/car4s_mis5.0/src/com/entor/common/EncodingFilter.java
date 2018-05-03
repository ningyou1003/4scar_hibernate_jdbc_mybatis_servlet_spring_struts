package com.entor.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 编码过滤器
 * @author ZHQL
 */
public class EncodingFilter implements Filter {
	//编码方式
	private String encodingType;
	@Override
	public void init(FilterConfig fc) throws ServletException {
		//获取过滤器初始化参数
		encodingType = fc.getInitParameter("encodingType");
	}
	@Override
	public void destroy() {
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain fc) throws IOException, ServletException {
		// 需求强制转换才能得到请求对象
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
        //请求编码
		request.setCharacterEncoding(encodingType);
		//响应编码
		response.setCharacterEncoding(encodingType);
		//response.setContentType("text/html;charset=" + encodingType);
		fc.doFilter(req, resp);
	}
}
