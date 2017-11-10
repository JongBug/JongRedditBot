package br.com.jonglee.RedditBot;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FailingHttpStatusCodeException, MalformedURLException, IOException, ParserConfigurationException, SAXException {
    	
//    	ApiContextInitializer.init();
//    	
//    	TelegramBotsApi botsApi = new TelegramBotsApi();
//    	
//        try {
//            botsApi.registerBot(new JongRedditBot());
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//        
        try (final WebClient webClient = new WebClient()) {
            final HtmlPage page = webClient.getPage("https://www.reddit.com/r/brasil/");
            webClient.waitForBackgroundJavaScript(10 * 1000);
//            final HtmlDivision div = page.getHtmlElementById("thing_t3_7b99x6");
            final HtmlDivision subreddit = page.getHtmlElementById("thing_t3_79gu0w");
//            final HtmlAnchor anchor = page.getAnchorByName("anchor_name");

          //Gerar e imprimir uma lista contendo número de upvotes, subreddit, título da thread, link para os comentários da thread, link da thread. 

//            final HtmlDivision divL = (HtmlDivision) page.getByXPath("//div[@data-fullname]").get(1);
            
            //adicionando todas as threads do subreddit;
            //ignorando a primeira thread de propagando i = 1;
            ArrayList<HtmlDivision> divs = new ArrayList<>();
            int divLen = page.getByXPath("//div[@data-score]").size();                        
            for (int i = 0; i < divLen; i++) {
            	divs.add((HtmlDivision) page.getByXPath("//div[@data-score]").get(i));            	
            }
            
            for (int i = 0; i < divs.size(); i++) {
            	if(200 < Integer.parseInt(divs.get(i).getAttribute("data-score"))) {
                	System.out.println(divs.get(i).getAttribute("data-score"));
                	System.out.println(divs.get(i).getAttribute("data-subreddit"));
                	System.out.println(divs.get(i).getAttribute("data-permalink"));
                	System.out.println(divs.get(i).getAttribute("data-url"));
            	}
            }
            
//            System.out.println(divs.get(0).getTagName());
//            System.out.println(divs.get(0).getNodeValue());
//            System.out.println(divs.get(0).getAttributesMap());
//            System.out.println(divs.get(0).getId());
//            System.out.println(divs.get(0).getTextContent());
//            System.out.println(divs.get(0).getAttribute("data-score"));
//            System.out.println(divs.get(0).getElementsByTagName("data-score"));
//            System.out.println(divs.get(0).getLastChild());
        }
    }

}
