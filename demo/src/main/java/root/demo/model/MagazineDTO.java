package root.demo.model;

import java.util.ArrayList;
import java.util.List;

import root.demo.entities.Magazine;
import root.demo.entities.ScienceArea;
import root.demo.entities.User;


public class MagazineDTO {
	
	private Long id;

	private String name;
	

	private Long ISSNNumber;

	private List<String> scienceAreas = new ArrayList<String>();
	

	private Boolean  openAccess;
	
	private String editor;
	
	private List<String> editorsScienceAreas = new ArrayList<String>();
	

	private List<String> reviewers = new ArrayList<String>();


	public MagazineDTO() {
		super();
		
	}


	public MagazineDTO(Magazine magazine) {
	this.id = magazine.getId();
	this.name= magazine.getName();
	this.ISSNNumber = magazine.getISSNNumber();
	this.openAccess = magazine.getOpenAccess();
	for(User user : magazine.getEditorsScienceAreas()) {
		String editor = user.getFirstName() + " " + user.getLastName();
		this.editorsScienceAreas.add(editor);
	}
	for(User user : magazine.getReviewers()) {
		String reviewer = user.getFirstName() + " " + user.getLastName();
		this.reviewers.add(reviewer);
	}
	for(ScienceArea sa : magazine.getScienceAreas()) {
		this.scienceAreas.add(sa.getName());
	}
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getISSNNumber() {
		return ISSNNumber;
	}


	public void setISSNNumber(Long iSSNNumber) {
		ISSNNumber = iSSNNumber;
	}


	public List<String> getScienceAreas() {
		return scienceAreas;
	}


	public void setScienceAreas(List<String> scienceAreas) {
		this.scienceAreas = scienceAreas;
	}



	public Boolean getOpenAccess() {
		return openAccess;
	}


	public void setOpenAccess(Boolean openAccess) {
		this.openAccess = openAccess;
	}


	public String getEditor() {
		return editor;
	}


	public void setEditor(String editor) {
		this.editor = editor;
	}


	public List<String> getEditorsScienceAreas() {
		return editorsScienceAreas;
	}


	public void setEditorsScienceAreas(List<String> editorsScienceAreas) {
		this.editorsScienceAreas = editorsScienceAreas;
	}


	public List<String> getReviewers() {
		return reviewers;
	}


	public void setReviewers(List<String> reviewers) {
		this.reviewers = reviewers;
	}
	
	
}
