package ar.edu.ubp.das.src.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class LoaderTagHandler extends SimpleTagSupport {
	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();

		JspWriter out = this.getJspContext().getOut();

		out.println("<div class=\"loader-overlay\">");	
		out.println("<div class=\"circle-loader\"></div>");
		out.println("</div>");
	}
}