package ar.edu.ubp.das.src.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SearchTagHandler extends SimpleTagSupport {
	private String searchText;
	
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		
		JspWriter out = this.getJspContext().getOut();

		out.println("<div class=\"loader-overlay\">");	
		out.println("<p class=\"searchText\">" + this.searchText + "</p>");
		out.println("<form method=\"get\" class=\"searchForm\" action=\"/Maceo/productos/Buscar.do\">");
		out.println("<input id=\"searchStr\" type=\"text\" class=\"searchInput\" value=\"\" name=\"searchStr\"/>");
		out.println("<i class=\"mdi mdi-magnify searchIcon\"></i>");
		out.println("</form>");
		out.println("<div class=\"closeSearch\">");
		out.println("<i class=\"mdi mdi-close-circle-outline\"></i>");
		out.println("</div>");
		out.println("</div>");
	}
}