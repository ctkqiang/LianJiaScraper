package cn.ctkqiang.lianjiascraper.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import cn.ctkqiang.lianjiascraper.models.House;

/**
 * 链家房源数据API控制器
 * 
 * 提供RESTful API接口，用于获取和导出房源数据。
 * 支持多种数据格式：JSON、CSV、Excel
 * 
 * 主要功能：
 * 1. 获取房源列表数据
 * 2. 数据可视化展示
 * 3. 导出CSV格式数据
 * 4. 导出Excel格式数据
 * 5. 提供JSON格式数据接口
 * 
 * @author 钟智强
 * @version 1.0
 */
@Controller
@RequestMapping("/api")
public class Api {
    /** 爬虫实例 */
    private final Scrapper scrapper;

    /**
     * 构造函数，初始化爬虫实例
     */
    public Api() {
        this.scrapper = new Scrapper();
    }

    /**
     * 获取房源列表
     * 支持按城市筛选数据
     * 
     * @param province 城市名称，默认为"rs北京"
     * @param model    Spring MVC模型对象
     * @return 视图名称
     */
    @GetMapping("/house")
    public String getHouses(@RequestParam(defaultValue = "rs北京") String province, Model model) {
        try {
            List<House> houses = scrapper.scrapeLianjia(province);
            model.addAttribute("houses", houses);
            model.addAttribute("province", province);
            return "index";
        } catch (IOException e) {
            model.addAttribute("error", e.getMessage());
            return "index";
        }
    }

    /**
     * 数据可视化展示接口
     * 提供图表化展示房源数据
     * 
     * @param province 城市名称，默认为"rs北京"
     * @param model    Spring MVC模型对象
     * @return 可视化视图名称
     */
    @GetMapping("/show")
    public String showVisualization(@RequestParam(defaultValue = "rs北京") String province, Model model) {
        try {
            List<House> houses = scrapper.scrapeLianjia(province);
            model.addAttribute("houses", houses);
            model.addAttribute("province", province);
            return "show";
        } catch (IOException e) {
            model.addAttribute("error", e.getMessage());
            return "show";
        }
    }

    /**
     * API测试接口
     * 用于验证API服务是否正常运行
     * 
     * @return API状态响应
     */
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("API is working!");
    }

    /**
     * 导出CSV格式数据
     * 将房源数据导出为CSV文件格式
     * 
     * @param province 城市名称，默认为"rs北京"
     * @return CSV文件下载响应
     */
    @GetMapping("/house/export/csv")
    public ResponseEntity<Resource> exportCsv(@RequestParam(defaultValue = "rs北京") String province) {

        try {
            List<House> houses = scrapper.scrapeLianjia(province);
            StringBuilder csv = new StringBuilder();
            csv.append("标题,价格,位置,链接\n");

            for (House house : houses) {
                csv.append(String.format("\"%s\",\"%s\",\"%s\",\"%s\"\n",
                        house.getTitle().replace("\"", "\"\""),
                        house.getPrice().replace("\"", "\"\""),
                        house.getLocation().replace("\"", "\"\""),
                        house.getUrl()));
            }

            ByteArrayResource resource = new ByteArrayResource(csv.toString().getBytes(StandardCharsets.UTF_8));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"houses.csv\"")
                    .contentType(MediaType.parseMediaType("text/csv;charset=UTF-8"))
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * 导出Excel格式数据
     * 将房源数据导出为Excel文件格式
     * 
     * @param province 城市名称，默认为"rs北京"
     * @return Excel文件下载响应
     */
    @GetMapping("/house/export/excel")
    public ResponseEntity<Resource> exportExcel(@RequestParam(defaultValue = "rs北京") String province) {
        try {
            int rowNum = 1;

            List<House> houses = scrapper.scrapeLianjia(province);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("房源数据");
            Row headerRow = sheet.createRow(0);

            headerRow.createCell(0).setCellValue("标题");
            headerRow.createCell(1).setCellValue("价格");
            headerRow.createCell(2).setCellValue("位置");
            headerRow.createCell(3).setCellValue("链接");

            for (House house : houses) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(house.getTitle());
                row.createCell(1).setCellValue(house.getPrice());
                row.createCell(2).setCellValue(house.getLocation());
                row.createCell(3).setCellValue(house.getUrl());
            }

            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();

            ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"houses.xlsx\"")
                    .contentType(MediaType
                            .parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    /**
     * 获取JSON格式数据
     * 提供房源数据的JSON格式接口
     * 
     * @param province 城市名称
     * @return JSON格式的房源数据
     */
    @GetMapping("/house/json/{province}")
    @ResponseBody
    public ResponseEntity<?> getHousesJson(@PathVariable String province) {
        try {
            String searchProvince = province != null ? province : "rs北京";
            List<House> houses = scrapper.scrapeLianjia(searchProvince);
            return ResponseEntity.ok(houses);
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
