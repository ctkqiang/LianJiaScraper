package cn.ctkqiang.lianjiascraper.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

import cn.ctkqiang.lianjiascraper.models.House;

@Controller
@RequestMapping("/api")
public class Api {
    private final Scrapper scrapper;

    public Api() {
        this.scrapper = new Scrapper();
    }

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

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("API is working!");
    }

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

    @GetMapping("/api/house/export/excel")
    public ResponseEntity<Resource> exportExcel(@RequestParam(defaultValue = "rs北京") String province) {
        try {
            List<House> houses = scrapper.scrapeLianjia(province);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("房源数据");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("标题");
            headerRow.createCell(1).setCellValue("价格");
            headerRow.createCell(2).setCellValue("位置");
            headerRow.createCell(3).setCellValue("链接");

            // Create data rows
            int rowNum = 1;
            for (House house : houses) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(house.getTitle());
                row.createCell(1).setCellValue(house.getPrice());
                row.createCell(2).setCellValue(house.getLocation());
                row.createCell(3).setCellValue(house.getUrl());
            }

            // Auto-size columns
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
}
