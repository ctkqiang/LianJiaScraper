package cn.ctkqiang.lianjiascraper.controllers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.ctkqiang.lianjiascraper.models.House;

/**
 * 链家网站爬虫核心类
 * 负责从链家网站抓取房源数据
 */
public class Scrapper {
    // 链家租房基础URL
    private static final String URL = "https://bj.lianjia.com/zufang/";

    /**
     * 爬取链家网站房源数据
     * @param Province 城市/地区名称
     * @return 房源列表
     * @throws IOException 网络请求异常
     */
    public List<House> scrapeLianjia(@NonNull String Province) throws IOException {
        // 设置请求头，模拟浏览器访问
        Document doc = Jsoup.connect(URL + Province)
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .get();

        // 选择所有房源列表项
        Elements listings = doc.select("div.content__list--item");
        List<House> houses = new ArrayList<>();

        // 遍历每个房源，提取信息
        for (Element listing : listings) {
            // 提取房源标题
            String title = listing.select("p.content__list--item--title a").text();
            // 提取房源价格
            String price = listing.select("span.content__list--item-price").text();
            // 提取房源位置
            String location = listing.select("p.content__list--item--des").text();
            // 提取房源图片URL
            String imageUrl = listing.select("a.content__list--item--aside img").attr("src");
            // 构建完整的房源详情页URL
            String url = "https://bj.lianjia.com" + listing.select("p.content__list--item--title a").attr("href");

            // 只添加有标题的房源
            if (!title.isEmpty()) {
                houses.add(new House(title, price, location, imageUrl, url));
            }
        }
        return houses;
    }
}
