package com.project.spider.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class SpiderPageProcessor implements PageProcessor{
	
	
	 // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(1).setSleepTime(1000).setTimeOut(3000);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        // 部分二：定义如何抽取页面信息，并保存下来
//    	System.out.println(page.getRawText());
//        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        page.putField("name", page.getHtml().xpath("//div[@id='openlist']/ul/li/text()").toString());
        Object grapData = page.getResultItems().get("name");
		if (grapData == null) {
            //skip this page
            page.setSkip(true);
        }
		page.getHtml().xpath("//div[@id='openlist']");
		System.out.println(page.getHtml().xpath("//div[@id='openlist']"));
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

        // 部分三：从页面发现后续的url地址来抓取
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new SpiderPageProcessor())
                //从"https://github.com/code4craft"开始抓
                .addUrl("http://www.cqcp.net/game/ssc/")
                .addPipeline(new ConsolePipeline())
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
	}
	
}
