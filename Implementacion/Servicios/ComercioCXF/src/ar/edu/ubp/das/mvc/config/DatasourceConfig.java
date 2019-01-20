package ar.edu.ubp.das.mvc.config;

public final class DatasourceConfig {

	private String id;
	private String driver;
	private String url;
	private String username;
	private String password;
	
	public DatasourceConfig() {
		this.id = "default";
	}

	public String getId() {
		return id;
	}

	public String getDriver() {
		return driver;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "DatasourceConfig [id=" + id + ", driver=" + driver + ", url="
				+ url + ", username=" + username + ", password=" + password + "]";
	}

}
