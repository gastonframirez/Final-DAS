package ar.edu.ubp.das.mvc.config;

public class ResourceBundleConfig {
	
	private String basename;

	public String getBasename() {
		return basename;
	}

	public void setBasename(String basename) {
		this.basename = basename;
	}

	@Override
	public String toString() {
		return "ResourceBundleConfig [basename=" + basename + "]";
	}

}
