package ar.edu.ubp.das.src.clients;

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
import ar.edu.ubp.das.src.orchestrator.beans.OfertaResponseBean;
import ar.edu.ubp.das.src.orchestrator.beans.ResponseBean;
import ar.edu.ubp.das.src.orchestrator.forms.OfferForm;

public class RestClient implements WSClient {
	
	@Override
	public List<DynaActionForm> getOfertas(String authToken, String funcion, String url) {
		// TODO Auto-generated method stub
		
		HttpClient client = HttpClientBuilder.create().build();


		URI uri = URI.create(url+"/ofertas");

		HttpGet req = new HttpGet();
		req.setURI(uri);
		req.setHeader("Authorization", authToken);

		client = HttpClientBuilder.create().build();
		HttpResponse resp;
		List<DynaActionForm> ofertas = new LinkedList<DynaActionForm>();
		
		try {
			resp = client.execute(req);
			HttpEntity responseEntity = resp.getEntity();
			StatusLine responseStatus = resp.getStatusLine();
			String restResp = EntityUtils.toString(responseEntity);
			System.out.println(restResp);
			
			if(responseStatus.getStatusCode() != 200) {
				//Ver que hacer aca
				throw new RuntimeException(restResp);
			}
		
			
			Gson gson = new Gson();
			
			Type listType = new TypeToken<ArrayList<OfertaResponseBean>>(){}.getType();
			ArrayList<OfertaResponseBean> offers = gson.fromJson(restResp, listType);
			
			OfferForm oferta;
			for(OfertaResponseBean offer : offers) {
				oferta = new OfferForm();
				oferta.setStatus(offer.getStatus());
				oferta.setErrorMsg(offer.getErrorMsg());
				oferta.setFechaInicio(offer.getFechaInicio());
				oferta.setFechaFin(offer.getFechaFin());
				oferta.setIdOferta(offer.getIdOferta());
				oferta.setImageURL(offer.getImageURL());
				oferta.setUrl(offer.getOfertaURL());
				
				oferta.setStatus("200");
				ofertas.add(oferta);
			}
						
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ofertas;
	}

	@Override
	public void notificarTransaccion(DynaActionForm transaccion, String authToken, String url, String funcion) throws Exception{
		
		HttpClient client = HttpClientBuilder.create().build();


		URI uri = URI.create(url+"/ofertas");

		HttpPost req = new HttpPost();
		req.setURI(uri);
		req.setHeader("Authorization", authToken);

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("idTransaccion", transaccion.getItem("idTransaccion")));
		nvps.add(new BasicNameValuePair("fechaTransaccion", transaccion.getItem("fechaTransaccion")));
		nvps.add(new BasicNameValuePair("nombreCliente", transaccion.getItem("nombreCliente")));
		nvps.add(new BasicNameValuePair("apellidoCliente", transaccion.getItem("apellidoCliente")));
		nvps.add(new BasicNameValuePair("dniCliente", transaccion.getItem("dniCliente")));
		nvps.add(new BasicNameValuePair("emailCliente", transaccion.getItem("tipoTransaccion")));
		nvps.add(new BasicNameValuePair("tipoTransaccion", transaccion.getItem("tipoTransaccion")));
		if(transaccion.getItem("modeloProducto")!=null)
			nvps.add(new BasicNameValuePair("modeloProducto", transaccion.getItem("modeloProducto")));
		if(transaccion.getItem("precioProducto")!=null)
			nvps.add(new BasicNameValuePair("precioProducto",transaccion.getItem("precioProducto")));
		if(transaccion.getItem("idOferta")!=null)
			nvps.add(new BasicNameValuePair("idOferta", transaccion.getItem("idOferta")));
		nvps.add(new BasicNameValuePair("comision", transaccion.getItem("comision")));

		try {
			req.setEntity(new UrlEncodedFormEntity(nvps));
			
			client = HttpClientBuilder.create().build();
			HttpResponse resp = client.execute(req);

			HttpEntity responseEntity = resp.getEntity();
			StatusLine responseStatus = resp.getStatusLine();

			String restResp = EntityUtils.toString(responseEntity);
			
			Gson gson = new Gson();
			
			Type listType = new TypeToken<ArrayList<ResponseBean>>(){}.getType();
			ArrayList<ResponseBean> respuestas = gson.fromJson(restResp, listType);

			if(responseStatus.getStatusCode() != 200) {
				throw new RuntimeException(restResp);
			}
						
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	
}
