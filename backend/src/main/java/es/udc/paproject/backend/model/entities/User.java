package es.udc.paproject.backend.model.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
public class User {
	
	public enum RoleType {VIEWER,TICKET_SELLER};

	private Long id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private RoleType role;

	private Set<Sale> sale = new HashSet<>();

	public User() {}

	public User(String userName, String password, String firstName, String lastName, String email) {

		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	@OneToMany(mappedBy="user")
	public Set<Sale> getSale() {
		return sale;
	}
	public void setSale(Set<Sale> sale) {
		this.sale = sale;
	}


}
