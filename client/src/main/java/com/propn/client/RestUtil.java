package com.propn.client;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

public class RestUtil {
	
	private static String URL="http://redhat72:7980/RTDM/rest/runtime/decisions/lgevent01";
	private static int socketTimeout = 2000;// 请求超时时间
	private static int connectTimeout = 2000;// 传输超时时间
	

	public static void main(String[] args) {
		
/*		{
			"version":"1",
			"clientTimeZone":"Asia/Chongqing", 
			"inputs":
			{
			"LG_ID" : "11111",
			"CAMP_CODE" : "ssssss",
			"CUST_NAME" : "ssss",
			"MOBILE" : "1388222",
			"CUST_ID" : "111111111111"
			}
		}*/
		
		String json="{\"version\":\"1\",\"clientTimeZone\":\"Asia/Chongqing\", \"inputs\":"+ 
				"{" + 
				"	\"LG_ID\" : \"11111\"," + 
				"	\"CAMP_CODE\" : \"ssssss\"," + 
				"	\"CUST_NAME\" : \"ssss\"," + 
				"	\"MOBILE\" : \"1388222\"," + 
				"	\"CUST_ID\" : \"111111111111\"" + 
				"}}";
		
		CloseableHttpClient httpClient = HttpUtil.getHttpClient();
		
		HttpPost httpPost = new HttpPost(URL);
		// 设置请求和传输超时时间
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
		httpPost.setConfig(requestConfig);
		httpPost.setHeader("Content-Type", "application/soap+xml;charset=UTF-8");
		httpPost.setEntity(new StringEntity(json,Charset.forName("UTF-8")));
		
		try {
			CloseableHttpResponse response = httpClient.execute(httpPost);
			HttpEntity httpEntity = response.getEntity();
			if (httpEntity != null) {
				String retStr = EntityUtils.toString(httpEntity, "UTF-8");
				response.close();
			}
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
