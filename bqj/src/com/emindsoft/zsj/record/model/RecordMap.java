package com.emindsoft.zsj.record.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.dreampie.tablebind.TableBind;

import com.emindsoft.zsj.base.model.BaseModel;
import com.emindsoft.zsj.record.vo.RecordMonthCountVO;
import com.emindsoft.zsj.record.vo.RecordTreeVO;
import com.emindsoft.zsj.record.vo.RecortTimeCountVO;
import com.jfinal.plugin.activerecord.Page;

@TableBind(tableName = "c_record_map", pkName = "KeyId")
public class RecordMap extends BaseModel<RecordMap> {

	public static RecordMap dao = new RecordMap();
	public static String table = "c_record_map";

	public boolean deletePointsByIds(String[] keyids) {
		return super.deleteByIds(keyids, table);

	}

	public RecordMap loadPointsDetail(String keyid) {
		if (!StringUtils.isEmpty(keyid)) {
			return findFirst("select * from c_record c where c.keyid='" + keyid
					+ "' ");
		} else {
			return null;
		}
	}

	public List<RecordMap> findByRegion(String regionId) {
		return find("select * from " + table + " where regionId=?", regionId);
	}

	public List<RecordTreeVO> getRecords(String month, String startTime,
			String endTime, String area, String problem) {
		String sql = "SELECT rm.KeyId recordId, rm.regionId, a.ParentCode pRegionId, a.Region regionName, a.OperLevel level, r.outerNUmber, r.time time, rm.address, "
				+ "rm.Title,r.checkName, r.hasProblem, r.description Content, r.isSolved,r.settle,r.keyId  FROM c_record_map rm left JOIN s_area a on rm.regionId=a.RegionCode left join c_record r on r.mapRecordId =rm.keyId";
		// left join c_record r on r.mapRecordId =rm.keyId

		// 2016-11-09精确导出excel 开始
		if (StringUtils.isNotEmpty(month) || StringUtils.isNotEmpty(area)
				|| StringUtils.isNotEmpty(problem)) {
			sql += " WHERE";
		}
		// 月份 地区 是否发现问题 都不为空的时候导出excel
		if (StringUtils.isNotEmpty(month) && StringUtils.isNotEmpty(area)
				&& StringUtils.isNotEmpty(problem)) {
			sql += " DATE_FORMAT(r.time,'%Y-%m')='" + month
					+ "' AND rm.address like '%" + area
					+ "%' AND r.hasProblem='" + problem + "'";
		}
		// 月份 地区不为空 是否发现问题为空时导出excel
		if (StringUtils.isNotEmpty(month) && StringUtils.isNotEmpty(area)
				&& StringUtils.isEmpty(problem)) {
			sql += " DATE_FORMAT(r.time,'%Y-%m')='" + month
					+ "' AND rm.address like '%" + area + "%'";
		}
		// 月份 是否发现问题不为空 地区为空时导出excel
		if (StringUtils.isNotEmpty(month) && StringUtils.isNotEmpty(problem)
				&& StringUtils.isEmpty(area)) {
			sql += " DATE_FORmAT(r.time,'%Y-%m')='" + month
					+ "' AND r.hasProblem='" + problem + "'";
		}
		// 地区 是否发现问题不为空 月份为空时 导出excel
		if (StringUtils.isNotEmpty(area) && StringUtils.isNotEmpty(problem)
				&& StringUtils.isEmpty(month)) {
			sql += " rm.address like '%" + area + "%' AND r.hasProblem='"
					+ problem + "'";
		}
		// 月份不为空 地区 是否发现问题 为空 导出 excel
		if (StringUtils.isNotEmpty(month) && !StringUtils.isNotEmpty(area)
				&& StringUtils.isEmpty(problem)) {
			sql += " DATE_FORMAT(r.time, '%Y-%m')='" + month + "'";
		}
		// 地区不为空 月份 是否发现问题 为空 导出 excel
		if (StringUtils.isNotEmpty(area) && StringUtils.isEmpty(month)
				&& StringUtils.isEmpty(problem)) {
			sql += " rm.address like '%" + area + "%'";
		}
		// 是否发现问题不为空 月份 地区为空 导出excel
		if (StringUtils.isNotEmpty(problem) && StringUtils.isEmpty(month)
				&& StringUtils.isEmpty(area)) {
			sql += " r.hasProblem='" + problem + "'";
		}

		if (StringUtils.isNotEmpty(startTime)
				&& StringUtils.isNotEmpty(endTime)) {
			sql += " WHERE r.time>'" + startTime + "'  AND r.time<'" + endTime
					+ "' ";
		}
		sql += " ORDER BY r.time ";
		// 2016-11-09精确导出 excel 结束

		List<RecordTreeVO> rtVO = new ArrayList<RecordTreeVO>();
		List<RecordMap> rmList = find(sql);
		for (RecordMap rm : rmList) {
			String pid = rm.getStr("pregionid");
			if (!StringUtils.isEmpty(pid)) {
				rtVO.add(RecordTreeVO.markTree(rm, rmList));
			}
		}
		return rtVO;
	}

