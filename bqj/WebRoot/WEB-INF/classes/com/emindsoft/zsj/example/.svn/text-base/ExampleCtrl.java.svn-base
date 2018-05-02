package com.emindsoft.zsj.example;

import cn.dreampie.routebind.ControllerKey;
import com.emindsoft.zsj.base.ctrl.AdminBaseController;

@ControllerKey("example")
public class ExampleCtrl extends AdminBaseController{

	public void save() {
		//业务代码
		//...

		//处理附件
		processAttachment("456"); //456是关联id, 一般是业务代码保存得到的keyId

		success();
	}

}
