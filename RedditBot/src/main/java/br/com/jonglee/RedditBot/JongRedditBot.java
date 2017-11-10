package br.com.jonglee.RedditBot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class JongRedditBot  extends TelegramLongPollingBot{

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
        	
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            
            if (update.getMessage().getText().equals("/start")) {
                SendMessage message = new SendMessage()
                        .setChatId(chat_id)
                        .setText("You send /start");
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } 
            else if (update.getMessage().getText().matches("/NadaPraFazer ([a-zA-Z0-9 ]+)([;]{1}[a-zA-Z0-9]+)*")) {
            	String justThreads = update.getMessage().getText().replaceAll("/NadaPraFazer ", "");
            	String threads[];
            	threads = justThreads.split(";");
            	
            	//Gerar e imprimir uma lista contendo número de upvotes, subreddit, título da thread, link para os comentários da thread, link da thread. 
            	//Essa parte pode ser um CLI simples, desde que a formatação da impressão fique legível.
            	//upvotes subreddit título link link
            	
                try {
                	for (int i = 0; i < threads.length; i++) {
                		execute(mountMessage(threads[i], chat_id));
                	} 
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (update.getMessage().getText().equals("/help")) {
            	SendMessage message = new SendMessage()
            			.setChatId(chat_id)
            			.setText("Try /NadaPraFaze programming;dogs;brazil");
            }
            else {
                SendMessage message = new SendMessage()
                        .setChatId(chat_id)
                        .setText("Need /help?");
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public SendMessage mountMessage(String context,long chat_id) {
        SendMessage message = new SendMessage()
                .setChatId(chat_id)
                .setText(context);
    	return message;
    }
    
    @Override
    public String getBotUsername() {
        // TODO
    	return "JongRedditBot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "483239141:AAEfITiyynCCLDYAd-GLD1KOIchyS9oj1BA";
    }

}
