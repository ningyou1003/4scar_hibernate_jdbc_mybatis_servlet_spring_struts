<%@ page import="com.emindsoft.zsj.base.attachment.model.Attachment"%>
<%@ page import="com.emindsoft.zsj.util.PropertiesContent" %>
<%@ page import="java.io.File" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="javax.imageio.ImageIO" %>
<%@ page import="java.io.IOException" %>
<%@ page import="com.emindsoft.zsj.util.CmykToRgb" %>
<%@ page import="java.io.FileOutputStream" %>
<%@ page import="com.sun.image.codec.jpeg.JPEGImageEncoder" %>
<%@ page import="com.sun.image.codec.jpeg.JPEGCodec" %>
<%@ page import="java.awt.Image" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page language="java" import="java.util.*"%>
<%@ page import="org.apache.sanselan.ImageReadException" %>
<%@ page import="java.text.SimpleDateFormat" %>


<%
	int b = 1;
	String upload_Dir = PropertiesContent.get("upload_dir");
		
		List<Attachment> attachment = Attachment.dao.find("SELECT keyid,path FROM base_attachment WHERE RelateType = ? ", "silhouette");
		
			for(int i = 0; i < attachment.size(); i++) {
				File imageFile = new File(upload_Dir + attachment.get(i).getStr("path"));
				if(imageFile.exists()) {
					File zipimage = new File(upload_Dir + "zipJpg/" + attachment.get(i).getStr("keyid") + ".jpg");
					if(!zipimage.exists()) {
						zipimage = null;
						//CMYK TO RGB
						BufferedImage image;
	            try {
	          		image = ImageIO.read(imageFile);

	             } catch (IOException e) {
	          		// TODO Auto-generated catch block
	          		// e.printStackTrace();
	          		CmykToRgb rgb = new CmykToRgb();
	          		image = rgb.readImage(imageFile);
	          		rgb = null;
	          	}
	          	imageFile = null;
	            //确定压缩图片尺寸比例
	    		double rate1 = (double)image.getWidth(null);
	    		double rate2 = (double)image.getHeight(null);
	    		double rate;
	    		if(rate1 > rate2) {
	    			rate = 1700.00 / rate1;
	    		} else {
	    			rate = 950.00 / rate1;
	    		}
	    		//确定压缩图片尺寸
	    		int newWidth = (int)((double)image.getWidth(null) * rate);
	    		int newHeight = (int)((double)image.getHeight(null) * rate);
	    		
	    		BufferedImage tag = new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_RGB);
	    		tag.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight,Image.SCALE_SMOOTH), 0, 0, null);

	    		//输出路径
	    		FileOutputStream outputStream = new FileOutputStream(upload_Dir + "zipJpg/" + attachment.get(i).getStr("keyid") + ".jpg");
	    		//压缩图片
	    		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outputStream);
	    		encoder.encode(tag);
	    		outputStream.close();
	    		System.out.println("压缩完成第" + b++ + "张!");
	    		
				image = null;
				tag = null;
				outputStream = null;
				encoder = null;		
	    		}

	    	}
	    }
	System.out.println("完成压缩!");
%>


