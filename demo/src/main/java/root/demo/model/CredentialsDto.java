package root.demo.model;

public class CredentialsDto {
	
	private String username;
	
	private String password;
	
	public CredentialsDto() {
		super();
	}

	public CredentialsDto(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
