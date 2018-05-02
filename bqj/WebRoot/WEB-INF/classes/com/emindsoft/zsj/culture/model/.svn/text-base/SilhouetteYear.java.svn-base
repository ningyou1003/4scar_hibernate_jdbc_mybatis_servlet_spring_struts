package com.emindsoft.zsj.culture.model;

import cn.dreampie.tablebind.TableBind;

import com.emindsoft.zsj.base.model.BaseModel;
import com.jfinal.plugin.activerecord.Page;

@TableBind(tableName = "p_silhouetteyear", pkName = "KeyID")
public class SilhouetteYear extends BaseModel<SilhouetteYear> {
	
	public static SilhouetteYear dao = new SilhouetteYear();
	public static String table = "p_silhouetteyear";
	
	/**
	 * 分页
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<SilhouetteYear> page(int pageNo,int pageSize) {
		return this.paginate(pageNo, pageSize,"select * ",
				" from " + table + "  where 1 = 1  order by region desc");
	}
	
	public SilhouetteYear findByRegion(String region){
		return findFirst("select *  from " + table + "  where region='"+region+"'");
	}

}
