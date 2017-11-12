package br.com.jonglee.RedditBot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Class responsible for listen and response bot
 * @author Jong.Lee
 * @version 1.0
 * @since 2017-11-11
 */
public class JongRedditBot  extends TelegramLongPollingBot{

    @Override
    public void onUpdateReceived(Update update) {
    	RedditCollector reddit = new RedditCollector();
    	
        if (update.hasMessage() && update.getMessage().hasText()) {
        	
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            
            //start command
            if (update.getMessage().getText().equals("/start")) {
                SendMessage message = new SendMessage()
                        .setChatId(chat_id)
                        .setText("Bem vindo ao JongRedditBot, a minha função é fornecer "
                        		+ "informações sobre as threads que "
                        		+ "estão bombando(+ de 5000 votes) no momento em alguma subreddit."
                        		+ "\nPara o uso basta digitar o comando /NadaPraFazer (seguido do subreddit desejado)"
                        		+ "\nEx: /NadaPraFazer programming;dogs;brazil");
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } 
            //NadaPraFazer command
            else if (update.getMessage().getText().matches("/NadaPraFazer ([a-zA-Z0-9 ]+)([;]{1}[a-zA-Z0-9]+)*")) {
            	String justThreads = update.getMessage().getText().replaceAll("/NadaPraFazer ", "");
            	String threads[];
            	threads = justThreads.split(";");
            	
                try {
                	for (int i = 0; i < threads.length; i++) {
                		if(!reddit.collectData(threads[i]).isEmpty()) {
                			execute(mountMessage(reddit.collectData(threads[i]).toString()
                    				.replace("[", "").replace("]", "").replace(",", ""), chat_id)); 
                		} else {
                			execute(mountMessage("Nenhuma thread bombando em subreddit: " + threads[i], chat_id));
                		}                		               		
                	} 
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            //ajuda command
            else if (update.getMessage().getText().equals("/ajuda")) {
            	SendMessage message = new SendMessage()
            			.setChatId(chat_id)
            			.setText("Coloque /NadaPraFazer (seguido do subreddit desejado)\nEx: /NadaPraFazer programming;dogs;brazil");
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            //anything_else command
            else {
                SendMessage message = new SendMessage()
                        .setChatId(chat_id)
                        .setText("Precisa de /ajuda?");
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //mount message function
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
