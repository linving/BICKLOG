package com.tanerdiler.bpo;

import java.util.ArrayList;
import java.util.List;

import com.tanerdiler.model.view.TagValueWithArticleCount;

public class TagBPO {
	
	public static List<String> getTagLabelsWithEntityCount(List<TagValueWithArticleCount> entityList){
		List<String> labels = new ArrayList<String>();
		for(TagValueWithArticleCount entity : entityList){
			labels.add(entity.getLabel());
		}
		return labels;
	}
}
