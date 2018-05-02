package com.emindsoft.zsj.system.model;

import cn.dreampie.tablebind.TableBind;
import com.emindsoft.zsj.base.model.BaseModel;
import com.jfinal.plugin.activerecord.Page;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@TableBind(tableName = "s_dictionary", pkName = "KeyId")
public class Dictionary extends BaseModel<Dictionary> {
	public static Dictionary dao = new Dictionary();
	public static String table = "s_dictionary";

	/**
	 * 词典列表中的查询按钮的sql方法
	 * param pageNo
	 * param pageSize
	 * param selectType
	 * @return
	 */
	public Page<Dictionary> page(int pageNo, int pageSize, String selectType) {
		if (StringUtils.isEmpty(selectType)) {
			return this.paginate(pageNo, pageSize, "select *", "FROM " + table
					+ " ORDER BY OrderID");
		} else {
			return this.paginate(pageNo, pageSize, "select *", "FROM " + table
					+ " WHERE DictionaryType='" + selectType
					+ "' ORDER BY OrderID");
		}

	}
	
	/**
	 * 词典列表中的查询按钮的sql方法
	 * param pageNo
	 * param pageSize
	 * param selectType
	 * @return
	 */
	public Page<Dictionary> page(int pageNo, int pageSize) {
		return this.paginate(pageNo, pageSize, "select *", "FROM " + table +"DictionaryType ='企业性质'"
				+ " ORDER BY OrderID");
	}
	
	/**
	 * 根据参数keyid删除本表数据
	 * @return
	 */
	public boolean deleteByIds(String[] keyids) {
		return super.deleteByIds(keyids, table);
	}
	
	/**
	 * 加载查询字典列表
	 * @return
	 */
	public List<Dictionary> loadSelect() {
		String sql = "select * from " + table + " group by DictionaryType";
		return find(sql);
	}
	
	public List<Dictionary> loadSelectForLink() {
		String sql = "select KeyID,dictionaryname from s_dictionary where DictionaryType = '友情链接类别'";
		return find(sql);
	}

	/**
	 * 加载自动补齐控件字典列表
	 * @return
	 */
	public List<Dictionary> findAllType() {
		String sql = "select dictionaryType as name from " + table + " group by DictionaryType";
		return find(sql);
	}
	public List<Dictionary> findDicNameByType(String selectType){
		String sql = "select * from " + table + " WHERE DictionaryType='" + selectType+"' ORDER BY OrderID";
		return find(sql);
	}
}
