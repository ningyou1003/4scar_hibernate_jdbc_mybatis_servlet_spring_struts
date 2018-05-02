package com.emindsoft.zsj.docNotice.model;

import java.util.List;

import cn.dreampie.tablebind.TableBind;

import com.emindsoft.zsj.base.model.BaseModel;

@TableBind(tableName = "d_enrol", pkName = "KeyId")
public class Enrol extends BaseModel<Enrol>{
	public static Enrol dao =new Enrol();
	public static String table = "d_enrol";
	
	public List<Enrol> list(String flowid){
		String sql="SELECT * FROM d_enrol  WHERE FlowId=?  ORDER BY Time";//'"+flowid+"'";
		return find(sql, flowid);
	}
	
	public List<Enrol> allList(String sourceFlowId){
		String sql="SELECT e.KeyId,e.FlowId,e.SourceFlowId,e.RegionCode,e.CreateUser,e.Time,e.Name,e.Sex,e.Phone,e.Peoples,CONCAT(e.Duty,'(',a.Region,')') as Duty,e.transportation,e.ArriveTime,e.ArriveSite,e.ps FROM d_enrol e,s_area a WHERE e.sourceFlowId=? AND e.RegionCode=a.RegionCode ORDER BY e.Time";//'"+flowid+"'";
		return find(sql, sourceFlowId);
	}
	
	public Enrol findFistByFlowid(String flowid){
		String sql="SELECT * FROM d_enrol WHERE FlowId=? ORDER BY Time";//'"+flowid+"'";
		return findFirst(sql, flowid);
		
	}
}
