package handlingPack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.User;
import model.Users;

public class UserHandler {
	// Export
	public static void marshal(List<User> users, File selectedFile) throws IOException, JAXBException {
		JAXBContext context;
		BufferedWriter writer = null;
		writer = new BufferedWriter(new FileWriter(selectedFile));
		context = JAXBContext.newInstance(Users.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(new Users(users), writer);
		writer.close();
	}

	// Import
	public static List<User> unmarshal(File importFile) throws JAXBException {
		Users users = new Users();

		JAXBContext context = JAXBContext.newInstance(Users.class);
		Unmarshaller um = context.createUnmarshaller();
		users = (Users) um.unmarshal(importFile);

		return users.getUserList();
	}
}
