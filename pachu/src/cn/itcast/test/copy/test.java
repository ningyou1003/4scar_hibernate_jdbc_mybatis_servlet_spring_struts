package cn.itcast.test.copy;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class test {

	
public static void main(String[] args) throws IOException {
	
		//测试链接路径
		String url ="http://search.gome.com.cn/search?question=%E6%B4%97%E8%A1%A3%E6%9C%BA&page=2";
	
	
		//创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建http get请求
		HttpGet httpGet = new HttpGet(url);
		
		CloseableHttpResponse response = null;
		try {
			//执行请求
			response = httpClient.execute(httpGet);
			
			//判断返回状态是否为200
			if(response.getStatusLine().getStatusCode()==200){
				String content = EntityUtils.toString(response.getEntity(),"UTF-8");
				System.out.println(content);
			}
			
					
		} catch (Exception e) {
			System.out.println("出异常了");
			// TODO: handle exception
		}finally{
			if(response!=null){
				response.close();
			}
			httpClient.close();
		}
		System.out.println("执行完了");
		
	}
}
