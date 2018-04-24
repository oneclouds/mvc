package com.ssm.controller;

import com.ssm.Exceptions.CustomException;
import com.ssm.po.ItemsCustom;
import com.ssm.po.ItemsQueryVo;
import com.ssm.service.ItemService;
import com.ssm.validation.ValidGroup1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
//对url进行分类管理，可以在这里定义根路径，最终访问url是根路径+子路径
//例如：商品列表:/items/queryItems.action
//@RequestMapping("/items")

//限制http请求方法，可以是post和get
@RequestMapping(value = "/items", method = {RequestMethod.GET, RequestMethod.POST})
public class ItemsController2 {

    @Autowired
    private ItemService itemService;


    //    按照jsp传来的商品名称进行查询
    @RequestMapping("/queryItems.action")
    public ModelAndView queryItems(HttpServletRequest request, ItemsQueryVo itemsQueryVo) throws Exception {

//        调用service查找数据库，查询商品列表
        List<ItemsCustom> itemsList = itemService.findItemList(itemsQueryVo);

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

    //    商品信息修改页面
    @RequestMapping("/editItems.action")
    public ModelAndView editItems() throws Exception {
        //调用servic根据商品id查询商品信息
        ItemsCustom itemsCustom = itemService.findItemsById(1);

//        判断商品是否为空，根据id没有查询到商品，抛出异常，提示用户商品信息不存在
//        这里就是系统自定义的抛出的异常
        if(itemsCustom==null){
            throw new CustomException("修改的商品信息不存在");
        }

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
    public ModelAndView editItemsSubmit() throws Exception {
        //调用servic更新商品信息，页面需要将商品信息传到此方法中
//    。。。。。。。。。。。。。。。
        //返回ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    //批量删除商品信息
    @RequestMapping("/deleteItems")
    public String deleteItems(Integer[] items_id) throws Exception {
//        调用servic方法实现删除商品信息
//        ，，，，，，
        return "success";

    }

    //    批量修改商品页面，将商品信息查询出来，在页面中可以编辑商品信息
    @RequestMapping("/editItemQuery")
    public ModelAndView editItemQuery(HttpServletRequest request, ItemsQueryVo itemsQueryVo) throws Exception {

//        调用service查找数据库，查询商品列表
        List<ItemsCustom> itemsList = itemService.findItemList(itemsQueryVo);
//        返回ModelAndView
        ModelAndView modelAndView = new ModelAndView();
//        相当于request的setAttribute，在jsp页面中通过itemsList获取数据
        modelAndView.addObject("itemsList", itemsList);
        modelAndView.setViewName("itemsList");
        return modelAndView;
    }

    //    批量修改商品提交
    @RequestMapping("/editItemsAllSubmit")
    public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo) throws Exception {
//        调用servic方法实现删除商品信息
//        ，，，，，，
        return "success";

    }


    //  校验信息
//    在需要校验的pojo前面添加@validated，在需要校验的pojo后添加BindingResult bindingResult
//    接收校验出错信息
//    注意：@Validated和BindingResult bindingResult是配对出现，并且形参顺序是固定的（一前一后）
//    其中@Validated(value ={ValidGroup1.class}指定使用ValidGroup1分组校验
//    @ModelAttribute可以指定pojo回显到页面在request的key
    @RequestMapping("/editItemsSubmit")
    public String editItems( Model model, HttpServletRequest request, @ModelAttribute("items") @Validated(value ={ValidGroup1.class} ) ItemsCustom itemsCustom, BindingResult bindingResult,MultipartFile items_pic  //接收图片文件
    ) throws Exception {

//        获取错误信息
        if (bindingResult.hasErrors()) {
//        输出错误信息
            List<ObjectError> allErrors = bindingResult.getAllErrors();
//        在控制台显示
            for (ObjectError objectError : allErrors) {
                System.out.println(objectError.getDefaultMessage());
            }

//        将错误信息传到页面
            model.addAttribute("allErrors", allErrors);

//         出现错误后，可以直接使用model将提交的pojo回显到页面
            model.addAttribute("items",itemsCustom);

//        出错重新DAO商品修改页面
            return "items/editItems";
        }

//        上传图片
//        原始名称
        String originalFileName = items_pic.getOriginalFilename();
        if(items_pic!=null &&originalFileName!=null&&originalFileName.length()>0){
//            存储图片的路径
//            String path = request.getSession().getServletContext().getRealPath("upload");
//            “/”指代项目根目录
//            request.getSession().getServletContext() 获取的是Servlet容器对象，相当于tomcat容器了。getRealPath("/") 获取实际路径，“/”指代项目根目录，所以代码返回的是项目在容器中的实际发布运行的根路径

//            这里直接写死服务器的存储路径,这里是指tomcat服务器里配置的存储路径
            String pic_path = "F:\\develop\\upload\\temp\\";
//            新的图片名称
            String newFileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
            File newFile = new File(pic_path+newFileName);
//            将内存中的数据写入硬盘
            items_pic.transferTo(newFile);
//            将图片名称写入到itemsCustom中，就是将图片文件写入到数据库中
            itemsCustom.setPic(newFileName);

        }

        return "sucess";
    }

//    商品分类：这里使用静态数据模拟===>这种用法很大，本类中是页面中获取查询条件的option中的值
    @ModelAttribute("itemtypes")
    public Map<String,String> itemtypes(){
        Map<String, String> itemtypes = new HashMap<String ,String>();
        itemtypes.put("101","xiaoming");
        itemtypes.put("102","lisi");
        return itemtypes;
    }

}
