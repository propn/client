package com.propn.client;

import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class SoapUtil {
	static int socketTimeout = 2000;// 请求超时时间
	static int connectTimeout = 2000;// 传输超时时间
	static Logger logger = Logger.getLogger(SoapUtil.class);

	/**
	 * 使用SOAP1.1发送消息
	 * 
	 * @param postUrl
	 * @param soapXml
	 * @param soapAction
	 * @return
	 */
	public static String doPostSoap1_1(String postUrl, String soapXml, String soapAction) {
		String retStr = "";
		// HttpClient
		CloseableHttpClient httpClient = HttpUtil.getHttpClient();
		HttpPost httpPost = new HttpPost(postUrl);
		// 设置请求和传输超时时间
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
				.setConnectTimeout(connectTimeout).build();
		httpPost.setConfig(requestConfig);
		try {
			httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
			httpPost.setHeader("SOAPAction", soapAction);
			StringEntity data = new StringEntity(soapXml, Charset.forName("UTF-8"));
			httpPost.setEntity(data);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			HttpEntity httpEntity = response.getEntity();
			if (httpEntity != null) {
				retStr = EntityUtils.toString(httpEntity, "UTF-8");
				logger.info("response:" + retStr);
			}
			// 释放资源
			httpClient.close();
		} catch (Exception e) {
			logger.error("exception in doPostSoap1_1", e);
		}
		return retStr;
	}

	/**
	 * 使用SOAP1.2发送消息
	 * 
	 * @param postUrl
	 * @param soapXml
	 * @param soapAction
	 * @return
	 */
	public static String doPostSoap1_2(String postUrl, String soapXml, String soapAction) {
		String retStr = "";
		// HttpClient
		CloseableHttpClient httpClient = HttpUtil.getHttpClient();
		HttpPost httpPost = new HttpPost(postUrl);
		// 设置请求和传输超时时间
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
				.setConnectTimeout(connectTimeout).build();
		httpPost.setConfig(requestConfig);
		try {
			httpPost.setHeader("Content-Type", "application/soap+xml;charset=UTF-8");
			httpPost.setHeader("SOAPAction", soapAction);
			StringEntity data = new StringEntity(soapXml, Charset.forName("UTF-8"));
			httpPost.setEntity(data);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			HttpEntity httpEntity = response.getEntity();
			if (httpEntity != null) {
				// 打印响应内容
				retStr = EntityUtils.toString(httpEntity, "UTF-8");
				logger.info("response:" + retStr);
			}
			// 释放资源
			httpClient.close();
		} catch (Exception e) {
			logger.error("exception in doPostSoap1_2", e);
		}
		return retStr;
	}

	public static void main(String[] args) {
		
		String postUrl = "http://redhat72:8680/RTDM/Event";
		
		String soapXml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:rtdm=\"http://www.sas.com/xml/schema/sas-svcs/rtdm\">"
				+ "	<soapenv:Header/>" 
				+ "	<soapenv:Body>"
				+ "		<rtdm:Event name=\"营销线索-厅堂转换\">"
				+ "     	<rtdm:Header>"
				+ "      		<rtdm:Identify></rtdm:Identify><rtdm:ClientTimeZoneID>PRC</rtdm:ClientTimeZoneID>"
				+ "			</rtdm:Header>"
				+ "			<rtdm:Body>"
				+ "      		<rtdm:Data name=\"CUST_ID\">"
				+ "      			<rtdm:String><rtdm:Val>11111111111</rtdm:Val></rtdm:String>"
				+ "				</rtdm:Data>"
				+ "			</rtdm:Body>"
				+ "		</rtdm:Event>"
				+ "	</soapenv:Body>" 
				+ "</soapenv:Envelope>";
		
		// 采用SOAP1.1调用服务端，这种方式能调用服务端为soap1.1和soap1.2的服务
		doPostSoap1_1(postUrl, soapXml, "");
		// 采用SOAP1.2调用服务端，这种方式只能调用服务端为soap1.2的服务
		// doPostSoap1_2(postUrl, orderSoapXml, "order");
	}
}
