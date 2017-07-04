package com.project.spider.factory;

import java.util.List;

import com.project.spider.model.SSCRepo;
import com.project.spider.model.list.SSCRepoOriginalList;
/**
 * 
 * @author bjluzhangqing
 *
 */
public interface SSCRepoFactory {
	public List<SSCRepo> getSSCRepoList(SSCRepoOriginalList sscRepoOriginalList);
}
