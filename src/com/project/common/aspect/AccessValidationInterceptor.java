package com.project.common.aspect;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.project.util.ApplicationObject;

/**
 * <p>springMVC拦截器对request进行拦截</p>

 *
 */
public class AccessValidationInterceptor extends HandlerInterceptorAdapter
{
	@Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
  {
		HttpSession session=request.getSession();
		if(null!=session&&(null!=session.getAttribute("student")||null!=session.getAttribute("teacher"))){
			return true;
		}else{
			response.sendRedirect(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/index.html");
			return false;
		}
  }
	
	@Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
  {
	  super.afterCompletion(request, response, handler, ex);
  }

	@Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
  {
	  super.postHandle(request, response, handler, modelAndView);
  }

}
