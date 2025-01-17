package com.mall.shop.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName="CharsetFilter",urlPatterns= {"/*"})
public class CharsetFilter implements Filter {

	@Override
	public void init(FilterConfig fc)throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		request.setCharacterEncoding("utf-8");//接收用户Submit的参数（request里面）传递过来的字符的格式信息
		response.setCharacterEncoding("utf-8");//response发送过去的编码格式

		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {

	}

}
