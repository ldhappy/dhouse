package com.ld.dhouse.web.interceptor;

import com.ld.dhouse.web.config.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一放置需要返回前端的配置信息
 * 梁聃 2017/12/20 22:26
 */
@Component
public class ConfigInfoInterceptor implements HandlerInterceptor {
    @Autowired
    WebConfig webConfig;
    //在请求处理之前进行调用（Controller方法调用之前）
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
    }
    //请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        modelAndView.getModel().put("web",webConfig);
    }
    //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}
