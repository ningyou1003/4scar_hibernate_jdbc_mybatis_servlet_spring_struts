package com.emindsoft.zsj.system.vo;

import com.emindsoft.zsj.system.model.RolePower;

import java.util.ArrayList;
import java.util.List;

public class RolePowerVO {
	private String keyid;
	private String name;
	private String Parentid;
	private int lookpower;
	private int addpower;
	private int editpower;
	private int delpower;
	private int apppower;
	private List<RolePowerVO> children;

	public RolePowerVO() {
		children = new ArrayList<RolePowerVO>();
	}

	public static RolePowerVO copyFormPower(RolePower power2,
			List<RolePower> plist) {
		RolePowerVO rp = new RolePowerVO();
		rp.keyid = power2.getStr("KeyID");
		rp.name = power2.getStr("Name");
		rp.Parentid = power2.getStr("ParentID");
		rp.lookpower = power2.getLong("lookpower").intValue();
		rp.addpower = power2.getLong("addpower").intValue();
		rp.editpower = power2.getLong("editpower").intValue();
		rp.delpower = power2.getLong("delpower").intValue();
		rp.apppower = power2.getLong("apppower").intValue();
		for (RolePower power : plist) {
			//子节点
			if (rp.keyid.equals(power.get("ParentID"))) {
				rp.children.add(copyPowerToVO(power));
			//疑似孙子节点
			} else if(power.getStr("Keyid").contains(rp.keyid)){
				for (RolePowerVO rolesPower : rp.children) {
					if (rolesPower.keyid.equals(power.get("ParentID"))) {
						rolesPower.children.add(copyPowerToVO(power));
					}
				}
			}
		}
		return rp;

	}
	
	public static RolePowerVO copyPowerToVO(RolePower power2) {
		RolePowerVO rp = new RolePowerVO();
		rp.keyid = power2.getStr("KeyID");
		rp.name = power2.getStr("Name");
		rp.Parentid = power2.getStr("ParentID");
		rp.lookpower = power2.getLong("lookpower").intValue();
		rp.addpower = power2.getLong("addpower").intValue();
		rp.editpower = power2.getLong("editpower").intValue();
		rp.delpower = power2.getLong("delpower").intValue();
		rp.apppower = power2.getLong("apppower").intValue();
		return rp;
	}

	public String getKeyid() {
		return keyid;
	}

	public void setKeyid(String keyid) {
		this.keyid = keyid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentid() {
		return Parentid;
	}

	public void setParentid(String parentid) {
		Parentid = parentid;
	}

	public int getLookpower() {
		return lookpower;
	}

	public void setLookpower(int lookpower) {
		this.lookpower = lookpower;
	}

	public int getAddpower() {
		return addpower;
	}

	public void setAddpower(int addpower) {
		this.addpower = addpower;
	}

	public int getEditpower() {
		return editpower;
	}

	public void setEditpower(int editpower) {
		this.editpower = editpower;
	}

	public int getDelpower() {
		return delpower;
	}

	public void setDelpower(int delpower) {
		this.delpower = delpower;
	}

	public int getApppower() {
		return apppower;
	}

	public void setApppower(int apppower) {
		this.apppower = apppower;
	}

	public List<RolePowerVO> getChildren() {
		return children;
	}

	public void setChildren(List<RolePowerVO> children) {
		this.children = children;
	}

}
