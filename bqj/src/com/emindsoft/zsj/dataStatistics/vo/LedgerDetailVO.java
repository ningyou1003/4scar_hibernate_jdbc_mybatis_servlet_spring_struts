package com.emindsoft.zsj.dataStatistics.vo;

import java.util.List;

import com.emindsoft.zsj.base.attachment.model.Attachment;
import com.emindsoft.zsj.dataStatistics.model.Ledger;

public class LedgerDetailVO {
	private Ledger ledger;
	private List<Attachment> attachment;
	
	public LedgerDetailVO(Ledger ledger,List<Attachment> attachment) {
		this.ledger = ledger;
		this.attachment = attachment;
	}
	
	public Ledger getLedger() {
		return ledger;
	}
	public void setLedger(Ledger ledger) {
		this.ledger = ledger;
	}
	public List<Attachment> getAttachment() {
		return attachment;
	}
	public void setAttachment(List<Attachment> attachment) {
		this.attachment = attachment;
	}
	
	
	

}
