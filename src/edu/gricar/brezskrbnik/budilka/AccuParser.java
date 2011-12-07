package edu.gricar.brezskrbnik.budilka;

import java.net.URL;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import android.app.Application;

import edu.gricar.brezskrbnik.ApplicationBrezskrbnik;

public class AccuParser {
    ApplicationBrezskrbnik app;
    public TagNode xmlCleaner(String url) {

        try {
            HtmlCleaner cleaner = new HtmlCleaner();
            TagNode root = cleaner.clean(new URL(url));
            return root;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        //		CleanerProperties props = new CleanerProperties();
        //		props.setTranslateSpecialEntities(true);
        //		props.setTransResCharsToNCR(true);
        //		props.setOmitComments(true);
        //		TagNode tagNode;
        //		try {
        //			tagNode = new HtmlCleaner(props).clean(new URL(url));
        //			return tagNode;
        //		} catch (MalformedURLException e) {
        //			e.printStackTrace();
        //		} catch (IOException e) {
        //			e.printStackTrace();
        //		}
        //		return null;
    }

    /**
     * 
     * @param node HTML5 node 
     * @param XPathExpression
     * @return HTML5 subnode
     */
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

    public AccuParser(ApplicationBrezskrbnik app){
        
        TagNode stran = xmlCleaner("http://www.accuweather.com/en-us/si/mozirje/mozirje/forecast.aspx");
        TagNode[] all = findInfo(stran, "//div[@id='content_640']//div[@class='fltLeft'][1]//div[@style='margin-bottom: 10px;']");

        Vreme[] vreme = new Vreme[all.length];
        for(int i=0; i<all.length; i++){
            String[] img_url = findInfo(all[i], "//div[@class='ForecastIcon']//img")[0].getAttributeByName("src").toString().split("/");
           
            TagNode[] info = findInfo(all[i], "//span");
            
            System.out.println(info[0].getText() + "\t" + info[5].getText().toString().replace("&deg;", "°") + "\t" + info[1].getText());
            vreme[i] = new Vreme(info[0].getText().toString(), info[5].getText().toString().replace("&deg;", "°"), info[3].getText().toString().replace("&deg;", "°"), info[1].getText().toString(), img_url[img_url.length-1]);
        }
        app.setVreme(vreme);
    }
    
}
