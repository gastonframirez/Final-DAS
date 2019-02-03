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
		
		Dao daoComercio = DaoFactory.getDao( "Comercio", "admin" );
		
		//No controlo si no vienen porque son obligatorios en el form
		form.setItem("publicName", request.getParameter("publicName"));
		form.setItem("razonSocial", request.getParameter("companyName"));
		form.setItem("CUIT", request.getParameter("CUIT"));
		form.setItem("address", request.getParameter("address"));
		form.setItem("zipCode", request.getParameter("zipCode"));
		form.setItem("phone", request.getParameter("phone"));
		form.setItem("logoURL", request.getParameter("logoURL"));
		form.setItem("baseURLOffers", request.getParameter("baseURLOffers"));
		form.setItem("portOffers", request.getParameter("portOffers"));
		form.setItem("functionOffers", request.getParameter("functionOffers"));
		form.setItem("baseURLTrsct", request.getParameter("baseURLTrsct"));
		form.setItem("portTrsct", request.getParameter("portTrsct"));
		form.setItem("functionTrsct", request.getParameter("functionTrsct"));
		form.setItem("ppProd", request.getParameter("ppProd"));
		form.setItem("ppOffer", request.getParameter("ppOffer"));
		form.setItem("authToken", request.getParameter("authToken"));
		form.setItem("techID", request.getParameter("techID"));
		
		Map<String, String> classes = new HashMap<String, String>();
		classes.put("iterator", request.getParameter("cssIterator"));
		classes.put("name", request.getParameter("cssNombre"));
		classes.put("model", request.getParameter("cssModel"));
		classes.put("brand", request.getParameter("cssBrand"));
		classes.put("price", request.getParameter("cssPrice"));
		classes.put("imgURL", request.getParameter("cssImgURL"));
		classes.put("prodURL", request.getParameter("cssProductURL"));
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
		
		try {
			if(request.getParameter("idComercio")!=null) {
				System.out.println("Updating");
				daoComercio.update(form);
			}else {
				System.out.println("Adding");
				daoComercio.insert(form);
			}
		}catch (SQLException ex){
			System.out.println(ex.getMessage());
		}
		
		
		
		return mapping.getForwardByName("success");
	}

}
