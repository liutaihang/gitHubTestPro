package cn.lth.base;

import cn.lth.util.VerifyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean result = false;
        log.info("当前访问地址 >> {}", request.getRequestURI());
        System.err.println(request.getRequestURI());
        if(!VerifyUtil.verifyURI(request.getRequestURI())){
            response.getWriter().print("还没添加url权限");
        }else{
            result = true;
        }
        return result;
    }
}
