package root.demo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Subscription implements Serializable{

	    @Id
		@Column
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne(optional = false, fetch = FetchType.EAGER)
	    private User user;

	    @ManyToOne(optional = false, fetch = FetchType.EAGER)
	    private Magazine magazine;

	    @Column
	    private Boolean paid;

	    @Column
	    private Boolean canceled;

		public Subscription(User user, Magazine magazine, Boolean paid, Boolean canceled) {
			super();
			this.user = user;
			this.magazine = magazine;
			this.paid = paid;
			this.canceled = canceled;
		}

		public Subscription() {
			super();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Magazine getMagazine() {
			return magazine;
		}

		public void setMagazine(Magazine magazine) {
			this.magazine = magazine;
		}

		public Boolean getPaid() {
			return paid;
		}

		public void setPaid(Boolean paid) {
			this.paid = paid;
		}

		public Boolean getCanceled() {
			return canceled;
		}

		public void setCanceled(Boolean canceled) {
			this.canceled = canceled;
		}

	  
	}


