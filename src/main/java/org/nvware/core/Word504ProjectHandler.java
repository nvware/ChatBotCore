package org.nvware.core;

/**
 * @author hamid
 */

import com.darkprograms.speech.translator.GoogleTranslate;
import org.nvware.BotConfig;
import org.nvware.BuildVars;
import org.nvware.MachineEnum;
import org.nvware.core.services.FlashCardService;

import org.nvware.core.model.FlashCard;
import org.nvware.core.model.Word504;
import org.nvware.core.config.AppConfig;
import org.telegram.services.Emoji;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.services.LocalisationService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
//@Component
public class Word504ProjectHandler extends TelegramLongPollingBot {
    private final FlashCardService flashCardService;
    private final AppConfig appConfig;

    @Autowired
    public Word504ProjectHandler(FlashCardService flashCardService, AppConfig appConfig) throws TelegramApiException {
        this.flashCardService = flashCardService;
        this.appConfig = appConfig;
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
    }

    private static final String LOGTAG = "WORD504HANDLERS";

    @Override
    public String getBotUsername() {
        // TODO Auto-generated method stub
        return appConfig.getBotUsername();//BotConfig.USERNAMEMYPROJECT;
    }

    @Override
    public String getBotToken() {
        // TODO Auto-generated method stub
        return  appConfig.getBotToken();//BotConfig.TOKENMYPROJECT;
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public void onUpdateReceived(Update update) {
        if (BuildVars.debug)
            System.out.println("onUpdateReceived update : " + update);
        //check if the update has a message
        Message message = update.getMessage();
        if (update.hasMessage() && message.isCommand() && update.getMessage().hasText() ) {
            //check if the message has text. it could also  contain for example a location ( message.hasLocation() )                       
            String cmd = message.getText().toLowerCase();
            if (Commdand.islesson(cmd)) {
                String lesson = cmd.replace("/", "");
                //List<Word504> allWords = DatabaseManager.getInstance(MachineEnum.MAIN).getAllWordsByLessons(lesson);
                List<FlashCard> allWords = flashCardService.getAllWordsByLessons(lesson);
//                    message.getFrom().getId() todo
                String msg = listToText(allWords);
                sendTextMesage(new SendMessage(), message, msg);
            } else {
                if (Commdand.isHelp(cmd)) {
                    sendTextMesage(new SendMessage(), message,
                            "<strong> /h or /help \n "
                                    + "/start or /menu or /504 (Book words)\n "
                                    + "/gt(google translate)/lang(default fa)/word \n  "
                                    + "/save/left/right or /add/left/right (is unique)\n  "
                                    + "/update/left/right or /edit/left/right \n  "
                                    + "/delete/left or /del/left \n  "
                                    + " /?/left or /view/left for view</strong> ");
                } else if (Commdand.is504(cmd)) {
                    SendMessage sendMessageWithReplyKeyboardMarkup = new SendMessage();
                    sendMessageWithReplyKeyboardMarkup.setReplyMarkup(MainMenuKeyboard.getMainMenuKeyboard("en"));
                    sendTextMesage(sendMessageWithReplyKeyboardMarkup, message, "<strong>cmd :</strong> " + message.getText());
                } else if (Commdand.isGoogleTranslator(cmd)) {
                    //"/gt/lang/word" or "/gt/word";
                    Scanner scan = new Scanner(cmd).useDelimiter("/");   // initialize the string delimiter

                    scan.next();//ignore gt
                    String lang = "";
                    String word = "";
                    if (scan.hasNext()) {
                        lang = scan.next();
                    }
                    if (scan.hasNext()) {
                        word = scan.next();
                    } else {
                        word = lang;
                        lang = "fa";
                    }
                    try {
                        sendTextMesage(new SendMessage(), message, "<strong>Meaning :</strong> " + GoogleTranslate.translate(lang, word));
                    } catch (IOException ex) {
                        Logger.getLogger(Word504ProjectHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (Commdand.isView(cmd)) {
                    // "/?/left";
                    Scanner scan = new Scanner(cmd).useDelimiter("/");   // initialize the string delimiter       
                    scan.next();//ignore ?
                    String left = "";
                    String msg = "";
                    //log.error("Exception Message",e);// display error message to customer
                    //sendTextMesage(new SendMessage(), message,""+message.getChatId());
                    if (scan.hasNext()) {
                        left = scan.next();
                        try {
                            //List<FlashCard> allWords = DatabaseManager.getInstance(MachineEnum.MAIN).getAllWordsByLeft(left, (message.getFrom() != null ? message.getFrom().getId() : -1));
                            List<FlashCard> allWords = flashCardService.getAllWordsByLeftAndUserId(left, (message.getFrom() != null ? message.getFrom().getId() : -1));
                            msg = flashCardlistToText(allWords);
                        } catch (Exception e) {
                        }
                    }
                    sendTextMesage(new SendMessage(), message, msg);
                } else if (Commdand.isSave(cmd)) {///////////////  ????????????
                    // "/save/left/right or /add/left/right";
                    int rowAffected = saveWords(cmd, message.getFrom().getId());
                    sendTextMesage(new SendMessage(), message, String.format("<strong>%d row Affected </strong>", rowAffected));
                } else if (Commdand.isUpdate(cmd)) {///////////////  ????????????
                    // "/update/left/right or /edit/left/right";
                    int rowAffected = updateWords(cmd, message.getFrom().getId());
                    sendTextMesage(new SendMessage(), message, String.format("<strong>%d row Affected </strong>", rowAffected));
                } else if (Commdand.isDelete(cmd)) {///////////////  ????????????
                    // "/delete/left or /del/left";
                    int rowAffected = deleteWords(cmd, message.getFrom().getId());
                    sendTextMesage(new SendMessage(), message, String.format("<strong>%d row Affected </strong>", rowAffected));
                    ////////////////////////sendTextMesage(new SendMessage(), message, String.format("<strong>%d row Affected </strong>", 0));
                } else {
                    String word = cmd.replace("/", "");
                    if (word.length() == cmd.length())
                        try {
                            sendTextMesage(new SendMessage(), message, "<strong>Meaning :</strong> " + GoogleTranslate.translate("en", word));
                        } catch (IOException ex) {
                            Logger.getLogger(Word504ProjectHandler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
            }
        }//end  if()

    }//end onUpdateReceived()

    @SuppressWarnings("CallToPrintStackTrace")
    private String flashCardlistToText(List<FlashCard> allWords) {
        String result = "";
        for (FlashCard word : allWords) {
            synchronized (Thread.currentThread()) {
                try {
                    Thread.currentThread().wait(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            result += "<strong>" + word.getLeft() + " : </strong> " + word.getRight() + "\n";
        }
        return result;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    private String listToText(List<FlashCard> allWords) {
        String result = "";
        for (FlashCard word : allWords) {
            synchronized (Thread.currentThread()) {
                try {
                    Thread.currentThread().wait(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            result += "<strong>" + word.getLesson() + "-" + word.getRow() + ")" + word.getLeft() + ":</strong> " + word.getRight() + "\n";
        }
        return result;
    }

    private void sendTextMesage(SendMessage sendMessageRequest, Message message, String txt) {
        if (BuildVars.debug)
            System.out.println("mesage is : " + txt);
        sendMessageRequest.enableHtml(true);
        sendMessageRequest.setChatId(message.getChatId().toString()); //who should get the message? the sender from which we got the message...
        sendMessageRequest.setText(txt);
        try {
            sendApiMethod(sendMessageRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates); //To change body of generated methods, choose Tools | Templates.
    }

    private int saveWords(String cmd, Long userId) {
        Scanner scan = new Scanner(cmd).useDelimiter("/");   // initialize the string delimiter       
        scan.next();//ignore save or add 
        String left = "";
        String right = "";
        if (scan.hasNext()) {
            left = scan.next();
        }
        if (scan.hasNext()) {
            right = scan.next();
        }

        //int rowAffected = DatabaseManager.getInstance(MachineEnum.MAIN).saveWords(left, right, userId);
        int rowAffected = flashCardService.saveWords(left, right, userId);

        return rowAffected;
    }

    private int updateWords(String cmd, Long userId) {
        Scanner scan = new Scanner(cmd).useDelimiter("/");   // initialize the string delimiter       
        scan.next();//ignore save or add 
        String left = "";
        String right = "";
        if (scan.hasNext()) {
            left = scan.next();
        }
        if (scan.hasNext()) {
            right = scan.next();
        }
        //int rowAffected = DatabaseManager.getInstance(MachineEnum.MAIN).updateWords(left, right, userId);
        int rowAffected = flashCardService.updateWords(left, right, userId);
        return rowAffected;
    }

    private int deleteWords(String cmd, Long userId) {
        Scanner scan = new Scanner(cmd).useDelimiter("/");   // initialize the string delimiter       
        scan.next();//ignore save or add 
        String left = "";
        if (scan.hasNext()) {
            left = scan.next();
        }
        //int rowAffected = DatabaseManager.getInstance(MachineEnum.MAIN).deleteWords(left, userId);
        int rowAffected = flashCardService.deleteWords(left, userId);
        return rowAffected;
    }
}
