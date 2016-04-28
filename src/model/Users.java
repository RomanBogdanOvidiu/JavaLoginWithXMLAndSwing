package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "users")
public class Users {

	@XmlElement(name = "user", type = User.class)
	private List<User> users = new ArrayList<User>();

	public Users() {
	}

	public Users(List<User> users) {
		this.users = users;
	}

	public List<User> getUserList() {
		return users;
	}

	public void setUserList(List<User> userList) {
		this.users = userList;
	}
}
