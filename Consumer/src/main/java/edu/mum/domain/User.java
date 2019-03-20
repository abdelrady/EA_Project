package edu.mum.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.*;

@Entity
@Table(name = "USERS")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Long id = null;

	@Column(name = "FIRSTNAME", nullable = false)
//	@Size(min = 3, max = 20, message = "{size}")
	private String firstName;

	@Column(name = "LASTNAME", nullable = false)
//	@Size(min = 3, max = 20, message = "{size}")
	private String lastName;

	@Column(name = "EMAIL", nullable = false)
//	@NotNull()
//	@Email(message = "{email}")
	private String email;

	@Column(name = "IS_ADMIN", nullable = false)
	private boolean admin = false;

	@Column(nullable = false)
//	@NotEmpty
//	@Size(min = 4, max = 20, message = "{size}")
	String userName;

//	@NotEmpty
//	@Size(min = 8, max = 30, message = "{size}")
	@Column(name = "PASSWORD", nullable = false)
	String password;

	@Column
	Boolean enabled;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "authority_id", nullable = false)
	private Authority authority;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "owner")
	private Set<Order> orders = new HashSet<Order>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", admin=" + admin
				+ ", userName=" + userName + "]";
	}

}