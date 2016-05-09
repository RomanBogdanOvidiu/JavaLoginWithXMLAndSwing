package model;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace = "model")
public class User {

	private String nume;
	private String prenume;
	private String userName;
	private String password;
	private String safePass;

	private Set<UserRole> userRole = new HashSet<UserRole>(0);

	public User(String nume, String prenume, String username, String password, String safePass) {
		this.userName = username;
		this.password = password;
		this.nume = nume;
		this.prenume = prenume;
		this.safePass = safePass;
	}

	public User() {
		userName = "";
		password = "";
		safePass = "";
		// TODO Auto-generated constructor stub
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
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

	public String getSafePass() {
		return safePass;
	}

	public void setSafePass(String safePass) {
		this.safePass = safePass;
	}

	@Override
	public String toString() {
		return "Users [nume=" + nume + ", prenume=" + prenume + ", userName=" + userName + ", password=" + password
				+ ", getUserRole=" + getUserRole() + "]";
	}
}
