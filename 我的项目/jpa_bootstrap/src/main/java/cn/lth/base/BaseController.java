package cn.lth.base;

import cn.lth.contant.userEm;
import cn.lth.dto.UserDemo;
import cn.lth.util.DemoException;
import cn.lth.util.Message_;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author liutaihang
 * @version : 1.0
 * @Date : Create in 10:08 2018/4/11
 * @Description : ${TODO}
 */
@Slf4j
public class BaseController {

    @Autowired
    private Message_ message_;

    public void print(HttpServletResponse response, Object object) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(object);
    }

    public void verifyBind(BindingResult bindingResult) throws DemoException {
        if (bindingResult.hasErrors()) {
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                throw new DemoException(message_.get(objectError.getDefaultMessage()));
            }
        }
    }

    public void saveSesseionAtr(HttpServletRequest request, UserDemo userinfo) {
        HttpSession session = request.getSession();
        session.setAttribute(userEm.USER_INFO.name(), userinfo);
    }
}
