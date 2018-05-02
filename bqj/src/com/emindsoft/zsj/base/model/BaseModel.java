package com.emindsoft.zsj.base.model;

import cn.dreampie.web.model.Model;
import com.jfinal.plugin.activerecord.Db;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * Created by ym on 15-3-10.
 */
public class BaseModel<M extends Model> extends Model<M> {

    public boolean saveOrUpdate() {
        String id = this.getStr("KeyId");
        if (StringUtils.isEmpty(id)) {
            id = UUID.randomUUID().toString();
            id = id.replace("-", "");
            set("KeyId", id);
            return super.save();
        } else {
            return super.update();
        }
    }

    public String getId() {
        String id = this.getStr("KeyId");
        id = UUID.randomUUID().toString();
        id = id.replace("-", "");
        return id;
    }


    public boolean deleteByIds(String[] keyids, String table) {
        StringBuffer sql = new StringBuffer("");
        sql.append("DELETE FROM ");
        sql.append(table);
        sql.append(" WHERE KeyId in ( ? ");
        for (String keyid : keyids) {
            sql.append(", ? ");
        }
        String sqlString = sql.substring(0, sql.length() - 4);
        sqlString = sqlString + ")";

        int i = Db.update(sqlString, keyids);
        return i > 0;
    }

}
