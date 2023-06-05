package com.ism.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import antlr.collections.Enumerator;
@Component
@Order(value = 1)
public class CorsFilter implements Filter {

	@Override
	public void doFilter(ServletRequest reqx, ServletResponse respx, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("CrossOrigin Filter.......");
		HttpServletRequest request = (HttpServletRequest) reqx;
		HttpServletResponse response = (HttpServletResponse) respx;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE,PUT,OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type, Accept, X-Requested-With,token");

		System.out.println("token => " + request.getHeader("token"));

//		Enumeration<String> n = request.getHeaderNames();
//	
//		while(n.hasMoreElements()) {
//			System.out.println("==> "+n.nextElement());
//		}
		
		
		chain.doFilter(reqx, respx);
	}

}
