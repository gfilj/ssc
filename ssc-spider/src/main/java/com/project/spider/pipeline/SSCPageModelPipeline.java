package com.project.spider.pipeline;

import com.project.spider.factory.SSCRepoFactory;
import com.project.spider.model.list.SSCRepoOriginalList;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

public class SSCPageModelPipeline implements PageModelPipeline<SSCRepoOriginalList> {
	
	private SSCRepoFactory sscRepoFactory;
	
	public void setSscRepoFactory(SSCRepoFactory sscRepoFactory) {
		this.sscRepoFactory = sscRepoFactory;
	}

	@Override
	public void process(SSCRepoOriginalList sscRepoOriginalList, Task task) {
		 System.out.println(sscRepoFactory.getSSCRepoList(sscRepoOriginalList));
		// into db
	}

}