	public List<RecordMonthCountVO> getRecords1(String month) {
		String sql = "SELECT RegionCode id, ParentCode pid, Region regionname, OperLevel level,'' checkName, '' outernumber, '' time, '' hasproblem, '' content, '' issolved,''settle, '0' isrecord FROM s_area a, c_record_map rm WHERE a.RegionCode=rm.regionId "
				+ "UNION "
				+ "SELECT keyid id, regionId pid, Title regionname, '' level,'' checkName, '' outerNUmber, '' time, '' hasProblem, '' Content, '' isSolved,'' settle, '0' isrecord FROM c_record_map "
				+ "UNION"
				+ " SELECT keyid id, mapRecordId pid, num regionname, '' level,checkName, outerNUmber, time, hasproblem, description content, issolved,settle, '1' isrecord FROM c_record";
		if (StringUtils.isNotEmpty(month)) {
			sql += " WHERE DATE_FORMAT(time, '%Y-%m')='" + month + "'";
		}
		sql += " ORDER BY time ";
		List<RecordMonthCountVO> rtVO = new ArrayList<RecordMonthCountVO>();
		List<RecordMap> rmList = find(sql);
		for (RecordMap rm : rmList) {

			String id = rm.getStr("id");
			if (id.length() < 20) {
				rtVO.add(RecordMonthCountVO.markTree(rm, rmList));
			}
		}
		return rtVO;
	}

	// 2016-10-14 添加月份查询功能 开始
	/**
	 * 条件查询--根据月份查询
	 * */
	public List<RecordMonthCountVO> getRecords2(String month, String area,
			String problem) {

		String sql = "SELECT keyid id, mapRecordId pid, num regionname, '' level,checkName, outerNUmber, time, hasproblem, description content, issolved,settle, '1' isrecord FROM c_record";
		// 2017-02-16 增加是否发现问题的查询条件 开始
		if (StringUtils.isNotEmpty(month) || StringUtils.isNotEmpty(problem)) {
			sql += " WHERE ";
		}
		// 2017-02-16 增加是否发现问题的查询条件 结束
		if (StringUtils.isNotEmpty(month)) {
			sql += " DATE_FORMAT(time, '%Y-%m')='" + month + "'";
		}
		// 2017-02-16 增加是否发现问题的查询条件 开始
		if (StringUtils.isNotEmpty(month) && StringUtils.isNotEmpty(problem)) {
			sql += " AND hasProblem ='" + problem + "'";
		} else if (!StringUtils.isNotEmpty(month)
				&& StringUtils.isNotEmpty(problem)) {
			sql += " hasProblem ='" + problem + "'";
		}
		// 2017-02-16 增加是否发现问题的查询条件 结束
		sql += " UNION"
				+ " SELECT keyid id, regionId pid, Title regionname, '' level,'' checkName, '' outerNUmber, '' time, '' hasProblem, '' Content, '' isSolved,'' settle,'0' isrecord FROM c_record_map ";
		if (StringUtils.isNotEmpty(area)) {
			sql += " WHERE address like '%" + area + "%'";
		}
		sql += " UNION SELECT RegionCode id, ParentCode pid, Region regionname, OperLevel level,'' checkName, '' outernumber, '' time, '' hasproblem, '' content, '' issolved,'' settle, '0' isrecord FROM s_area a, c_record_map rm WHERE a.RegionCode=rm.regionId ";
		// + "UNION "
		// +
		// "SELECT RegionCode id, ParentCode pid, Region regionname, OperLevel level,'' checkName, '' outernumber, '' time, '' hasproblem, '' content, '' issolved,'' settle, '0' isrecord FROM s_area a, c_record_map rm WHERE a.RegionCode=rm.regionId ";
		/*if (StringUtils.isNotEmpty(area)) {
			sql += " OR a.Region like '%" + area + "%'";
		}*/
		sql += " ORDER BY time ";
		List<RecordMonthCountVO> rtVO = new ArrayList<RecordMonthCountVO>();

		List<RecordMap> rmList = find(sql);

		List<RecordMap> rmList1 = new ArrayList<RecordMap>();

		RecordMap addRm = null;

		// 得到查询条件的数据 2016-10-26
		for (RecordMap rm1 : rmList) {
			String time = rm1.getStr("time");
			if (time != null && !"".equals(time)) {
				rmList1.add(rm1);
			}
		}

		// 根据查询条件的数据得到它的父节点数据 2016-10-26
		if (rmList1.size() != 0) {
			for (int i = 0; i < rmList1.size(); i++) {
				for (RecordMap rm : rmList) {
					if (rmList1.get(i) != null
							&& !"".equals(rmList1.get(i).get("id"))
							&& rmList1.get(i).get("id") != null) {
						if (rmList1.get(i).get("pid").equals(rm.get("id"))) {
							rmList1.add(rm);
							addRm = checkMonthList(rm, rmList);
							if (addRm != null)
								rmList1.add(addRm);
						}
					}

				}

			}

		}
		// 删除重复数据 2016-10-26
		HashSet h = new HashSet(rmList1);
		rmList1.clear();
		rmList1.addAll(h);

		for (RecordMap rm : rmList1) {

			String id = rm.getStr("id");
			if (id.length() < 20) {
				rtVO.add(RecordMonthCountVO.markTree(rm, rmList1));
			}
		}
		return rtVO;
	}

