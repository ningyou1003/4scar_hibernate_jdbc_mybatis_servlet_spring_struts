package cn.itcast.jj;

import java.io.File;  
import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.net.URL;  
import java.net.URLConnection;  
  
public class CheDImageCreator implements Runnable {  
    private static int count = 0;  
    private String imageUrl;  
    private int page;  
     //存储路径  
    private static final String basePath = "/home/ym/ll/jj";   
    public CheDImageCreator(String imageUrl,int page) {  
        this.imageUrl = imageUrl;  
        this.page = page;  
    }  
    @Override  
    public void run() {  
        File dir = new File(basePath);  
        if(!dir.exists()){  
            dir.mkdirs();  
              
        }  
        String imageName = imageUrl.substring(imageUrl.lastIndexOf("/")+1);//获取图片名字  
        try {  
            File file = new File( basePath+"/"+page+"--"+imageName);//拼接  
            OutputStream os = new FileOutputStream(file);  
                //创建一个url对象  
                String u="http://www.jf258.com"+imageUrl;  
                URL uri = new URL(u);    
                URLConnection connection = uri.openConnection();    
                connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");//伪装成一个浏览器  
                InputStream is = connection.getInputStream();  //开始一个流  
  
            byte[] buff = new byte[1024];  
            while(true) {  
                int readed = is.read(buff);  
                if(readed == -1) {  
                    break;  
                }  
                byte[] temp = new byte[readed];  
                System.arraycopy(buff, 0, temp, 0, readed);  
                //写入文件  
                os.write(temp);  
            }  
            System.out.println("第"+(count++)+"张:"+file.getAbsolutePath());  
            is.close();   
            os.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
} 