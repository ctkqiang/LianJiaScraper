<div align="center">
    <img src="https://www.logosc.cn/uploads/output/2024/11/13/ce17ccb7503e4482e6ca2b2b0aff4061.jpg" alt="链家爬虫" width="100%" style="max-width: 800px;" />
</div>

# 链家房源数据爬虫

## 项目简介

这是一个基于Spring Boot框架开发的链家房源数据爬虫系统。本项目致力于为用户提供一个便捷、高效的房源数据采集解决方案。通过自动化爬取链家网站的房源信息，系统能够实时获取各个城市的房源详情，包括房屋价格、位置、面积、户型等关键信息。

项目采用现代化的Web界面设计，支持灵活的城市筛选，用户可以轻松查看和分析不同区域的房源数据。同时，系统提供多种数据导出格式（Excel、CSV），方便用户进行后续的数据分析和处理。无论是普通用户查询房源信息，还是研究人员进行房地产市场分析，本系统都能提供有力的数据支持。

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

## API 接口说明
```bash
- 测试接口：GET `/api/test`
- 获取房源数据：GET `/api/house`
- 获取房源JSON数据：GET `/api/house/json/{province}`
- 可视化展示：GET `/api/show`
- 导出 CSV：GET `/api/house/export/csv`
- 导出 Excel：GET `/api/house/export/excel`
```
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
方式一：直接运行 JAR 文件
```bash
java -jar target/lianjiascraper-0.0.1-SNAPSHOT.jar
```
方式二：使用 Maven Spring Boot 插件运行

```bash
mvn spring-boot:run
```

### 使用说明

1. 访问 Web 界面：http://localhost:8080
2. API 测试：http://localhost:8080/api/test
3. 数据可视化：http://localhost:8080/api/show
4. 获取房源数据：http://localhost:8080/api/house
5. JSON格式数据：http://localhost:8080/api/house/json/{province}
6. 导出CSV：http://localhost:8080/api/house/export/csv
7. 导出Excel：http://localhost:8080/api/house/export/excel

### 演示界面

<div align="center">
    <img src="./demo/截屏2025-02-10 上午2.29.33.png" alt="演示界面" width="100%" style="max-width: 800px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);" />
    <p align="center" style="margin-top: 8px; color: #666;">
        链家房源数据爬虫系统界面展示
    </p>

<img src="./demo/截屏2025-02-10 下午10.01.48.png" alt="演示界面" width="100%" style="max-width: 800px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);" />

    
<img src="./demo/截屏2025-02-10 下午7.26.10.png" alt="演示界面" width="100%" style="max-width: 800px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);" />
    
<img src="./demo/截屏2025-02-10 下午7.26.22.png" alt="演示界面" width="100%" style="max-width: 800px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);" />

<img src="./demo/截屏2025-02-10 下午7.26.33.png" alt="演示界面" width="100%" style="max-width: 800px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);" />


</div>

## API 接口说明

- 获取房源数据：GET `/api/house?province={城市名}`
- 导出 CSV：GET `/api/house/export/csv?province={城市名}`
- 导出 Excel：GET `/api/house/export/excel?province={城市名}`
- 数据可视化：GET `/api/show?province={城市名}`
- 获取JSON数据：GET `/api/house/json/{province}`
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


## 🌟 开源项目赞助计划

### 用捐赠助力发展

感谢您使用本项目！您的支持是开源持续发展的核心动力。  
每一份捐赠都将直接用于：  
✅ 服务器与基础设施维护  
✅ 新功能开发与版本迭代  
✅ 文档优化与社区建设

点滴支持皆能汇聚成海，让我们共同打造更强大的开源工具！

---

### 🌐 全球捐赠通道

#### 国内用户

<div align="center" style="margin: 40px 0">

<div align="center">
<table>
<tr>
<td align="center" width="300">
<img src="https://github.com/ctkqiang/ctkqiang/blob/main/assets/IMG_9863.jpg?raw=true" width="200" />
<br />
<strong>🔵 支付宝</strong>
</td>
<td align="center" width="300">
<img src="https://github.com/ctkqiang/ctkqiang/blob/main/assets/IMG_9859.JPG?raw=true" width="200" />
<br />
<strong>🟢 微信支付</strong>
</td>
</tr>
</table>
</div>
</div>

#### 国际用户

<div align="center" style="margin: 40px 0">
  <a href="https://qr.alipay.com/fkx19369scgxdrkv8mxso92" target="_blank">
    <img src="https://img.shields.io/badge/Alipay-全球支付-00A1E9?style=flat-square&logo=alipay&logoColor=white&labelColor=008CD7">
  </a>
  
  <a href="https://ko-fi.com/F1F5VCZJU" target="_blank">
    <img src="https://img.shields.io/badge/Ko--fi-买杯咖啡-FF5E5B?style=flat-square&logo=ko-fi&logoColor=white">
  </a>
  
  <a href="https://www.paypal.com/paypalme/ctkqiang" target="_blank">
    <img src="https://img.shields.io/badge/PayPal-安全支付-00457C?style=flat-square&logo=paypal&logoColor=white">
  </a>
  
  <a href="https://donate.stripe.com/00gg2nefu6TK1LqeUY" target="_blank">
    <img src="https://img.shields.io/badge/Stripe-企业级支付-626CD9?style=flat-square&logo=stripe&logoColor=white">
  </a>
</div>

---

### 📌 开发者社交图谱

#### 技术交流

<div align="center" style="margin: 20px 0">
  <a href="https://github.com/ctkqiang" target="_blank">
    <img src="https://img.shields.io/badge/GitHub-开源仓库-181717?style=for-the-badge&logo=github">
  </a>
  
  <a href="https://stackoverflow.com/users/10758321/%e9%92%9f%e6%99%ba%e5%bc%ba" target="_blank">
    <img src="https://img.shields.io/badge/Stack_Overflow-技术问答-F58025?style=for-the-badge&logo=stackoverflow">
  </a>
  
  <a href="https://www.linkedin.com/in/ctkqiang/" target="_blank">
    <img src="https://img.shields.io/badge/LinkedIn-职业网络-0A66C2?style=for-the-badge&logo=linkedin">
  </a>
</div>

#### 社交互动

<div align="center" style="margin: 20px 0">
  <a href="https://www.instagram.com/ctkqiang" target="_blank">
    <img src="https://img.shields.io/badge/Instagram-生活瞬间-E4405F?style=for-the-badge&logo=instagram">
  </a>
  
  <a href="https://twitch.tv/ctkqiang" target="_blank">
    <img src="https://img.shields.io/badge/Twitch-技术直播-9146FF?style=for-the-badge&logo=twitch">
  </a>
  
  <a href="https://github.com/ctkqiang/ctkqiang/blob/main/assets/IMG_9245.JPG?raw=true" target="_blank">
    <img src="https://img.shields.io/badge/微信公众号-钟智强-07C160?style=for-the-badge&logo=wechat">
  </a>
</div>

---

🙌 感谢您成为开源社区的重要一员！  
💬 捐赠后欢迎通过社交平台与我联系，您的名字将出现在项目致谢列表！
