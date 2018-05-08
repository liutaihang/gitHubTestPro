package cn.lth.base;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author liutaihang
 * @version : 1.0
 * @Date : Create in 10:08 2018/4/11
 * @Description : ${TODO}
 */
public class BaseController {

    public void print(HttpServletResponse response, Object object) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(object);
    }

    public void verifyBind(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            for(ObjectError objectError : bindingResult.getAllErrors()){
                throw new RuntimeException(objectError.getDefaultMessage());
            }
        }
    }
}
