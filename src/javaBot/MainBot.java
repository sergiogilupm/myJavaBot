/**
 * Java bot for IRC chat. Main module
 * 
 * @author Sergio Gil
 * @version 1.0
 * 
 */

package javaBot;
import org.jibble.pircbot.*;

public class MainBot extends PircBot{

    public static void main(String[] args) throws Exception {
        
        // Bot initialization
        MyBot bot = new MyBot();
        
        // Enable debugging output.
        bot.setVerbose(true);
        
        // Connect to the IRC server.
        bot.connect("irc.freenode.net");

        // Join the #pircbot channel.
        bot.joinChannel("#pircbot");     
    }
}
