package cn.itcast.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;



import cn.itcast.crawler.pojo.Item;

public class JdCrawler {
	
public static void main(String[] args) throws Exception {
		
		new JdCrawler().start();
	}
	
	private static final String BASE_URL = "https://list.jd.com/list.html?cat=9987,653,655&page={page}";
	
	
	public void start() throws Exception {
		// 入口页面地址
		String startUrl = StringUtils.replace(BASE_URL, "{page}", "1");
		
		//获取到总页数
		String html = doGet(startUrl);
		
		//解析html
		Document document = Jsoup.parse(html);
		String pageText = document.select("#J_topPage").text();
		//输出总页数
		//System.out.println(pageText);
		
		String[] strs = pageText.split("\\D+");
		/*for (String string : strs) {
			System.out.println(string);
		}*/
		Integer totalPage = Integer.parseInt(strs[1]);
		
		System.out.println("开始爬，共"+totalPage+"页");
		//分页查询
		for (int i = 1; i <= totalPage; i++) {
			String url = StringUtils.replace(BASE_URL, "{page}", String.valueOf(i));
			String content = doGet(url);//商品数据
			
			Document root = Jsoup.parse(content);//解析html
			Elements lis = root.select("#plist li.gl-item");//商品列表 //select是选择器，选择id为plist里的li
			
			Map<Long,Item> items = new HashMap<Long,Item>();
			for (Element li : lis) {
				Item item = new Item();
				Element div = li.child(0);
				Long id = Long.valueOf(div.attr("data-sku"));//获取li里属性为data-sku的值
				//System.out.println(id);
				String image = li.select(".p-img img").attr("src");
				
				//不为空时下载图片
				if(!(image=="")){
					//System.out.println("http:"+image);
					String imageUrl = "http:"+image;
					String imageName = image.substring(image.lastIndexOf('/')+1, image.length());
					//System.out.println(imageName);
					String save = "/home/ym/QQ文件接收/MobileFile/Image/"+i;
					//图片的url，图片名，保存位置
					DownloadImage.download(imageUrl,imageName,save);
				}
				
				//String title = li.select(".p-name").text();
				//System.out.println(title);
				
			}
			
			System.out.println("第"+i+"页");
		}
		System.out.println("爬完了");
		
	}
	
	public String doGet(String url) throws Exception{
		
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
				return content;
			}
			
					
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(response!=null){
				response.close();
			}
			httpClient.close();
		}
		return null;
		
	}
}
