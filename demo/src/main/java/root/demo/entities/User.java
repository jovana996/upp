package root.demo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class User implements Serializable {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	private String city;
	
	@Column
	private String country;
	
	@Column
	private String title;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private Boolean reviewer;
	
	@Column
	private Boolean activatedAccount;
	
	@Column
	private Boolean approvedReviewer;
	
	@Column
	private String email;
	
	@Column (nullable = true)
	@OneToMany( cascade = CascadeType.ALL)
	private List<ScienceArea> sienceAreas;
	
	

	public User() {
		super();
		this.sienceAreas = new ArrayList();
		this.activatedAccount = false;
		this.approvedReviewer = false;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Boolean getReviewer() {
		return reviewer;
	}

	public void setReviewer(Boolean reviewer) {
		this.reviewer = reviewer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ScienceArea> getSienceAreas() {
		return sienceAreas;
	}

	public void setSienceAreas(ArrayList<ScienceArea> sienceAreas) {
		this.sienceAreas = sienceAreas;
	}

	public Boolean getActivatedAccount() {
		return activatedAccount;
	}

	public void setActivatedAccount(Boolean activatedAccount) {
		this.activatedAccount = activatedAccount;
	}

	public Boolean getApprovedReviewer() {
		return approvedReviewer;
	}

	public void setApprovedReviewer(Boolean approvedReviewer) {
		this.approvedReviewer = approvedReviewer;
	}
	
	
}
