package com.project.spider.pipeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.project.spider.factory.SSCRepoFactory;
import com.project.spider.model.SSCRepo;
import com.project.spider.model.list.SSCRepoOriginalList;
import com.project.spider.service.sscservice.SSCService;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

@Component
public class SSCPageModelPipeline implements PageModelPipeline<SSCRepoOriginalList> {
	@Autowired
	@Qualifier("TJSSCRepoFactory")
	private SSCRepoFactory sscRepoFactory;

	@Autowired
	private SSCService sSCService;

	public void setSscRepoFactory(SSCRepoFactory sscRepoFactory) {
		this.sscRepoFactory = sscRepoFactory;
	}

	@Override
	public void process(SSCRepoOriginalList sscRepoOriginalList, Task task) {

		List<SSCRepo> sscRepoList = sscRepoFactory.getSSCRepoList(sscRepoOriginalList);
		System.out.println(sscRepoList);
		for (SSCRepo sscRepo : sscRepoList) {
			try {
				sSCService.insertSelective(sscRepo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
