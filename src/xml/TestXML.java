package xml;

import java.io.File;

import xml.model.SimModel;

public class TestXML {

	public static void main (String[] args) {
		MainXML reader = new MainXML();
		File f = new File("data/GameOfLife.xml");
		SimModel s = reader.xmlRead(f);
		System.out.println(s.getMySimAuthor());
	}
}
