package ar.edu.ubp.das.mvc.config;

public final class ForwardConfig {

	private String  name;
	private String  path;
	private boolean redirect;
	
	public String getName() {
		return name;
	}
	
	public String getPath() {
		return path;
	}
	
	public boolean isRedirect() {
		return redirect;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public void setRedirect(String redirect) {
		this.redirect = redirect.isEmpty() ? false : redirect.equals("true") ? true : false;
	}

	@Override
	public String toString() {
		return "ForwardConfig [name=" + name + ", path=" + path + ", redirect="
				+ redirect + "]";
	}

}
