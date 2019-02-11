package ar.edu.ubp.das.src.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SideNavBarTagHandler extends SimpleTagSupport {
	
	private String add;
	private String home;
	private String category;
	private String categories;
	private String store;
	private String stores;
	private String listOf;
	private String orchConfig;
	
	
	
	public void setOrchConfig(String orchConfig) {
		this.orchConfig = orchConfig;
	}
	public void setListOf(String listOf) {
		this.listOf = listOf;
	}



	public void setAdd(String add) {
		this.add = add;
	}



	public void setHome(String home) {
		this.home = home;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public void setCategories(String categories) {
		this.categories = categories;
	}



	public void setStore(String store) {
		this.store = store;
	}



	public void setStores(String stores) {
		this.stores = stores;
	}



	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();

		
		JspWriter out = this.getJspContext().getOut();

		out.println("<nav class=\"sidebar sidebar-offcanvas\" id=\"sidebar\">");	
		out.println("<ul class=\"nav\">");
		out.println("<li class=\"nav-item border-top\">"
				+ "<a class=\"nav-link\" href=\"/Maceo/admin/Home.do\">"
				+ "<span class=\"menu-title\">"
				+ this.home
				+ "</span>"
				+ "<i class=\"mdi mdi-home menu-icon\"></i>"
				+ "</a>"
				+ "</li>");
		out.println("<li class=\"nav-item\">\n" + 
				"            <a class=\"nav-link\" href=\"/Maceo/admin/Comercios.do\">\n" + 
				"              <span class=\"menu-title\"> "+ this.listOf +" " + this.stores +"</span>\n" + 
				"              <i class=\"mdi mdi-format-list-numbers menu-icon\"></i>\n" + 
				"            </a>\n" + 
				"          </li>");
		out.println("<li class=\"nav-item\">\n" + 
				"            <a class=\"nav-link \" href=\"/Maceo/admin/Categorias.do\">\n" + 
				"              <span class=\"menu-title\">"+ this.listOf +" " + this.categories +"</span>\n" + 
				"              <i class=\"mdi mdi-apps menu-icon\"></i>\n" + 
				"            </a>\n" + 
				"          </li>");
		out.println("<li class=\"nav-item border-bottom\">\n" + 
				"            <a class=\"nav-link \" href=\"/Maceo/admin//Configuracion.do\">\n" + 
				"              <span class=\"menu-title\">Configuracion de Scraper</span>\n" + 
				"              <i class=\"mdi mdi-settings menu-icon\"></i>\n" + 
				"            </a>\n" + 
				"          </li>");
		out.println("<li class=\"nav-item sidebar-actions\">\n" + 
				"            <span class=\"nav-link text-capitalize\">\n" + 
				"              <a href=\"/Maceo/admin/AgregarComercio.do\" class=\"btn btn-block btn-md btn-gradient-primary mt-3\">+ "+ this.add +" " + this.store +"</a>\n" + 
				"            </span>\n" + 
				"          </li>");
		out.println("<li class=\"nav-item sidebar-actions\">\n" + 
				"            <span class=\"nav-link text-capitalize\"> \n" + 
				"              <a href=\"/Maceo/admin/AgregarCategoria.do\" class=\"btn btn-block btn-md btn-gradient-danger\">+ "+ this.add +" " + this.category +"</a>\n" + 
				"            </span>\n" + 
				"          </li>");

        out.println("</ul>");
        out.println("</nav>");
	}
}
