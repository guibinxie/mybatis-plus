package com.suneee.controller;

import com.suneee.model.IndexField;
import com.suneee.model.Item;
import com.suneee.service.ItemService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/findall")
    @ResponseBody
    public List<Item> findAll(){
        List<Item> itemList = itemService.findAll("xiuwen");
       /* Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("User");
        final Row row = sheet.createRow(0);
        Map<Integer,Field> map = new HashMap<>();
        Field[] declaredFields = Item.class.getDeclaredFields();//获得全部field
        int r =0;
        for (Field field:declaredFields) {

                if(field.isAnnotationPresent(IndexField.class)){//获得有注解的字段
                    IndexField indexField = field.getAnnotation(IndexField.class);
                    map.put(indexField.index(), field);
                    row.createCell(r).setCellValue(field.getName());
                    r++;

                }
        }

        for (int i = 0; i <itemList.size() ; i++) {
            final Item item = itemList.get(i);
            Row sheetRow = sheet.createRow(i + 1);
            for (int j= 0;j<map.size();j++){
                sheetRow.createCell(j).setCellValue("");
            }

        }
        try {
            //路径需要存在
            FileOutputStream fos = new FileOutputStream("D:\\newExcel.xls");
            workbook.write(fos);
            fos.close();
            workbook.close();
            System.out.println("写数据结束！");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

        return itemList;

    }


    @RequestMapping("/save")
    @ResponseBody
    public Object save(){
        int i  = itemService.save();
        return i;

    }


    /*private  Object getFieldValue(Item item ,Field field){
        field.
        return null;
    }*/
    @RequestMapping("/item")
    @ResponseBody
    public  String setRedisKey(@RequestParam("key")String key){
        String setRedisKey = itemService.setRedisKey(key);
        return setRedisKey;
    }


    @RequestMapping("/getRediskey")
    @ResponseBody
    public  Object getRedisKey(@RequestParam("key")String key){
        Object setRedisKey = itemService.getRedisKey(key);
        return setRedisKey;
    }


    @RequestMapping("/")
    @ResponseBody
    public Object getItem(@PathVariable("key") String key) {

       Object o = itemService.getItem(key);
        return  o;
    }




}
