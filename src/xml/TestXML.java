package xml;

import java.io.File;
import xml.model.SimModel;

public class TestXML {

	public static void main (String[] args) {
		MainXML reader = new MainXML();
		File f = new File("data/PredPrey.xml");
		SimModel s = reader.xmlRead(f);
		System.out.println(s.getMyEmptyState().getMyColor());
		int[] l = new int[4];
		int[] m = new int[4];
		if (l[0] == 0) {
		System.out.println("fuck");}
	}
}