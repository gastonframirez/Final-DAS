package ar.edu.ubp.das.src.admin.actions;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import antlr.collections.List;
import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.db.Dao;
import ar.edu.ubp.das.mvc.db.DaoFactory;

public class AddComercioAction implements Action{

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, RuntimeException {
		// TODO Auto-generated method stub
		try {
			Dao daoComercio = DaoFactory.getDao( "Comercio", "admin" );
			
			//No controlo si no vienen porque son obligatorios en el form
			form.setItem("publicName", request.getParameter("publicName"));
			form.setItem("razonSocial", request.getParameter("companyName"));
			form.setItem("CUIT", request.getParameter("CUIT"));
			form.setItem("address", request.getParameter("address"));
			form.setItem("zipCode", request.getParameter("zipCode"));
			form.setItem("phone", request.getParameter("phone"));
			form.setItem("logoURL", request.getParameter("logoURL"));
		
			if(request.getParameter("totalCrawl")!=null) {
				form.setItem("totalCrawl", "1");
			}
			
			form.setItem("ppProd", request.getParameter("ppProd"));
			form.setItem("ppOffer", request.getParameter("ppOffer"));
			form.setItem("authToken", request.getParameter("authToken"));
			form.setItem("techID", request.getParameter("techID"));
			
			Map<String, String> classes = new HashMap<String, String>();
			classes.put("iterator", request.getParameter("cssIterator"));
			classes.put("name", request.getParameter("cssNombre"));
			if(request.getParameter("cssModel")!=null) {
				classes.put("model", request.getParameter("cssModel"));
			}else {
				classes.put("model", null);
			}
			if(request.getParameter("cssBrand")!=null) {
				classes.put("brand", request.getParameter("cssBrand"));
			}else{
				classes.put("brand", null);
			}
			classes.put("price", request.getParameter("cssPrice"));
			classes.put("imgURL", request.getParameter("cssImgURL"));
			classes.put("prodURL", request.getParameter("cssProductURL"));
			classes.put("pagNext", request.getParameter("paginationNext"));
			classes.put("pagParam", request.getParameter("paginationParam"));
			
			form.setItems("cssClasses", classes);
	
			
			
			Map<String, String> catIds = new HashMap<String, String>();
	
			
			if(request.getParameterValues("urlCategoriasIds")!=null) {
				String[] urlCategoriasIds = request.getParameterValues("urlCategoriasIds");
				String[] urlCategorias = request.getParameterValues("urlCategorias");
				for(Integer i=0; i<urlCategoriasIds.length;++i) {
					catIds.put(urlCategoriasIds[i], urlCategorias[i]);
				}
				form.setItems("urlCategories", catIds);
			}

			Map<String, DynaActionForm> services = new HashMap<String, DynaActionForm>();
//			System.out.println(request.getParameterValues("baseURL").length);
			if(request.getParameterValues("baseURL")!=null) {
				String[] baseURL = request.getParameterValues("baseURL");
				String[] servTipo = request.getParameterValues("servTipo");
				String[] ports = request.getParameterValues("port");
				String[] functions = request.getParameterValues("function");
				
				DynaActionForm service;
				Map<String, String> bases = new HashMap<String, String>();
				Map<String, String> puertos = new HashMap<String, String>();
				Map<String, String> funciones = new HashMap<String, String>();
				Map<String, String> tipos = new HashMap<String, String>();
				for(Integer i=0; i<baseURL.length;++i) {
					bases.put(servTipo[i], baseURL[i]);
					puertos.put(servTipo[i], ports[i]);
					funciones.put(servTipo[i], functions[i]);
					tipos.put(servTipo[i],servTipo[i]);
				}
				form.setItems("tipos", tipos);
				form.setItems("bases", bases);
				form.setItems("puertos", puertos);
				form.setItems("funciones", funciones);
			}
			
			
			Map<String, Boolean> needsCrawl = new HashMap<String, Boolean>();
			Map<String, Boolean> searchInName = new HashMap<String, Boolean>();
			
			if(request.getParameterValues("cssModelOptions")!=null) {
				String[] modelOptions = request.getParameterValues("cssModelOptions");
				for(String mOpt : modelOptions) {
					if(mOpt.equals("inTitle")) {
						searchInName.put("model", true);
					}else {
						needsCrawl.put("model", true);
					}
	
				}
			}
			if(request.getParameterValues("cssBrandOptions")!=null) {
				String[] brandOptions = request.getParameterValues("cssBrandOptions");
				for(String bOpt : brandOptions) {
					if(bOpt.equals("inTitle")) {
						searchInName.put("brand", true);
					}else {
						needsCrawl.put("brand", true);
					}
				}
			}
			if(request.getParameterValues("cssImgURLOptions")!=null) {
				String[] imgOptions = request.getParameterValues("cssImgURLOptions");
				for(String iOpt : imgOptions) {
					if(iOpt.equals("inTitle")) {
						searchInName.put("imgURL", true);
					}else {
						needsCrawl.put("imgURL", true);
					}
				}
			}
			
			if(needsCrawl.size()>0) {
				form.setBoolItems("needsCrawl", needsCrawl);
			}
			if(searchInName.size()>0) {
				form.setBoolItems("searchInName", searchInName);
			}
			
			
			if(request.getParameter("idComercio")!=null) {
//				System.out.println("Updating");
				daoComercio.update(form);
			}else {
//				System.out.println("Adding");
				daoComercio.insert(form);
			}
		}catch (SQLException ex){
			String excStr = ex.getMessage();
			System.out.println(excStr);
			DynaActionForm formLogs = new DynaActionForm();
			Dao daoLogs = DaoFactory.getDao( "Log", "admin" );
			formLogs.setItem("logStr", "Error al intentar agregrar un comercio");
			daoLogs.insert(formLogs);
			response.setStatus(400);
			if(excStr.contains("uq__comercio__1__end")) {
		    	request.setAttribute("errComercio", "dupR");
		    }else if(excStr.contains("uq__comercio__2__end")) {
		    	request.setAttribute("errComercio", "dupC");
		    }
		}
		
		
		
		return mapping.getForwardByName("success");
	}

}
