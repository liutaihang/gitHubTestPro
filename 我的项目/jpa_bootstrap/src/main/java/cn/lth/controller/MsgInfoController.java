package cn.lth.controller;

import cn.lth.dto.MsgInfo;
import cn.lth.service.MsgInfoService;
import com.alibaba.fastjson.JSONObject;
import com.google.common.hash.HashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/msg/")
public class MsgInfoController {

    @Autowired
    private MsgInfoService service;

    @PostMapping("send")
    public MsgInfo save(@RequestBody MsgInfo msgInfo){
        return service.save(msgInfo);
    }

    @GetMapping("securityToken")
    public String wecatToken(HttpServletRequest req){
        String signature = req.getParameter("signature");
        String s = JSONObject.toJSONString(req.getParameterMap());
        JSONObject res = JSONObject.parseObject(s);
        res.put("token", "0dd4b5731df8fe5e84b7f723d61362dc");
        List list = new ArrayList<String>(){{
            add( res.getString("timestamp"));
            add("0dd4b5731df8fe5e84b7f723d61362dc");
            add(res.getString("nonce"));
        }};
        list.sort(null);
        String encode = new BASE64Encoder().encode(JSONObject.toJSONString(list).getBytes());
        System.out.println(encode + " |  " + signature);
        return res.getJSONArray("echostr").getString(0);
    }
}
