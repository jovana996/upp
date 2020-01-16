package root.demo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Magazine {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private Long ISSNNumber;
	
	@OneToMany( cascade = CascadeType.ALL)
	private List<ScienceArea> scienceAreas;
	
	@Column
	private Long  membershipFeeAuthor;
	
	@Column
	private Long  membershipFeeReader;
	
	@Column
	private User editor;
	
	@OneToMany( cascade = CascadeType.ALL)
	private List<User> editorsScienceAreas;
	
	@OneToMany( cascade = CascadeType.ALL)
	private List<User> reviewers;
	
	@Column
	private boolean active;

	public Magazine() {
		super();
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

	public List<ScienceArea> getScienceAreas() {
		return scienceAreas;
	}

	public void setScienceAreas(List<ScienceArea> scienceAreas) {
		this.scienceAreas = scienceAreas;
	}

	public Long getMembershipFeeAuthor() {
		return membershipFeeAuthor;
	}

	public void setMembershipFeeAuthor(Long membershipFeeAuthor) {
		this.membershipFeeAuthor = membershipFeeAuthor;
	}

	public Long getMembershipFeeReader() {
		return membershipFeeReader;
	}

	public void setMembershipFeeReader(Long membershipFeeReader) {
		this.membershipFeeReader = membershipFeeReader;
	}

	public User getEditor() {
		return editor;
	}

	public void setEditor(User editor) {
		this.editor = editor;
	}

	public List<User> getEditorsScienceAreas() {
		return editorsScienceAreas;
	}

	public void setEditorsScienceAreas(List<User> editorsScienceAreas) {
		this.editorsScienceAreas = editorsScienceAreas;
	}

	public List<User> getReviewers() {
		return reviewers;
	}

	public void setReviewers(List<User> reviewers) {
		this.reviewers = reviewers;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
