package com.emindsoft.zsj.culture.vo;

import java.util.List;

import com.emindsoft.zsj.base.attachment.model.Attachment;
import com.emindsoft.zsj.culture.model.Other;

public class OtherDetailVO {
	private Other other;
	private List<Attachment> attachment;
	
	public OtherDetailVO(Other other,List<Attachment> attachment) {
		this.other = other;
		this.attachment = attachment;
	}
	
	public List<Attachment> getAttachment() {
		return attachment;
	}

	public void setAttachment(List<Attachment> attachment) {
		this.attachment = attachment;
	}

	public Other getOther() {
		return other;
	}

	public void setOther(Other other) {
		this.other = other;
	}
	

}
