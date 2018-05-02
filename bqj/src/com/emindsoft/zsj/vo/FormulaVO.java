package com.emindsoft.zsj.vo;

import java.util.List;

import com.emindsoft.zsj.base.attachment.model.Attachment;
import com.emindsoft.zsj.cases.model.Formula;
import com.emindsoft.zsj.culture.model.Silhouette;

public class FormulaVO {
	private Formula formula;
	private List<Attachment> attachment;
	
	public FormulaVO(Formula formula,List<Attachment> attachment) {
		this.formula = formula;
		this.attachment = attachment;
	}

	public FormulaVO (Formula formula) {
		super();
		this.formula = formula;
	}

	public Formula getFormula() {
		return formula;
	}

	public void setFormula(Formula formula) {
		this.formula = formula;
	}

	public List<Attachment> getAttachment() {
		return attachment;
	}

	public void setAttachment(List<Attachment> attachment) {
		this.attachment = attachment;
	}

	

}
