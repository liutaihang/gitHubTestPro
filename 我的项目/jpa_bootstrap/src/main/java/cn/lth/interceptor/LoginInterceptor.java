package cn.lth.interceptor;

import cn.lth.contant.UserEm;
import cn.lth.util.DemoException;
import cn.lth.util.VerifyUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author liutaihang
 * @version : 1.0
 * @Date : Create in 17:49 2018/4/13
 * @Description : ${TODO}
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor{

    @Autowired
    private VerifyUtil VerifyUtil;

    private Gson GSON = new GsonBuilder().serializeNulls().create();
    private boolean isBusinessError = false;
    private String msg = "";


    /**
     * 这个方法是在过滤器的chain.doFilter(request, response)方法的前一步执行，
     * 也就是在 [System.out.println("before...")][chain.doFilter(request, response)]之间执行。
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = false;
        log.info("当前访问地址 >> {}", request.getRequestURI());
        log.info(JSONObject.toJSONString(request.getParameterMap()));
        if(!VerifyUtil.turnLogin(request, request.getRequestURI())){
            response.sendRedirect("/login");
            return false;
        }

        //访问出错处理
        if (request.getDispatcherType().equals(DispatcherType.ERROR)) {
            int errorCode = response.getStatus();
            log.error("错误码 >> " + errorCode);
            String returnMsgJson = GSON.toJson(msg);
            if (isBusinessError) {
                response.setStatus(HttpStatus.OK.value());
            }
            //重置消息
            msg = "SYSTEM_ERROR";
            isBusinessError = false;
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(returnMsgJson);
        } else {
            result = true;
        }
        return result;
    }

    /**
     * 在return ModelAndView之前进行，可以操控Controller的ModelAndView内容。
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {}

    /**
     * 在过滤器返回给前端前一步执行，
     * 也就是在[chain.doFilter(request, response)][System.out.println("after...")]之间执行
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        StringBuilder b = new StringBuilder();
        if(ex != null){
            if(ex instanceof DemoException){
                isBusinessError = true;
            }else{
                log.error("SYSTEM.ERROR:" + ex.toString());
                StackTraceElement[] stackTrace = ex.getStackTrace();
                for (StackTraceElement sta: stackTrace) {
                    b.append("\t").append(sta.getClassName() + ":" + sta.getFileName() + ":" + sta.getMethodName() + "():" + sta.getLineNumber()).append("\n");
                }
                log.error(b.toString());
            }
        }
    }
}
