package root.demo.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import root.demo.enums.PaperState;

@Entity
public class Paper implements Serializable {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String title;
	
	@Column
	private String coAuthorName;
	
	@Column
	private String coAuthorEmail;
	
	@Column
	private String coAuthorAddress;
	
	@Column
	private String keyWords;
	
	@Column
	private String paperAbstract;
	
	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private ScienceArea scienceArea;
	
	@Column
	private String paper;
	
	@Column
	private Boolean accepted;
	
	@Column
	private PaperState state;
	
	@Column
	private String doi;
	
	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private User author;

	public Paper() {
		super();
		this.accepted = false;
	}

	public Paper(String title, String coAuthorName, String coAuthorEmail, String coAuthorAddress, String keyWords,
			String paperAbstract, ScienceArea scienceArea, String paper) {
		super();
		this.title = title;
		this.coAuthorName = coAuthorName;
		this.coAuthorEmail = coAuthorEmail;
		this.coAuthorAddress = coAuthorAddress;
		this.keyWords = keyWords;
		this.paperAbstract = paperAbstract;
		this.scienceArea = scienceArea;
		this.paper = paper;
		this.accepted = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCoAuthorName() {
		return coAuthorName;
	}

	public void setCoAuthorName(String coAuthorName) {
		this.coAuthorName = coAuthorName;
	}

	public String getCoAuthorEmail() {
		return coAuthorEmail;
	}

	public void setCoAuthorEmail(String coAuthorEmail) {
		this.coAuthorEmail = coAuthorEmail;
	}

	public String getCoAuthorAddress() {
		return coAuthorAddress;
	}

	public void setCoAuthorAddress(String coAuthorAddress) {
		this.coAuthorAddress = coAuthorAddress;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getPaperAbstract() {
		return paperAbstract;
	}

	public void setPaperAbstract(String paperAbstract) {
		this.paperAbstract = paperAbstract;
	}

	public ScienceArea getScienceArea() {
		return scienceArea;
	}

	public void setScienceArea(ScienceArea scienceArea) {
		this.scienceArea = scienceArea;
	}

	public String getPaper() {
		return paper;
	}

	public void setPaper(String paper) {
		this.paper = paper;
	}

	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

	public PaperState getState() {
		return state;
	}

	public void setState(PaperState state) {
		this.state = state;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	@Override
	public String toString() {
		return "Paper [id=" + id + ", title=" + title + ", coAuthorName=" + coAuthorName + ", coAuthorEmail="
				+ coAuthorEmail + ", coAuthorAddress=" + coAuthorAddress + ", keyWords=" + keyWords + ", paperAbstract="
				+ paperAbstract + ", scienceArea=" + scienceArea + ", paper=" + paper + ", accepted=" + accepted
				+ ", state=" + state + ", doi=" + doi + ", author=" + author + "]";
	}
}
