package com.hamonize.portal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class PortalUrlInterceptor implements HandlerInterceptor{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // @Override
    // public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
    //         throws Exception {
    //             logger.info("\n\n\nafterCompletion,,,, {}",request.getRequestURI());

    //     HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    // }

    // @Override
    // public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
    //         ModelAndView modelAndView) throws Exception {
    //             logger.info("\n\n\npostHandle,,,, {}",request.getRequestURI());

    //     HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    // }

    // @Override
    // public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    //         throws Exception {
    //             logger.info("\n\n\npreHandler,,,, {}",request.getRequestURI());
    //             String url = request.getRequestURI();
                
    //             // if(url.contains("support/")){
    //             //     url = url.replace("board/", "");
 
    //             // }else if(url.contains("user/")){
    //             //     url = url.replace("board/", "");
    //             //     logger.info("url : {}", url);
 
    //             // }else if(url.contains("subscribe/")){
    //             //     url = url.replace("board/", "");
                    
    //             // }
           
    //             logger.info("result url : {}", url);
    //             response.sendRedirect("/user/detail");
    //            return false;
    //         //  return HandlerInterceptor.super.preHandle(request, response, handler);
    // }
    
    
}
