package ar.edu.ubp.das.src.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class StatisticBannerTagHandler extends SimpleTagSupport {

	private String title;
	private String color;
	private String icon;
	private String value;
	
	
	public void setTitle(String title) {
		this.title = title;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}


	public void setValue(String value) {
		this.value = value;
	}


	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();

		JspWriter out = this.getJspContext().getOut();
		out.println("<div class=\"card bg-"+ this.color +" card-img-holder text-white\">");	
		out.println("<div class=\"card-body\">");
		out.println("<img src=\"/Maceo/img/dashboard/circle.svg\" class=\"card-img-absolute\" alt=\"circle-image\"/>");
		out.println("<h4 class=\"font-weight-normal mb-3\">"+ this.title);
		out.println("<i class=\"mdi mdi-"+ this.icon +" mdi-24px float-right\"></i></h4>");
		out.println("<h2 class=\"mb-5\">"+ this.value +"</h2>");
		out.println("</div>");
		out.println("</div>");
	}
}
