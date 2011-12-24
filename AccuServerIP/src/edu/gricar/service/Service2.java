package edu.gricar.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class Service2 {
	
	public TagNode xmlCleaner(String url) {
		CleanerProperties props = new CleanerProperties();
		props.setTranslateSpecialEntities(true);
		props.setTransResCharsToNCR(true);
		props.setOmitComments(true);
		TagNode tagNode;
		try {
			tagNode = new HtmlCleaner(props).clean(new URL(url));
			return tagNode;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private TagNode[] findInfo(TagNode node, String XPathExpression) {
		TagNode[] description_node = null;
		Object[] object = null;
		try {
			object = node.evaluateXPath(XPathExpression);
			description_node = new TagNode[object.length];
			for(int i=0; i<object.length; i++){
				description_node[i] = (TagNode) object[i];
			}
		} catch (XPatherException e) {
			e.printStackTrace();
		}
		return description_node;
	}
	
	public String Parser(String kraj) throws Exception{
		
        kraj = kraj.replace(" ", "-");
        kraj = kraj.replace("è", "c");
        kraj = kraj.replace("ž", "z");
        kraj = kraj.replace("š", "s");
        kraj = kraj.replace("È", "c");
        kraj = kraj.replace("Ž", "z");
        kraj = kraj.replace("Š", "s");
		
		TagNode stran = xmlCleaner("http://www.accuweather.com/en-us/si/" + kraj + "/" + kraj + "/forecast.aspx");
        TagNode[] all = findInfo(stran, "//div[@id='content_640']//div[@class='fltLeft'][1]//div[@style='margin-bottom: 10px;']");

        Vreme[] vreme = new Vreme[all.length];
        for(int i=0; i<all.length; i++){
            String[] img_url = findInfo(all[i], "//div[@class='ForecastIcon']//img")[0].getAttributeByName("src").toString().split("/");
           
            TagNode[] info = findInfo(all[i], "//span");
            
            System.out.println(info[0].getText() + "\t" + info[5].getText().toString().replace("&deg;", "°") + "\t" + info[1].getText() + "\t" + img_url[img_url.length-1]);
            vreme[i] = new Vreme(info[0].getText().toString(), info[5].getText().toString().replace("&deg;", "°"), info[3].getText().toString().replace("&deg;", "°"), info[1].getText().toString(), img_url[img_url.length-1]);
            
           
            
        }
        
        Translate.setKey("FFFA78669F3D3DBACCEC690DFA60E95A5F8D86FF");
        
			//vreme[1].setOpis(Translate.execute(vreme[1].getOpis().toString(), Language.ENGLISH, Language.SLOVENIAN));
			String prevod = Translate.execute(vreme[1].getOpis().toString(), Language.SLOVENIAN);
			System.out.println(prevod);
			vreme[1].setOpis(prevod);
			
		
		
        return vreme[1].getSlika().toString() + ";" + vreme[1].getRealfeel().toString() + ";" + vreme[1].getOpis().toString();
		
	}
	
	public Service2() throws Exception{ 
		getVreme();
	}
	
	public static void main(String [] args) throws Exception{
		new Service2();
	}
	
	
	
	public String getVreme() throws Exception{
		return Parser("mozirje");
	}
	
}
