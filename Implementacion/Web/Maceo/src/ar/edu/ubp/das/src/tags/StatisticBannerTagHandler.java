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
	private Boolean isMoney=false;
	
	
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

	public void setIsMoney(Boolean isMoney) {
		this.isMoney = isMoney;
	}


	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		
		JspWriter out = this.getJspContext().getOut();
		if(this.color.equals("gradient-secondary") || this.color.equals("gradient-warning")) {
			out.println("<div class=\"card bg-"+ this.color +" card-img-holder\">");	
		}else {
			out.println("<div class=\"card bg-"+ this.color +" card-img-holder text-white\">");	
		}
		out.println("<div class=\"card-body\">");
		out.println("<img src=\"/Maceo/img/dashboard/circle.svg\" class=\"card-img-absolute\" alt=\"circle-image\"/>");
		out.println("<h4 class=\"font-weight-normal mb-3\">"+ this.title);
		if(this.icon!=null) {
			out.println("<i class=\"mdi mdi-"+ this.icon +" mdi-24px float-right\"></i></h4>");
		}
		out.println("<h2 class=\"mb-5\">");
		if(this.isMoney!=null && this.isMoney) {
			out.println("$");
		}
		out.println(this.value +"</h2>");
		
		
		out.println("</div>");
		out.println("</div>");
	}
}
