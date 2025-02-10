package cn.ctkqiang.lianjiascraper.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

/**
 * 自定义错误处理控制器
 * 
 * 处理应用程序的错误响应，提供友好的错误提示。
 * 实现ErrorController接口，自定义错误页面展示。
 * 
 * 主要功能：
 * 1. 处理404页面未找到错误
 * 2. 返回友好的错误信息
 * 3. 统一错误响应格式
 * 
 * @author 钟智强
 * @version 1.0
 */
@RestController
public class CustomErrorController implements ErrorController {

    /**
     * 处理错误请求
     * 返回404错误响应
     * 
     * @return 错误响应实体
     */
    @RequestMapping("/error")
    public ResponseEntity<String> handleError() {
        return ResponseEntity
                .status(404)
                .body("页面未找到，请检查URL后重试。");
    }
}