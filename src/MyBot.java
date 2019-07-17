import java.io.IOException;
import org.jibble.pircbot.*;
public class MyBot extends PircBot{
    
    public MyBot() 
    {
        this.setName("API_Bot");
    }
    
    public void onMessage(String channel, String sender,
                       String login, String hostname, String message) 
    {
        String location = "";
        String searchby = "";
        if (message.contains("weather")) 
        {
            
            String[] input = message.split(" ");
            if(input.length == 2)
            {
                if (input[0].equals("weather")) 
                {
                    location = input[1];
                } 
                else
                {
                    location = input[0];
                }
                if(location.matches("^[a-zA-Z]*$"))
                {
                    searchby = "city";
                }
                else
                {
                    searchby = "zip";
                }
            }
            else if(input.length == 3)
            {
                
               if(input[0].matches("^[a-zA-Z]*$") && input[1].matches("^[a-zA-Z]*$") && input[2].matches("^[a-zA-Z]*$"))
               {
                   searchby = "city-state";
                   if (input[0].equals("weather")) 
                   {
                       location = input[1] + " " + input[2];
                   }
                   else
                   {
                        location = input[0] + " " + input[1];
                   }
                   
               }
               else
               {    
                    searchby = "coord";
                    if (input[0].equals("weather")) 
                    {
                        location = input[1] + " " + input[2];
                    }
                    else
                    {
                        location = input[0] + " " + input[1];
                    }
               }
            }
            else
            {
                sendMessage("#pircbot", "Invalid command!");
            }
             
                try 
                {
                    String result = weatherAPI.main(location, searchby);
                    sendMessage("#pircbot", "Current temperature in " + weatherAPI.location(result) + " is "+ weatherAPI.currtemp(result) + "°F");
                    sendMessage("#pircbot", "High in " + weatherAPI.location(result) + " will be "+ weatherAPI.high(result) + "°F");
                    sendMessage("#pircbot", "Low in " + weatherAPI.location(result) + " will be "+ weatherAPI.low(result) + "°F");
                    sendMessage("#pircbot", "Weather condition in " + weatherAPI.location(result) + ": "+ weatherAPI.weather(result));
                } 
                catch (IOException ex) 
                {
                    //Logger.getLogger(MyBot.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Exception error: " + ex);
                }
        }
        if (message.contains("XR")) 
        {
            String [] input = message.split(" ");
            String country1 = "";
            String country2 = "";
            String date = "";
            String searchBy = "";
            if(input.length==3)
            {
                searchBy = "current";
                if(input[0].equals("XR"))
                {
                    country1 = input[1];
                    country2 = input[2];
                }
                else
                {
                    country1 = input[0];
                    country2 = input[1];
                }
                try
                {
                    String result = exchangerateAPI.main(country1, country2, date, searchBy);

                    sendMessage("#pircbot","Exchange rate from "+ country1 + " to " + country2 + " is $"+exchangerateAPI.getRate1(result, country1, country2));
                    sendMessage("#pircbot","Exchange rate from "+ country2 + " to " + country1 + " is $"+exchangerateAPI.getRate2(result, country1, country2));
                }
                catch (IOException ex) 
                {
                    //Logger.getLogger(MyBot.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Exception error: " + ex);
                }
            }
            else if(input.length==4)
            {  
                searchBy = "past";
                if(input[0].equals("XR"))
                {
                    country1 = input[1];
                    country2 = input[2];
                    date = input[3];
                
                }
                else
                {
                    country1 = input[0];
                    country2 = input[1];
                    date = input[2];
                    
                }
                try
                {
                    String result = exchangerateAPI.main(country1, country2, date, searchBy);

                    sendMessage("#pircbot","Exchange rate from "+ country1 + " to " + country2 + " is "+ date + " was $" + exchangerateAPI.getpastRate1(result, country1, country2, date));
                    sendMessage("#pircbot","Exchange rate from "+ country2 + " to " + country1 + " is "+ date + " was $" + exchangerateAPI.getpastRate2(result, country1, country2, date));
                }
                catch (IOException ex) 
                {
                    //Logger.getLogger(MyBot.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Exception error: " + ex);
                }
                
            }
            
            
        }
        
   
    }
}