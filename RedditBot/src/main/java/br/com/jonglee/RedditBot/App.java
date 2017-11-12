package br.com.jonglee.RedditBot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Class main to bot
 * @author Jong.Lee
 * @version 1.0
 * @since 2017-11-11
 */
public class App 
{
    public static void main( String[] args ) {
    	
    	ApiContextInitializer.init();
    	TelegramBotsApi botsApi = new TelegramBotsApi();
    	
        try {
            botsApi.registerBot(new JongRedditBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
