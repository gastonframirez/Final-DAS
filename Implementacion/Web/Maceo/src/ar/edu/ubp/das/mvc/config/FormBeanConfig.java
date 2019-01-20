package ar.edu.ubp.das.mvc.config;

public final class FormBeanConfig {

	private String name;
	private String type;
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "FormBeanConfig [name=" + name + ", type=" + type + "]";
	}
	
}
