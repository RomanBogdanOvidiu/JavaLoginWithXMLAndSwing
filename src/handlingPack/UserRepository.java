package handlingPack;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.UnmarshallerHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import model.User;
import model.Users;

public class UserRepository implements XMLRepository {

	public User findByUsername(String username)
			throws JAXBException, ParserConfigurationException, SAXException, IOException {

		JAXBContext context = JAXBContext.newInstance(Users.class);
		Unmarshaller um = context.createUnmarshaller();
		UnmarshallerHandler handler = um.getUnmarshallerHandler();

		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		XMLReader xr = sp.getXMLReader();
		xr.setContentHandler(handler);
		xr.parse("users.xml");
		Users users = (Users) handler.getResult();

		System.out.println("*******************************JAXB exception");
		for (User u : users.getUserList()) {

			System.out.println("******************************Ion o intrat in formatie");
			if (u.getUserName().equals(username))
				return u;

		}

		return new User();
	}

}
