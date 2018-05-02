package cn.itcast.jj;

import java.util.ArrayList;
import java.util.List;  

public class CheDHtmlParser implements Runnable {  
    private String html;  
    private int page;  
      
    public CheDHtmlParser(String html,int page) {  
        this.html = html;  
        this.page = page;  
    }  
    @Override  
    public void run() {  
        List<String> list = new ArrayList<String>();  
        html = html.substring(html.indexOf("list"));  
            String[] ss = html.split("li>");  
            for (String s : ss) {  
                if (s.indexOf("<img src=") > 0) {  
                    try{  
                        int i = s.indexOf("<img src=\"") + "<img src=\"".length();  
                        list.add(s.substring(i, s.indexOf("\"", i + 1)));  
                    }catch (Exception e) {  
                        System.out.println(s);  
                    }  
                }  
            }  
          
        for(String imageUrl : list){  
                new Thread(new CheDImageCreator(imageUrl,page)).start();  
        }  
    }  
}