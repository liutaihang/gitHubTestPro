package cn.lth.base;

import cn.lth.contant.UserEm;
import cn.lth.dto.SysUser;
import cn.lth.util.DemoException;
import cn.lth.util.PropertiesUtils;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PropertiesUtils propertiesUtils = PropertiesUtils.getInstance();

    public void print(HttpServletResponse response, Object object) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(object);
    }

    public void verifyBind(BindingResult bindingResult) throws DemoException {
        if (bindingResult.hasErrors()) {
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                throw new DemoException(propertiesUtils.get(objectError.getDefaultMessage()));
            }
        }
    }

    public void saveSesseionAtr(HttpServletRequest request, SysUser userinfo) {
        HttpSession session = request.getSession();
        session.setAttribute(UserEm.USER_INFO.name() + "-" + userinfo.getUserUuid(), userinfo);
    }

    public void renderObj(HttpServletResponse response, Object data){
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(new Gson().toJson(data));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            writer.flush();
            writer.close();
        }
    }
}
