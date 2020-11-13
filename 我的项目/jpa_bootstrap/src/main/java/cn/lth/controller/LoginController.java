package cn.lth.controller;

import cn.lth.base.BaseController;
import cn.lth.contant.UserEm;
import cn.lth.dto.DemoDto;
import cn.lth.dto.SysUser;
import cn.lth.service.DemoService;
import cn.lth.service.SysUserService;
import cn.lth.util.DemoException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * @author liutaihang
 * @version : 1.0
 * @Date : Create in 10:08 2018/4/11
 * @Description : ${TODO}
 */
@Slf4j
@Controller
@Api(tags = {"测试控制器"})
public class LoginController extends BaseController {

    @Autowired
    private DemoService demoService;

    @Autowired
    private SysUserService sysUserService;

    private Gson gson = new GsonBuilder().create();

    @GetMapping("/")
    public String index(){
        return "content";
    }

    @GetMapping("/login")
    @ApiOperation(value = "登录页面")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录参数请求")
    public String validateLogin(SysUser sysUser, HttpServletRequest request, HttpServletResponse resp){
        if(!ObjectUtils.isEmpty(sysUser)){
            String s = request.getSession().getId();
            sysUser.setUserUuid(s);
            resp.setHeader(UserEm.USER_UUID.name(), s);
            saveSesseionAtr(request, sysUser);
            return "redirect:/?sessionId=" + s;
        }
        return "redirect:/login";
    }

    @GetMapping("/loginout")
    @ApiOperation(value = "登出请求")
    public String loginOut(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        session.invalidate();
        return "login";
    }

    @PostMapping("regist")
    @ApiOperation(value = "注册")
    public String register(@RequestBody SysUser user, HttpServletResponse response){
        if(!sysUserService.existsUserName(user.getUsername())){
            renderObj(response, sysUserService.insert(user));
        }else{
            renderObj(response, "");
        }
        return "/";
    }

    @GetMapping("/webs")
    public String webSocket(){
        return "webSocket";
    }

    @PostMapping("/demo")
    @ApiOperation(value = "demo")
    public ResponseEntity<DemoDto> demo(@Valid DemoDto demoDto, BindingResult bindingResult) throws DemoException {
//        throw new RuntimeException("");
        verifyBind(bindingResult);
        //传入前段
        return  new ResponseEntity<>(demoService.save(demoDto), HttpStatus.OK);
    }

    @GetMapping("/viewData")
    @ApiOperation(value = "viewData")
    public ResponseEntity<List<DemoDto>> viewData(String data){
        log.error(data);
        List<DemoDto> demoDtos = demoService.findAll();
        return new ResponseEntity<>(demoDtos, HttpStatus.OK);
    }

    @GetMapping("/page/{page}/{size}")
    public ResponseEntity<Map<String, Object>> paginationData(@PathVariable Integer page,@PathVariable Integer size){
        Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id"));
        List<DemoDto> demoDtos = demoService.paginationList(pageable);
        Map<String, Object> data = new HashMap<String, Object>(){
            {put("data",demoDtos);put("size", demoService.findAllNumber());}
        };
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity<Map<String, Object>> updateDemo(DemoDto demoDto, BindingResult bindingResult) throws DemoException {
        verifyBind(bindingResult);
        demoService.updateDemo(demoDto);
        Map<String, Object> map = new HashMap<String, Object>(){
            {put("msg", "修改成功!");put("data", demoDto);}
        };
        return  new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("del/{id}")
    public ResponseEntity< Map<String, Object>> del(@PathVariable String id){
        demoService.delDemo(id);
        Map<String, Object> map = new HashMap<String, Object>(){
            {put("msg", "删除成功!");}
        };
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("testd")
    @ResponseBody
    public String get(HttpServletRequest request, HttpServletResponse response
            ,@RequestParam(value = "file", required = false) MultipartFile file){
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(parameterMap.size());
        return "";
    }
}
