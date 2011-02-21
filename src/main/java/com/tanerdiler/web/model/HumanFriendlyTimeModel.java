package com.tanerdiler.web.model;

import java.util.Calendar;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import com.tanerdiler.util.DateTimeUtil;
import com.tanerdiler.util.HumanFriendlyTime;

public class HumanFriendlyTimeModel extends StringResourceModel {

	private static final long serialVersionUID = 6548602720867824520L;

	public HumanFriendlyTimeModel(IModel<Calendar> model) {
		super("humanfrienlytime.${type}", new Model<HumanFriendlyTime>(
				DateTimeUtil.getHumanFriendlyTime(model.getObject())));
	}

	@Override
	public String toString() {
		
		return super.toString();
	}

}