	// 2016-10-14 添加月份查询功能 结束

	// 2016-10-25开始
	public RecordMap checkMonthList(RecordMap rm, List<RecordMap> rmList) {
		RecordMap rm1 = null;
		for (RecordMap rm2 : rmList) {
			if (rm.get("pid").equals(rm2.get("id"))) {
				checkMonthList(rm2, rmList);
				rm1 = rm2;
				// checkMonthList(rm2, rmList);
			} else {
				;
			}
		}
		return rm1;
	}

	// 2016-10-25结束

	public RecordMap findById(String keyid) {
		String sql = "SELECT rm.KeyId, rm.Title, rm.regionId, rm.address, rm.lat, rm.lng, rm.nationalCheckPoint, rm.important, a.Region as level FROM "
				+ table
				+ " rm JOIN s_area a ON rm.regionId=a.RegionCode WHERE rm.KeyId='"
				+ keyid + "'";
		return findFirst(sql);
	}

	public List<RecortTimeCountVO> getRecordsCountByTime(String startTime,
			String endTime) {
		String sql = "SELECT DISTINCT rm.regionId id, a.ParentCode pid, a.OperLevel level, a.Region regionname, COUNT(r.keyId) checkTimes, "
				+ "SUM(r.outerNumber) outerNumber, SUM(r.hasProblem=1) problemNumbers, SUM(r.isSolved=1) isSolvedNumbers FROM c_record_map rm, s_area a, c_record r "
				+ "WHERE rm.regionId=a.RegionCode AND r.mapRecordId=rm.KeyId ";
		if (StringUtils.isNotEmpty(startTime)
				&& StringUtils.isNotEmpty(endTime)) {
			sql += " AND r.time>'" + startTime + "'  AND r.time<'" + endTime
					+ "' ";
		}
		sql += " GROUP BY rm.regionId ORDER BY a.OperLevel";

		// sql +=" ORDER BY time ";

		List<RecordMap> rmList = find(sql);
		List<RecortTimeCountVO> rtVO = new ArrayList<RecortTimeCountVO>();
		if (rmList.size() > 0) {
			Integer pNodeLevel = rmList.get(0).getInt("level");
			for (RecordMap rm : rmList) {
				Integer level = rm.getInt("level");
				if (StringUtils.isNotEmpty(rm.getStr("id"))) {
					rtVO.add(RecortTimeCountVO.markTree(rm, rmList));
				}
			}
		}
		return rtVO;
	}
	
	//2017-12-07 csh
	/**
	 * 分页获取，减少一次的数据量，提高浏览器画点位时间，之前是一次性取完所有点位，
	 * 1500多个点，浏览器测试画点位用了几十秒才画完
	 * 
	 * @param pageNumber 第几页数
	 * @param pageSize 一页的记录条数
	 * @param regioncode 查询条件 根据区域编码查询
	 * @return
	 * @author csh 2017-12-07 
	 */
	public Page<RecordMap> findByRegioncode(int pageNumber,int pageSize,String regioncode,String title){
		String sql="select * ";
		String sqle=" from c_record_map where regionid='"+regioncode+"'";
		if (title!=null && title.length()>0) {
			sqle+=" and title like '%"+title+"%'";
		}
		return this.paginate(pageNumber, pageSize, sql, sqle);
		
	}

}
