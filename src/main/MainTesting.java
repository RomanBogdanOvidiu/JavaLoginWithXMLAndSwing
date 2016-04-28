package main;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import uinterface.GraphInterface;

public class MainTesting {
	public static void main(String[] args)
			throws JAXBException, ParserConfigurationException, SAXException, IOException {
		GraphInterface sim = new GraphInterface();
		sim.setVisible(true);

		
	}
}
