package autoInsurance;
//9bfce2fe98f09c664d2bb83debbd05e2
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	public static Map<String,String> digestMap = new HashMap<String,String>();

	SSLClient httpClient = null;

	public HttpClientUtil(){
		try {
			httpClient = new SSLClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String doPost(String url,Map<String,String> map,String charset){
		map = map == null?new HashMap<String,String>():map;
		HttpPost httpPost = null;
		String result = null;
		try{
			httpPost = new HttpPost(url);
			setHeaders(httpPost);
			httpPost.setHeader("Referer", url);
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator iterator = map.entrySet().iterator();
			while(iterator.hasNext()){
				Entry<String,String> elem = (Entry<String, String>) iterator.next();
				list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
			}
			if(list.size() > 0){
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
				httpPost.setEntity(entity);
			}
			HttpResponse response = httpClient.execute(httpPost);
			  
			if(response != null){
				Header[] headers = response.getHeaders("x-iCore_fa-digest");
				if(null != headers){
					for(Header h:headers){
						System.out.println(h.getName() + ":" + h.getValue());
					}
				}
				HttpEntity resEntity = response.getEntity();
				if(resEntity != null){
					result = EntityUtils.toString(resEntity,charset);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	public String doPost(String url,String jsonParam,String charset){
		jsonParam = StringUtils.isBlank(jsonParam)?"":jsonParam;
		HttpPost httpPost = null;
		String result = null;
		try{
			httpPost = new HttpPost(url);
			setHeaders(httpPost);
			httpPost.setHeader("Referer", url);
			
			StringEntity s = new StringEntity(jsonParam,charset);
//		    s.setContentEncoding(charset);
		    s.setContentType("application/json;charset=utf-8");//发送json数据需要设置contentType
		    httpPost.setEntity(s);
			HttpResponse response = httpClient.execute(httpPost);
			if(response != null){
				Header[] headers = response.getHeaders("x-iCore_fa-digest");
				if(null != headers && headers.length>0){
					Header h = headers[0];
					//System.out.println(h.getName() + ":" + h.getValue());
					Map map = JackJson.fromJsonToObject(h.getValue(), Map.class);
					List dataList = (List)map.get("datalist");
					if(null != dataList){
						for(Object obj:dataList){
							Map item = (Map)((Map)obj).get("voucherNo");
							digestMap.put(item.get("val").toString(), item.get("digestKey").toString());
						}
					}
				}
				
				HttpEntity resEntity = response.getEntity();
				if(resEntity != null){
					result = EntityUtils.toString(resEntity,charset);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	public String doGet(String url,Map<String,String> map,String charset){
		
		map = map == null?new HashMap<String,String>():map;
		HttpGet httpGet = null;
		String result = null;
		try{
			String param = "";
			String[] tmpUrl = url.split("\\?");
			url = tmpUrl[0];
			if(tmpUrl.length>1)
				param = "?" + tmpUrl[1];
			else
				param = "?";
			if(map.size()>0){
				Iterator iterator = map.entrySet().iterator();
				while(iterator.hasNext()){
					Entry<String,String> elem = (Entry<String, String>) iterator.next();
					param += "&" + elem.getKey() + "=" + elem.getValue();
				}
			}
			httpGet = new HttpGet(url + param);
			HttpResponse response = httpClient.execute(httpGet);
			  
			if(response != null){
				HttpEntity resEntity = response.getEntity();
				if(resEntity != null){
					result = EntityUtils.toString(resEntity,charset);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	private void setHeaders(HttpPost method) { 
		method.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;"); 
		method.setHeader("Accept-Language", "zh-cn"); 
		method.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.0.3) Gecko/2008092417 Firefox/3.0.3"); 
		method.setHeader("Keep-Alive", "300"); 
		method.setHeader("Connection", "Keep-Alive"); 
		method.setHeader("Cache-Control", "no-cache"); 
	} 
}