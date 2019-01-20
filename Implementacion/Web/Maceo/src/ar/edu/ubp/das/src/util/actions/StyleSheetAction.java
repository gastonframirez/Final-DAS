package ar.edu.ubp.das.src.util.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ubp.das.mvc.action.Action;
import ar.edu.ubp.das.mvc.action.ActionMapping;
import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.ForwardConfig;
import ar.edu.ubp.das.mvc.config.ModuleConfigImpl;

public class StyleSheetAction implements Action {

	@Override
	public ForwardConfig execute(ActionMapping mapping, DynaActionForm form, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/css;charset=ISO-8859-1");
        try {
			PrintWriter out = response.getWriter();
	        try {
	        	if(request.getParameter("load") != null) {
	        		String root      = ModuleConfigImpl.getContext().getRealPath("/css/") + "/";
	        		String scripts[] = request.getParameter("load").split(",");
		        	for(String script : scripts) {
		        		try {
		        			List<String>  file  = Files.readAllLines(Paths.get(root + script + ".css"), Charset.defaultCharset());
			        		StringBuilder lines = new StringBuilder();
			        		for(String line : file) {
			        			lines.append(line);
			        		}
			        		out.println(lines);
		        		}
		        		catch(NoSuchFileException ex) { }
		        	}
	        	}
			}
	        finally {
	            out.close();
	        }
		}
		catch (IOException e) { }
		return null;
	}

}
