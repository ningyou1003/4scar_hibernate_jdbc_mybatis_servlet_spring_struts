package com.zrcx.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class UploadController{
	@RequestMapping("upload")
	public ModelAndView upload(String name , 
			@RequestParam("image") MultipartFile multiFile) throws Exception {
		System.out.println("-------------执行upload()方法---------------");
		System.out.println("姓名:"+name);
		System.out.println("文件名:"+multiFile.getOriginalFilename());
		System.out.println("文件类型:"+multiFile.getContentType());
		//保存到指定目录
		multiFile.transferTo(new File("d:\\temp\\"+multiFile.getOriginalFilename()));
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("msg","文件上传成功");
		return mv;
	}

}

















