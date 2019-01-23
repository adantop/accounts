package com.accounts.app.models.entities;

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

/**
 * Main entity of the application contains all 
 * the information of the account mapped to 
 * the table accounts
 * @author adantop
 */
@Entity
@Table(name = "accounts")
public class Account implements Serializable {
	
    private static final long serialVersionUID = 1L;

	/**
     * (Auto-managed) The primary key that identifies 
     * uniquely the entity, must be autogenerated
     * upon creation
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * (Required) Given name of the accountant must
	 * contain only letters, dots and spaces
	 */
	@Column(name="name")
	private String givenName;
	
	/**
	 * (Optional) The last name of the accountant, must
	 * contain only letters, dots and spaces
	 */
	@Column(name="lastname")
	private String lastName;
	
	/**
	 * (Required) The google email ID of the accountant, must comply
	 * with gmail email account type
	 */
	@Column(name="gaccount")
	private String gAccount;
	
	/**
	 * (Optional) Some more account related information not necessary,
	 * added for the purpose of having different details for the accounts
	 */
	@Column(name="DOB")
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	/**
	 * (Auto-managed) Required for history audit purposes 
	 */
	@Column(name="create_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTS;
	
	/**
	 * (Auto-managed) Required for history audit purposes 
	 */
	@Column(name="update_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTS;
	
	/**
	 *  Method for handling creation and update of the entity
	 *  must handle the setting of the create and update
	 *  timestamps as well as validaing the information available
	 *  in the entity
	 *  TODO: Add updateTS handling and email verification  
	 */
	@PrePersist
	public void prePersist() {
		this.createTS = new Date();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getgAccount() {
		return gAccount;
	}

	public void setgAccount(String gAccount) {
		this.gAccount = gAccount;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getCreateTS() {
		return createTS;
	}

	public void setCreateTS(Date createTS) {
		this.createTS = createTS;
	}

	public Date getUpdateTS() {
		return updateTS;
	}

	public void setUpdateTS(Date updateTS) {
		this.updateTS = updateTS;
	}

}