package cn.lth.util;

import cn.lth.contant.UserEm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.stream.Stream;

/**
 * @author liutaihang
 * @version : 1.0
 * @Date : Create in 17:55 2018/4/13
 * @Description : ${TODO}
 */
@Component
public class VerifyUtil {

    @Value("${visit.path}")
    private String [] visitPaths;

    public boolean verifyURI(String currentURI){
        AntPathMatcher andMatcher = new AntPathMatcher();
//        boolean result = true;
//        for (String uri : visitPaths)
//            if (!andMatcher.match(uri, currentURI)) {
//                result = false;
//                break;
//            }
//
//        return result;
//        if("/error".equals(currentURI)) return false;
        return Stream.of(visitPaths).anyMatch(url -> andMatcher.match(url, currentURI));
    }

    public boolean hasLogin(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null ){
            return true;
        }
        String s = session.getId();
        Object attribute = session.getAttribute(UserEm.USER_INFO.name() + "-" + s);
        return ObjectUtils.isEmpty(attribute);
    }

    public boolean turnLogin(HttpServletRequest request, String currentURI){
        return (!hasLogin(request) || verifyURI(currentURI));
//        && !verifyURI(currentURI)
    }
}
