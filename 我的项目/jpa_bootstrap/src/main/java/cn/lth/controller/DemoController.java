package cn.lth.controller;

import cn.lth.base.BaseController;
import cn.lth.dto.DemoDto;
import cn.lth.service.DemoService;
import cn.lth.util.DemoException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import net.minidev.json.JSONArray;

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
    public ResponseEntity<DemoDto> demo(@Valid DemoDto demoDto, BindingResult bindingResult) throws DemoException {
        verifyBind(bindingResult);
        //传入前段
        return  new ResponseEntity<>(demoService.save(demoDto), HttpStatus.OK);
    }

    @GetMapping("/viewData")
    public ResponseEntity<List<DemoDto>> viewData(){
        List<DemoDto> demoDtos = demoService.findAll();
        return new ResponseEntity<>(demoDtos, HttpStatus.OK);
    }

    @GetMapping("/page/{page}/{size}")
    public ResponseEntity<Map<String, Object>> paginationData(@PathVariable Integer page,@PathVariable Integer size){
        Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id"));
        List<DemoDto> demoDtos = demoService.paginationList(pageable);
        Map<String, Object> data = new HashMap<String, Object>(){
            {put("data",demoDtos);put("size", demoService.findAllNumber().toString());}
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
    public ResponseEntity< Map<String, Object>> del(@PathVariable Integer id){
        demoService.delDemo(id);
        Map<String, Object> map = new HashMap<String, Object>(){
            {put("msg", "删除成功!");}
        };
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
