package root.demo.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
public class ScienceArea implements Serializable{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@ManyToMany(cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE
	    })
	    @JoinTable(name = "science_area_user",
	        joinColumns = @JoinColumn(name = "science_area_id"),
	        inverseJoinColumns = @JoinColumn(name = "user_id")
	    )
	private List<User> reviewers;

	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private User editor;

	public ScienceArea() {
		super();
	}
	public ScienceArea(String name) {
		super();
		this.name = name;
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
	public List<User> getReviewers() {
		return reviewers;
	}
	public void setReviewers(List<User> reviewers) {
		this.reviewers = reviewers;
	}
	public User getEditor() {
		return editor;
	}
	public void setEditor(User editor) {
		this.editor = editor;
	}
	@Override
	public String toString() {
		return "ScienceArea [id=" + id + ", name=" + name  + ", editor=" + editor + "]";
	}

}
