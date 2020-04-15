package ccf.org.platform.model.core;

import ccf.org.platform.model.common.AbstractEntity;
import ccf.org.platform.model.converter.UserStatusConverter;
import ccf.org.platform.model.util.EUserStatus;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "PL_USER")
public class User extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "pl_user_seq")
	@SequenceGenerator(name = "pl_user_seq", sequenceName = "PL_USER_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private long id;
	
	@Column(name = "NAME", nullable = false, length = 50)
	private String name;
	
	@Column(name = "SURNAME", nullable = false, length = 50)
	private String surname;
	
	@Column(name = "EMAIL", nullable = false)
	private String email;
	
	@Column(name = "AGE")
	private int age;
	
	@Column(name = "BIRTHDAY", nullable = false)
	private Date birthday;
	
	@Column(name = "PHONE_NUMBER")
	private long phoneNumber;
	
	@Column(name = "STATUS", columnDefinition = "varchar(10) default 'A'")
	@Convert(converter = UserStatusConverter.class)
	private EUserStatus status;
	
	@OneToMany (mappedBy = "user", targetEntity = Address.class, orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<Address> addresses;

	@Override
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public EUserStatus getStatus() {
		return status;
	}

	public void setStatus(EUserStatus status) {
		this.status = status;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

}
