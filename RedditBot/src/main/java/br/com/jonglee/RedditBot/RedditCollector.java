package br.com.jonglee.RedditBot;

import java.util.ArrayList;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * Class responsible for collect reddit data
 * @author Jong.Lee
 * @version 1.0
 * @since 2017-11-11
 */
public class RedditCollector {

	public ArrayList<DataReddit> collectData(String subreddit) {
		ArrayList<DataReddit> redditList = new ArrayList<>();
		
		try (final WebClient webClient = new WebClient()) {
			String redditUrl = "https://www.reddit.com";
			String url = redditUrl + "/r/" + subreddit;
			
			webClient.getOptions().setCssEnabled(false);//if you don't need css
			webClient.getOptions().setJavaScriptEnabled(false);//if you don't need js
			final HtmlPage page = webClient.getPage(url);
			webClient.waitForBackgroundJavaScript(10 * 1000);			
			
			// get all threads in Subreddits
			int divLen = page.getByXPath("//div[@data-score]").size();
			for (int i = 0; i < divLen; i++) {
				
				if (5000 < Integer.parseInt(((HtmlDivision) page
						.getByXPath("//div[@data-score]")
						.get(i)).getAttribute("data-score"))) {
					DataReddit redditData = new DataReddit();
					//upvotes
					redditData.setUpvote(((HtmlDivision) page.getByXPath("//div[@data-score]").get(i))
							.getAttribute("data-score"));
					//subreddit
					redditData.setSubreddit(((HtmlDivision) page.getByXPath("//div[@data-score]").get(i))
							.getAttribute("data-subreddit"));
					//título da thread
					redditData.setThreadTitle(((HtmlAnchor) page.getByXPath("//a[contains(@class, 'title may-blank')]").get(i))
							.getTextContent());
					//link da thread
					redditData.setLinkThread(((HtmlDivision) page.getByXPath("//div[@data-score]").get(i))
							.getAttribute("data-url"));
					//link para os comentários da thread
					redditData.setLinkComments(redditUrl + ((HtmlDivision) page.getByXPath("//div[@data-score]").get(i))
							.getAttribute("data-permalink"));

					redditList.add(redditData);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return redditList;
	}

}
