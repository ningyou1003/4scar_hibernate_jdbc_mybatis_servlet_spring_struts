package com.emindsoft.zsj.activity.vo;

import java.util.List;

import com.emindsoft.zsj.activity.model.Activity;
import com.emindsoft.zsj.docNotice.model.Document;

public class ActivityVO {
	private Activity act;
	private List<Document> docList;
	
	public ActivityVO(Activity act, List<Document> docList) {
		super();
		this.act = act;
		this.docList = docList;
	}

	public Activity getAct() {
		return act;
	}

	public void setAct(Activity act) {
		this.act = act;
	}

	public List<Document> getDocList() {
		return docList;
	}

	public void setDocList(List<Document> docList) {
		this.docList = docList;
	}
	
}
