package handlingPack;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import model.User;

public interface XMLRepository {

	User findByUsername(String username) throws JAXBException, ParserConfigurationException, SAXException, IOException;
}
