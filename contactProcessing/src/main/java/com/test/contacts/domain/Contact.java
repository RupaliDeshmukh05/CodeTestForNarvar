package com.test.contacts.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "contact")
public class Contact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1767826428798909234L;

	@Id
  	@GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Size(max = 32)
    @Column(name = "id", nullable = false, updatable = false)
  	private long id;
	
  	@NotNull
    @Size(max = 64)
    @Column(name = "name", nullable = false)
  	private String name;

  	@NotNull
    @Size(max = 64)
    @Column(name = "email", nullable = false)
    @Email
  	private String email;

  	@NotNull
    @Size(max = 64)
    @Column(name = "profession", nullable = false)
  	private String profession;
  	
 	@NotNull
 	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdOn", nullable = false)
  	private Date createdOn;
 	
  	public Contact(long id) {
  		this.id = id;
  	}
  	
    /**
     * Default Constructor
     */
	public Contact() {
		
	}

    /**
     * Overloaded constructor
     * @param name
     * @param email
     * @param profession
     */
	public Contact(String name, String email, String profession) {
		this.name = name;
		this.email = email;
		this.profession = profession;
		this.createdOn = new Date();
	}

	@PrePersist
    protected void onCreate() {
		createdOn = new Date();
    }
	
	public long getId() {
		return id;
	}
	
	//Since ID is auto generated, this can be made protected as well
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getProfession() {
		return profession;
	}
	
	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Date getCreatedOn() {
		return createdOn;
	}
	
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	@Override
	public String toString() {
		return "Contact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", profession='" + profession + '\'' +
                ", createdOn='" + createdOn + '\'' +
                '}';
	}  
}

