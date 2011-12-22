package edu.gricar.brezskrbnik.widget;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vijay

 *
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RSSReader {

	private static RSSReader instance = null;

	public RSSReader() {
	}

	public static RSSReader getInstance() {
		if (instance == null) {
			instance = new RSSReader();
		}
		return instance;
	}

	public List<String> readNews() {
		List<String> news = new ArrayList<String>();
		try {

			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			URL u = new URL("http://www.feri.uni-mb.si/rss/novice.xml"); // your feed url
			
			/*BufferedReader r = new BufferedReader(
	                new InputStreamReader(u.openStream(), "UTF-8"));

			String document = "";
			String line = "";
			while((line = r.readLine())!=null)
				document += line;
			
			document = document.replace("&#353;", "s");
			document = document.replace("&#352;", "S");
			document = document.replace("&#269;", "c");
			document = document.replace("&#268;", "C");
			document = document.replace("&#382;", "z");
			document = document.replace("&#381;", "Z");
			
			Document doc = builder.parse(document);*/
			Document doc = builder.parse(u.openStream());
			
			System.out.println("Encoding: "+doc.getXmlEncoding());
			
			NodeList nodes = doc.getElementsByTagName("item");

			for (int i = 0; i < nodes.getLength(); i++) {

				Element element = (Element) nodes.item(i);
				news.add(getElementValue(element, "title"));
//				System.out.println("Title: "
//						+ getElementValue(element, "title"));
				/*System.out.println("Link: " + getElementValue(element, "link"));
				System.out.println("Publish Date: "
						+ getElementValue(element, "pubDate"));
				System.out.println("author: "
						+ getElementValue(element, "dc:creator"));
				System.out.println("comments: "
						+ getElementValue(element, "wfw:comment"));
				System.out.println("description: "
						+ getElementValue(element, "description"));
				System.out.println();*/
			}// for
		}// try
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return news;

	}

	private String getCharacterDataFromElement(Element e) {
		try {
			Node child = e.getFirstChild();
			if (child instanceof CharacterData) {
				CharacterData cd = (CharacterData) child;
				return cd.getData();
			}
		} catch (Exception ex) {

		}
		return "";
	} // private String getCharacterDataFromElement

	protected float getFloat(String value) {
		if (value != null && !value.equals("")) {
			return Float.parseFloat(value);
		}
		return 0;
	}

	protected String getElementValue(Element parent, String label) {
		return getCharacterDataFromElement((Element) parent
				.getElementsByTagName(label).item(0));
	}

	public static void main(String[] args) {
		RSSReader reader = RSSReader.getInstance();
		reader.readNews();
	}
}