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
		
		//Chequear que vengan todos los parametros
		
		/*
		 * 
		 * companyName - razonSocial
		 * CUIT - CUIT
		 * address - direccion
		 * zipCode - cp
		 * phone - telefono
		 * logoURL - logoURL
		 * baseURLOffers - baseURLOffers
		 * portOffers - portOffers
		 * functionOffers - funcionOffers
		 * baseURLTrsct - baseURLTransacciones
		 * portTrsct - portTransacciones
		 * functionTrsct - funcionTransacciones
		 * ppProd - productComm
		 * ppOffer - offerComm
		 * urlCategorias.... - categoriaURL
		 * cssNombre - cssNombre
		 * cssModel - cssModelo
		 * cssModelInTitle - searchInName
		 * cssModelNeedsCrawl - needsCrawl
		 * cssBrand - cssMarca
		 * cssBrandInTitle - searchInName
		 * cssBrandNeedsCrawl - needsCrawl
		 * cssPrice - cssPrecio
		 * cssImgURL - cssImgURL
		 * cssImgURLInTitle - searchInName
		 * cssImgURLNeedsCrawl - needsCrawl
		 * cssProductURL - cssProdURL
		 * 
		 */

//
//		form.setItem("nombre", request.getParameter("companyName"));
//		form.setItem("CUIT", request.getParameter("CUIT"));
//		form.setItem("address", request.getParameter("direccion"));
//		form.setItem("zipCode", request.getParameter("cp"));
//		form.setItem("phone", request.getParameter("telefono"));
//		form.setItem("logoURL", request.getParameter("logoURL"));
//		form.setItem("baseURLOffers", request.getParameter("baseURLOffers"));
//		form.setItem("portOffers", request.getParameter("portOffers"));
//		form.setItem("functionOffers", request.getParameter("funcionOffers"));
//		form.setItem("baseURLTrsct", request.getParameter("baseURLTransacciones"));
//		form.setItem("portTrsct", request.getParameter("portTransacciones"));
//		form.setItem("functionTrsct", request.getParameter("funcionTransacciones"));
//		form.setItem("ppProd", request.getParameter("productComm"));
//		form.setItem("ppOffer", request.getParameter("offerComm"));
//		form.setItem("cssNombre", request.getParameter("cssNombre"));
//		form.setItem("cssModel", request.getParameter("cssModelo"));
//		form.setItem("cssBrand", request.getParameter("cssMarca"));
//		form.setItem("cssPrice", request.getParameter("cssPrecio"));
//		form.setItem("cssPrice", request.getParameter("cssImgURL"));
//		form.setItem("cssProductURL", request.getParameter("cssProdURL"));
		
		//urlCategorias
		//needsCrawl
		//searchInName
		/* cssModelInTitle - searchInName
		 * cssModelOptions - needsCrawl
		 * cssBrandInTitle - searchInName
		 * cssBrandNeedsCrawl - needsCrawl
		 * cssImgURLInTitle - searchInName
		 * cssImgURLNeedsCrawl - needsCrawl
		 */
		 
		Map<String, Boolean> needsCrawl = new HashMap<String, Boolean>();
		Map<String, Boolean> searchInName = new HashMap<String, Boolean>();
		
		if(request.getParameterValues("cssModelOptions")!=null) {
			String[] modelOptions = request.getParameterValues("cssModelOptions");
			for(String s : modelOptions) {
				if(s.equals("inTitle")) {
					searchInName.put("model", true);
				}else {
					needsCrawl.put("model", true);
				}
//				System.out.println(s);
			}
		}
		if(request.getParameterValues("cssBrandOptions")!=null) {
			String[] modelOptions = request.getParameterValues("cssBrandOptions");
			for(String s : modelOptions) {
				if(s.equals("inTitle")) {
					searchInName.put("brand", true);
				}else {
					needsCrawl.put("brand", true);
				}
//				System.out.println(s);
			}
		}
		if(request.getParameterValues("cssImgURLOptions")!=null) {
			String[] modelOptions = request.getParameterValues("cssImgURLOptions");
			for(String s : modelOptions) {
				if(s.equals("inTitle")) {
					searchInName.put("imgURL", true);
				}else {
					needsCrawl.put("imgURL", true);
				}
//				System.out.println(s);
			}
		}
		
		if(needsCrawl.size()>0) {
			form.setBoolItems("needsCrawl", needsCrawl);
		}
		if(searchInName.size()>0) {
			form.setBoolItems("searchInName", searchInName);
		}

		
		return mapping.getForwardByName("success");
	}

}
