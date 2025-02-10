package cn.ctkqiang.lianjiascraper.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

/**
 * Web页面控制器
 * 
 * 处理网站主页面请求，展示房源列表数据。
 * 支持城市筛选功能，默认展示北京地区房源。
 * 
 * 主要功能：
 * 1. 处理首页请求
 * 2. 展示房源列表
 * 3. 处理城市筛选
 * 4. 错误处理
 * 
 * @author 钟智强
 * @version 1.0
 */
@Controller
public class WebController {
    /** 爬虫服务实例 */
    private final Scrapper scrapper;

    /**
     * 构造函数
     * 初始化爬虫服务实例
     */
    public WebController() {
        this.scrapper = new Scrapper();
    }

    /**
     * 处理首页请求
     * 获取并展示房源列表数据
     * 
     * @param province 城市名称，默认为"rs北京"
     * @param model    Spring MVC模型对象
     * @return 首页视图名称
     */
    @GetMapping("/")
    public String index(@RequestParam(required = false, defaultValue = "rs北京") String province, Model model) {
        try {
            model.addAttribute("houses", scrapper.scrapeLianjia(province));
            model.addAttribute("province", province);
        } catch (IOException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "index";
    }
}