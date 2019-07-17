import org.jibble.pircbot.*;

public class MyBotMain {
    
    public static void main(String[] args) throws Exception {
        
        // Now start our bot up.
        MyBot bot = new MyBot();
        
        // Enable debugging output.
        bot.setVerbose(true);
        
        // Connect to the IRC server.
        bot.connect("irc.freenode.net");

        // Join the #pircbot channel.
        bot.joinChannel("#pircbot");
        
        bot.sendMessage("#pircbot", "Hey! Enter weather followed by zipcode, city, city-country, or coordinates to get weather info.");
        bot.sendMessage("#pircbot", "You can also enter XR followed by two currency codes to get the exchange rate.");
        bot.sendMessage("#pircbot", "XR followed by two country symbols and a date(YYYY-MM-DD) will get the exhange rate on that date.");
        
    }
    
}