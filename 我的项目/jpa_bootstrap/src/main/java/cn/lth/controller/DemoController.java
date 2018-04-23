package cn.lth.controller;

import cn.lth.base.BaseController;
import cn.lth.dao.DemoDao;
import cn.lth.dto.DemoDto;
//import net.minidev.json.JSONArray;
import cn.lth.service.DemoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author liutaihang
 * @version : 1.0
 * @Date : Create in 10:08 2018/4/11
 * @Description : ${TODO}
 */
@Controller
public class DemoController extends BaseController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/demo")
    public void demo(DemoDto demoDto, HttpServletResponse response) throws IOException {
        //传入前段
        print(response, demoService.save(demoDto));
    }

    @GetMapping("/viewData")
    public void viewData(HttpServletResponse response) throws IOException {
        List<DemoDto> demoDtos = demoService.findAll();
//        System.out.println(JSONArray.toJSONString(demoDtos));
        Gson gson = new GsonBuilder().create();
        String demoJson = gson.toJson(demoDtos);
        print(response, demoJson);
    }

}
