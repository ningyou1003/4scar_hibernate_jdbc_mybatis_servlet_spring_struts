package com.emindsoft.zsj.dataStatistics.vo;

import java.util.List;

import com.emindsoft.zsj.base.attachment.model.Attachment;
import com.emindsoft.zsj.dataStatistics.model.Proposal;

public class ProposalDetailVO {
	private Proposal proposal;
	private List<Attachment> attachment;
	public Proposal getProposal() {
		return proposal;
	}
	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}
	public ProposalDetailVO(Proposal proposal, List<Attachment> attachment) {
		this.proposal = proposal;
		this.attachment = attachment;
	}
	public List<Attachment> getAttachment() {
		return attachment;
	}
	public void setAttachment(List<Attachment> attachment) {
		this.attachment = attachment;
	}
}
