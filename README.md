<div align="center">
    <img src="https://www.logosc.cn/uploads/output/2024/11/13/ce17ccb7503e4482e6ca2b2b0aff4061.jpg" alt="链家爬虫" width="100%" style="max-width: 800px;" />
</div>

# 链家房源数据爬虫

## 项目简介
这是一个基于Spring Boot的链家房源数据爬虫项目，可以自动抓取链家网站的房源信息，并提供Web界面展示和数据导出功能。

## 功能特点

- 🏠 支持多城市房源数据爬取
- 📊 美观的 Web 界面展示
- 📑 支持导出 Excel 和 CSV 格式
- 🔍 灵活的城市搜索功能
- 🖼️ 展示房源图片和详细信息

## 技术栈

- 后端：Spring Boot 2.7.18
- 爬虫：JSoup
- 前端：Thymeleaf + Tailwind CSS
- 数据导出：Apache POI

## 快速开始

### 环境要求

- JDK 11+
- Maven 3.6+

### 安装步骤

1. 克隆项目

```bash
git clone https://github.com/ctkqiang/lianjiascraper.git
```

2. 进入项目目录

```bash
cd lianjiascraper
```

3. 编译项目

```bash
mvn clean package
```

4. 运行项目

```bash
java -jar target/lianjiascraper-0.0.1-SNAPSHOT.jar
```

### 使用说明

1. 访问 Web 界面：http://localhost:8080
2. 在搜索框输入城市名称（例如：北京）
3. 点击搜索按钮获取房源数据
4. 可以点击导出按钮将数据导出为 Excel 或 CSV 格式

### 演示界面

<div align="center">
    <img src="./demo/截屏2025-02-10 上午2.29.33.png" alt="演示界面" width="100%" style="max-width: 800px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);" />
    <p align="center" style="margin-top: 8px; color: #666;">
        链家房源数据爬虫系统界面展示
    </p>
</div>

## API 接口说明

- 获取房源数据：GET `/api/house?province={城市名}`
- 导出 CSV：GET `/api/house/export/csv?province={城市名}`
- 导出 Excel：GET `/api/house/export/excel?province={城市名}`
- 测试接口：GET `/api/test`

## 项目结构

```
src/main/java/cn/ctkqiang/lianjiascraper/
├── LianjiascraperApplication.java    # 应用程序入口
├── controllers/                      # 控制器
│   ├── Api.java                     # API接口控制器
│   ├── Scrapper.java               # 爬虫核心逻辑
│   └── WebController.java          # Web页面控制器
├── models/                          # 数据模型
│   └── House.java                  # 房源信息模型
└── resources/                       # 资源文件
    └── templates/                   # 模板文件
        └── index.html              # 主页面
```

## 注意事项

- 请合理控制爬取频率，避免对目标网站造成压力
- 仅用于学习和研究目的，请勿用于商业用途
- 遵守目标网站的 robots.txt 规则

## 后续优化计划

- [ ] 添加数据库支持
- [ ] 实现定时任务
- [ ] 添加代理池
- [ ] 优化爬虫性能
- [ ] 添加更多数据分析功能

## 许可证

本项目采用 **木兰宽松许可证 (Mulan PSL)** 进行许可。  
有关详细信息，请参阅 [LICENSE](LICENSE) 文件。

[![License: Mulan PSL v2](https://img.shields.io/badge/License-Mulan%20PSL%202-blue.svg)](http://license.coscl.org.cn/MulanPSL2)

### 个人捐赠支持

如果您认为该项目对您有所帮助，并且愿意个人捐赠以支持其持续发展和维护，🥰 我非常感激您的慷慨。
您的捐赠将帮助我继续改进和添加新功能到该项目中。 通过 financial donation，您将有助于确保该项目保持免
费和对所有人开放。即使是一小笔捐款也能产生巨大的影响，也是对我个人的鼓励。

### 国内支付方式

<div align="center">
<table>
<tr>
<td align="center" width="300">
<img src="https://github.com/ctkqiang/ctkqiang/blob/main/assets/IMG_9863.jpg?raw=true" width="200" />
<br />
<strong>微信支付</strong>
</td>
<td align="center" width="300">
<img src="https://github.com/ctkqiang/ctkqiang/blob/main/assets/IMG_9859.JPG?raw=true" width="200" />
<br />
<strong>支付宝</strong>
</td>
</tr>
</table>
</div>

### 国际支付渠道

<div align="center">

[![支付宝](https://img.shields.io/badge/支付宝-捐赠-00A1E9?style=for-the-badge&logo=alipay&logoColor=white)](https://qr.alipay.com/fkx19369scgxdrkv8mxso92)
[![Ko-fi](https://img.shields.io/badge/Ko--fi-赞助-FF5E5B?style=for-the-badge&logo=ko-fi&logoColor=white)](https://ko-fi.com/F1F5VCZJU)
[![PayPal](https://img.shields.io/badge/PayPal-支持-00457C?style=for-the-badge&logo=paypal&logoColor=white)](https://www.paypal.com/paypalme/ctkqiang)
[![Stripe](https://img.shields.io/badge/Stripe-捐赠-626CD9?style=for-the-badge&logo=Stripe&logoColor=white)](https://donate.stripe.com/00gg2nefu6TK1LqeUY)

</div>

### 关注作者

<div align="center">

#### 专业平台

[![GitHub](https://img.shields.io/badge/GitHub-开源项目-24292e?style=for-the-badge&logo=github)](https://github.com/ctkqiang)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-职业经历-0077b5?style=for-the-badge&logo=linkedin)](https://www.linkedin.com/in/ctkqiang/)
[![Stack Overflow](https://img.shields.io/badge/Stack_Overflow-技术交流-f48024?style=for-the-badge&logo=stackoverflow)](https://stackoverflow.com/users/10758321/%e9%92%9f%e6%99%ba%e5%bc%ba)

#### 社交媒体

[![Facebook](https://img.shields.io/badge/Facebook-社交平台-1877F2?style=for-the-badge&logo=facebook)](https://www.facebook.com/JohnMelodyme/)
[![Instagram](https://img.shields.io/badge/Instagram-生活分享-E4405F?style=for-the-badge&logo=instagram)](https://www.instagram.com/ctkqiang)
[![Twitch](https://img.shields.io/badge/Twitch-直播频道-9146FF?style=for-the-badge&logo=twitch)](https://twitch.tv/ctkqiang)

[![](https://img.shields.io/badge/GitHub-项目仓库-24292F?style=for-the-badge&logo=github&logoColor=white)](https://github.com/ctkqiang)
[![](https://img.shields.io/badge/微信公众号-华佗AI-07C160?style=for-the-badge&logo=wechat&logoColor=white)](https://github.com/ctkqiang/ctkqiang/blob/main/assets/IMG_9245.JPG?raw=true)

</div>
