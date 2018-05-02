package com.emindsoft.zsj.record.vo;

import java.util.List;

import com.emindsoft.zsj.record.model.Record;
import com.emindsoft.zsj.record.model.RecordMap;

public class RecordInfoVO {
	private RecordMap rm;
	private List<Record> recordList;
	
	public RecordInfoVO(RecordMap rm, List<Record> recordList) {
		super();
		this.rm = rm;
		this.recordList = recordList;
	}
	public RecordMap getRm() {
		return rm;
	}
	public void setRm(RecordMap rm) {
		this.rm = rm;
	}
	public List<Record> getRecordList() {
		return recordList;
	}
	public void setRecordList(List<Record> recordList) {
		this.recordList = recordList;
	}
	
}
