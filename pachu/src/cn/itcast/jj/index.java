package cn.itcast.jj;


import java.io.InputStream;  
import org.apache.http.client.config.CookieSpecs;  
import org.apache.http.client.config.RequestConfig;  
import org.apache.http.client.methods.CloseableHttpResponse;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.impl.client.CloseableHttpClient;  
import org.apache.http.impl.client.HttpClients;  
public class index {  
	
    private static final int page = 4;  
    public static void main(String[] args) {  
        //HttpClient 超时配置  
        RequestConfig Config = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).setConnectionRequestTimeout(6000).setConnectTimeout(6000).build();  
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(Config).build();  
        System.out.println("开始lol....");  
        for (int i = page; i > 0; i--) {  
            //HttpPost httpPost = new HttpPost("http://www.jf258.com/nansheng/"+ i+"1.html"); //需要爬的网站  
            HttpPost httpPost = new HttpPost("http://www.jf258.com/nansheng/"+ i+"1.html"); //需要爬的网站  
            httpPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");//伪装一个浏览器  
            try {  
                CloseableHttpResponse response = httpClient.execute(httpPost);//开始  
                InputStream ism = response.getEntity().getContent();  
                String context = Utils.convertStreamToString(ism);  
                new Thread(new CheDHtmlParser(context, i)).start();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}