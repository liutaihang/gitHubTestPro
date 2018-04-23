package cn.lth.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

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
        boolean result = false;
        for (String uri : visitPaths) {
            result = andMatcher.match(uri, currentURI);
        }
        return Stream.of(visitPaths).anyMatch(url -> andMatcher.match(url, currentURI));
    }
}
