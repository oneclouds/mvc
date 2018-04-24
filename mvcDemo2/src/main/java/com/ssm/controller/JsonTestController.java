package com.ssm.controller;

import com.ssm.po.Items;
import com.ssm.po.ItemsCustom;
import com.ssm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/4/10.
 */

@Controller
public class JsonTestController {
    @Autowired
    private   ItemService itemService;

    //    请求json串（商品信息），输出json（商品信息）
//    @RequestBody将请求的商品信息的json串转成itemsCustom对象
//    @ResponseMapping 将itemsCustom转成json对象
    @RequestMapping("/requestJson")
    public @ResponseBody  ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom) {
//     @ResponseBody将itemsCustom转成json输出
        return itemsCustom;
    }


//    请求key/value，输出json
    @RequestMapping("/responseJson")
    public @ResponseBody ItemsCustom responseJson(ItemsCustom itemsCustom){

//        @ResponseBody将itemsCustom转成json输出
        return itemsCustom;
    }


//    查询商品信息，输出json
//     /itemsView/{id} 里面的{id}表示将这个位置的参数参数@PathVariable指定名中
    @RequestMapping("/itemsView/{id}")
    public @ResponseBody ItemsCustom itemsView(@PathVariable("id") Integer id) throws Exception {
//        调用service查询商品信息
        ItemsCustom itemsCustom = itemService.findItemsById(id);
        return itemsCustom;
    }

}
