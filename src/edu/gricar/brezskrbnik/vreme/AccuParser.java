package edu.gricar.brezskrbnik.vreme;

import java.net.URL;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
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

        try {
            String kraj = ActivityVreme.kraj;
            kraj = kraj.replace(" ", "-");
            kraj = kraj.replace("è", "c");
            kraj = kraj.replace("ž", "z");
            kraj = kraj.replace("š", "s");
            kraj = kraj.replace("È", "c");
            kraj = kraj.replace("Ž", "z");
            kraj = kraj.replace("Š", "s");

            Translate.setKey("FFFA78669F3D3DBACCEC690DFA60E95A5F8D86FF");

            TagNode stran = xmlCleaner("http://www.accuweather.com/en-us/si/" + kraj + "/" + kraj + "/forecast.aspx");
            TagNode[] all = findInfo(stran, "//div[@id='content_640']//div[@class='fltLeft'][1]//div[@style='margin-bottom: 10px;']");

            Vreme[] vreme = new Vreme[all.length];
            for(int i=0; i<all.length; i++){
                String[] img_url = findInfo(all[i], "//div[@class='ForecastIcon']//img")[0].getAttributeByName("src").toString().split("/");

                TagNode[] info = findInfo(all[i], "//span");
                vreme[i] = new Vreme(info[0].getText().toString(), info[5].getText().toString().replace("&deg;", "°"), info[3].getText().toString().replace("&deg;", "°"), Translate.execute(info[1].getText().toString(), Language.SLOVENIAN) , img_url[img_url.length-1]);
            }
            app.setVreme(vreme);

        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
