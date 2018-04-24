package com.ssm.controller;

import com.ssm.po.ItemsCustom;
import com.ssm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
//对url进行分类管理，可以在这里定义根路径，最终访问url是根路径+子路径
//例如：商品列表:/items/queryItems.action
//@RequestMapping("/items")

//限制http请求方法，可以是post和get
@RequestMapping(value = "/items",method = {RequestMethod.GET,RequestMethod.POST})
public class ItemsController {

    @Autowired
    private ItemService itemService;

    //@RequestMapping 实现queryItems方法和url进行映射，一个方法对应一个url
    //一般建议将url和方法写成一样
    @RequestMapping("/queryItems.action")
    public ModelAndView queryItems() throws Exception {

//        调用service查找数据库，查询商品列表
        List<ItemsCustom> itemsList = itemService.findItemList(null);

//        返回ModelAndView
        ModelAndView modelAndView = new ModelAndView();
//        相当于request的setAttribute，在jsp页面中通过itemsList获取数据
        modelAndView.addObject("itemsList", itemsList);
//        指定视图
       /* modelAndView.setViewName("/jsp/itemsList.jsp");*/
        // 下面的路径，因为在视图解析器中配置了前缀和后缀，所以不用写了
        modelAndView.setViewName("itemsList");
        return modelAndView;
    }
//    -------------------------------------------------
//    controller放回String类 型
    /*
  @RequestMapping("/queryItems.action")
  @RequestParam里面指定request传入参数名称和参数进行绑定
  通过required属性指定参数是否必须要传入
  通过defaultValue可以设置默认值，如果id参数没有传入，将默认值和形参绑定

    下面的方法中形参是model，当然也可以使HttpServletRequest等，见word文档默认支持的形参类型

    public String queryItems(Model model，@RequestParam（value="id",required=true,default=""）Integer items_id) throws Exception {
          调用service方法，查询上商品
        ItemsCustom itemsCustom = itemService.findItemsById(items_id);
        通过形参中的model将model数据传到页面
        相当于ModelAndView.addObject方法
        model.addAttribute("itemCustom", itemsCustom);

        return "items/editItem";
        下面是重 定向或者转发
        重定向
        return "redirect:queryItems.action";

        页面转发
        return "forward:queryItems.action";

}

-------------------------------------------------------
下面是绑定pojo类型
 public String editItems(HttpServeletRequest request，ItemsCustom itemsCustom） throw Exception{}

 itemsCustom是pojo类型，通过jsp页面进行绑定对应的pojo类型：
 <input type="text" name="price" value="${itemsCustom.price}"> 其中name属性的值必须与pojo属性名一致，这样就绑定了pojo类型：ItemsCustom
   */
//--------------------------------------------------------
//    商品信息修改页面
    @RequestMapping("/editItems.action")
    public  ModelAndView editItems() throws  Exception {
        //调用servic根据商品id查询商品信息
        ItemsCustom itemsCustom = itemService.findItemsById(1);
        //返回ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        //将商品信息放到model
        modelAndView.addObject("itemCustom", itemsCustom);
        //商品信息修改页面
        modelAndView.setViewName("items/editItem");

        return modelAndView;
    }

//    商品信息提交
@RequestMapping("/editItemsSubmit.action")
public  ModelAndView editItemsSubmit() throws  Exception {
    //调用servic更新商品信息，页面需要将商品信息传到此方法中
//    。。。。。。。。。。。。。。。
    //返回ModelAndView
    ModelAndView modelAndView = new ModelAndView();
    return modelAndView;
}
}
