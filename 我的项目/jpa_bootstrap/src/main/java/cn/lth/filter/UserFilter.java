//package cn.lth.filter;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class UserFilter extends UsernamePasswordAuthenticationFilter {
//
//    private String codeParameter = "code";
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        String username = this.obtainUsername(request);
//        String password = this.obtainPassword(request);
//        String code = this.obtainCode(request);
////        String caChecode = (String)request.getSession().getAttribute("VERCODE_KEY");
////        boolean flag = CodeValidate.validateCode(code,caChecode);
////        if(!flag){
////            throw new UsernameNotFoundException("验证码错误");
////        }
//        if(username == null) {
//            username = "";
//        }
//        if(password == null) {
//            password = "";
//        }
//        username = username.trim();
//        //通过构造方法实例化一个 UsernamePasswordAuthenticationToken 对象，此时调用的是 UsernamePasswordAuthenticationToken 的两个参数的构造函数
//        //其中 super(null) 调用的是父类的构造方法，传入的是权限集合，因为目前还没有认证通过，所以不知道有什么权限信息，这里设置为 null，然后将用户名和密码分别赋值给
//        // principal 和 credentials，同样因为此时还未进行身份认证，所以 setAuthenticated(false)。
//        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
//        //setDetails(request, authRequest) 是将当前的请求信息设置到 UsernamePasswordAuthenticationToken 中。
//        this.setDetails(request, authRequest);
//        //通过调用 getAuthenticationManager() 来获取 AuthenticationManager，通过调用它的 authenticate 方法来查找支持该
//        // token(UsernamePasswordAuthenticationToken) 认证方式的 provider，然后调用该 provider 的 authenticate 方法进行认证)。
//        return this.getAuthenticationManager().authenticate(authRequest);
//    }
//
//    protected String obtainCode(HttpServletRequest request) {
//        return request.getParameter(this.codeParameter);
//    }
//}
