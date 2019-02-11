package ar.edu.ubp.das.src.admin.clients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ar.edu.ubp.das.mvc.action.DynaActionForm;

public class RestClient implements WSClient {
	

	@Override
	public Integer testConnection(String authToken, String url, String funcion) throws Exception {
		// TODO Auto-generated method stub
		HttpClient client = HttpClientBuilder.create().build();


		URI uri = URI.create(url+"/"+funcion);

		HttpGet req = new HttpGet();
		req.setURI(uri);
		req.setHeader("Authorization", authToken);

		client = HttpClientBuilder.create().build();
		HttpResponse resp;
		
		try {
			resp = client.execute(req);
			HttpEntity responseEntity = resp.getEntity();
			StatusLine responseStatus = resp.getStatusLine();
			String restResp = EntityUtils.toString(responseEntity);
			System.out.println(restResp);
			
			if(responseStatus.getStatusCode() != 404) {
				return 1;
			}
				
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}


	
}
