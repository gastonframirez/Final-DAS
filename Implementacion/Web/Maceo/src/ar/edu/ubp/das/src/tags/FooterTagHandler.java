package ar.edu.ubp.das.src.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class FooterTagHandler extends SimpleTagSupport {
	
	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();

		JspWriter out = this.getJspContext().getOut();

		out.println("<footer class=\"footer\">");	
		out.println("<div class=\"d-sm-flex justify-content-center justify-content-sm-between\">");
		out.println("<span class=\"text-muted text-center text-sm-left d-block d-sm-inline-block\">Copyright © 2019 GFR.</span>" + "");
		out.println("<span class=\"float-none float-sm-right d-block mt-1 mt-sm-0 text-center\">Final Dise&ntilde;o Avanzado de Software</span>");
		out.println("</div>");
		out.println("</footer>");
	}
}
