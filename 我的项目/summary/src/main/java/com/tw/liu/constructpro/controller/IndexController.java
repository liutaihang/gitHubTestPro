package com.tw.liu.constructpro.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.tw.liu.constructpro.JsonUtils.CreationUtils;
import com.tw.liu.constructpro.entity.Book;
import com.tw.liu.constructpro.service.BookService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.reflect.misc.ReflectUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("index")
public class IndexController {

    private BookService bookService;

    public IndexController(BookService bookService) {
        this.bookService = bookService;
    }


    @RequestMapping("")
    public String index(){
        return "views/index";
    }

    @RequestMapping("token")
    public ResponseEntity token(String token){
        return ResponseEntity.ok("ok");
    }

    @RequestMapping("socket")
    public ResponseEntity<String> socket(@RequestBody(required = false) Map data, HttpServletRequest request){
        System.out.println(data);
        return ResponseEntity.ok(data.toString());
    }

    @RequestMapping("search")
    @ResponseBody
    public JSONArray search(String search){
        List<Book> search1 = bookService.search(search);
        return JSONArray.parseArray(search1.toString());
    }

    @RequestMapping("export")
    public void exportExcel(HttpServletResponse response) throws IOException {
        List<Book> books = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            books.add(new Book(i + "", "value" + i, "bookNo" + i));
        }
        List<String> tes = Lists.newArrayList();
        tes.add("id");
        tes.add("value");
        tes.add("bookno");

        HSSFWorkbook wb = getExportExcel(tes, books);

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=transaction.xls");
        OutputStream ouputStream = response.getOutputStream();
        wb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }


    public HSSFWorkbook getExportExcel(List<String> header, List<?> values){
        HSSFWorkbook hb = new HSSFWorkbook();
        HSSFSheet sheet = hb.createSheet("无文本信息标准");
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < header.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(header.get(i));
        }

        for (int i = 0; i < values.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);

            Class<?> clazz = ((Object) values.get(i)).getClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (int j = 0; j < declaredFields.length; j++) {
                HSSFCell cell = row1.createCell(j);
                cell.setCellValue(CreationUtils.invokeGetter(declaredFields[j].getName(), values.get(i), clazz).toString());
            }
        }
        return hb;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(){
        return "views/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String loginV(){
        return "";
    }
}
