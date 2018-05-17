package cn.lth.base;

import cn.lth.util.DemoException;
import cn.lth.util.VerifyUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liutaihang
 * @version : 1.0
 * @Date : Create in 17:49 2018/4/13
 * @Description : ${TODO}
 */
@Slf4j
public class DemoInterceptor implements HandlerInterceptor{

    @Autowired
    private VerifyUtil VerifyUtil;

    private Gson GSON = new GsonBuilder().serializeNulls().create();
    private boolean isBusinessError = false;
    private String msg = "";



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean result = false;
        log.info("当前访问地址 >> {}", request.getRequestURI());
        if(VerifyUtil.verifyURI(request.getRequestURI())){
            result = true;
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

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

}

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
