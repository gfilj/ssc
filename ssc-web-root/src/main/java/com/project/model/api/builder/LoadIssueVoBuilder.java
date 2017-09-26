package com.project.model.api.builder;
/**
 * 
 * @author goforit
 *
 */

import com.project.spider.model.Row;
import com.project.util.DateUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * 撤单/当前的采种类型
 * @author goforit
 *
 */
public class LoadIssueVoBuilder {

	private Row row = Row.getInstance();

	public static LoadIssueVoBuilder getInstance() {
		return new LoadIssueVoBuilder();
	}

	public LoadIssueVoBuilder setCurrentIssue(String current_issue)  {
		row.put("current_issue",current_issue);
		return this;
	}

	public LoadIssueVoBuilder setIssues(List<Row> issuesTemp) {
		List<Row> issues = new LinkedList<Row>();
		issuesTemp.forEach((v)->{
			Row row = Row.getInstance();
			row.put("issue",v.getString("issue"));
			row.put("wn_number",
					""+ v.getInt("digit5")
					+v.getInt("digit4")
					+v.getInt("digit3")
					+v.getInt("digit2")
					+v.getInt("digit1"));
			row.put("offical_time", DateUtils.getDateUnixTimeStamp(v.getDate("time")));
			issues.add(row);
		});
		row.put("issues",issues);
		return this;
	}


	public Row build() {
		return row;
	}
	
}
