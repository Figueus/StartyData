package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class Connection {

	private static final String DEFAULT_ADDRESS = "http://localhost:1337/api";
	private String address;
	private CloseableHttpClient httpclient = HttpClients.createDefault();
	private CookieStore cookieStore = new BasicCookieStore();
	private HttpContext httpContext = new BasicHttpContext();
	
	public Connection(){
		address = DEFAULT_ADDRESS;
		httpContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
	}
	
	public Connection(String address){
		this.address = address;
		httpContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
	}
	
	public HttpPost CreateAddUserPost(String email, String password, String name){
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("email", email));
		formparams.add(new BasicNameValuePair("password", password));
		formparams.add(new BasicNameValuePair("name", name));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
		HttpPost httppost = new HttpPost(address+"/users/register");
		httppost.setEntity(entity);
		return httppost;
	}
	
	public CloseableHttpResponse ExecuteHttpRequestBase(HttpRequestBase request){
		try {
			return httpclient.execute(request, httpContext);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
