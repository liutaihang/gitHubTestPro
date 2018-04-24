package cn.lth.controller;

import cn.lth.base.BaseController;
import cn.lth.dao.DemoDao;
import cn.lth.dto.DemoDto;
//import net.minidev.json.JSONArray;
import cn.lth.service.DemoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private Gson gson = new GsonBuilder().create();

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

    @GetMapping("/page/{page}/{size}")
    public void paginationData(HttpServletResponse response, @PathVariable Integer page,@PathVariable Integer size) throws IOException {
        Pageable pageable = new PageRequest(page, size);
        List<DemoDto> demoDtos = demoService.paginationList(pageable);
        Map<String, Object> data = new HashMap<>();
        data.put("data",demoDtos);
        data.put("size", demoService.findAllNumber().toString());
        print(response, gson.toJson(data));
    }

}
