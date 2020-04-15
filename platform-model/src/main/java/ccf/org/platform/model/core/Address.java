package ccf.org.platform.model.core;

import ccf.org.platform.model.common.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "PL_ADDRESS")
public class Address extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pl_address_seq")
	@SequenceGenerator(name = "pl_address_seq", sequenceName = "PL_ADDRESS_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private long id;
	
	@Column(name = "CITY", nullable = false)
	private String city;
	
	@Column(name = "COUNTRY", nullable = false)
	private String country;
	
	@Column(name = "STREET", nullable = false)
	private String street;
	
	@Column(name = "CITY", nullable = false)
	private String streetNumber;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;
	
	@Override
	public Long getId() {
		return id;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setId(long id) {
		this.id = id;
	}
}
