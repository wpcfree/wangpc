package com.wpc.common.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wpc.common.msg.AjaxResult;
import com.wpc.common.utils.Servlets;
import com.wpc.common.utils.exception.Exceptions;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MyExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		HandlerMethod method = null != handler ? (HandlerMethod) handler : null;//获取抛出异常的方法对象
		if(method != null){
			// ajax 请求

			if (Servlets.isAjaxRequest(request)) {
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				response.setCharacterEncoding("UTF-8");
				AjaxResult result = AjaxResult.error();
				result.setMsg(Exceptions.getStackTraceAsString(ex));
				try{
					PrintWriter writer =  response.getWriter();
					writer.write(new ObjectMapper().writeValueAsString(result));
					writer.flush();
				}catch (IOException e){
					throw new RuntimeException(e);
				}
				return null;
			} else {
				ModelAndView mv = new ModelAndView();
				mv.addObject("errorMsg", ex.getLocalizedMessage());
				mv.setViewName("common/error/500");//Get方法,跳转到500页面
				return mv;//500
			}
		}else{
			throw new NullPointerException("MyExceptionResolver中HandlerMethod为空!");
		}
	}

}
