package sandboxjavaserver;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class AccountBean {

	@Id
	@GeneratedValue
	private Integer id;
	private String firstName;
	private String middleInitial;
	private String lastName;
	private String email;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="account_address",
			joinColumns = { @JoinColumn(name="account_id") },
			inverseJoinColumns = @JoinColumn( name="address_id")
			)
	private Set<AddressBean> addresses = new HashSet<AddressBean>();
	
	@OneToMany(mappedBy="anAccount", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<PhoneNumberBean> phoneNumbers;
	
	public AccountBean() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public Set<PhoneNumberBean> getPhoneNumbers() {
		return phoneNumbers;
	}

	public Set<AddressBean> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<AddressBean> addresses) {
		this.addresses = addresses;
	}
	
	public void setPhoneNumbers(Set<PhoneNumberBean> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
}
