import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.stream.Collectors;
import com.google.gson.*;
import java.io.IOException;
public class weatherAPI {
    
    public static String main(String loc, String searchby) throws IOException{
        if(searchby == "city"){
        String myAPIurl = "http://api.openweathermap.org/data/2.5/weather?q=";
        String userInput = loc; // Read user input
        String myApiToken = "&APPID=e062a41ef6be67c70e0d122fb8e5a583";
        String weatherURL = myAPIurl + userInput + myApiToken;
        URL url = new URL(weatherURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String result = rd.lines().collect(Collectors.joining());
        return result;
        }
        else if (searchby == "zip")
        {
        String myAPIurl = "http://api.openweathermap.org/data/2.5/weather?zip=";
        String userInput = loc; // Read user input
        String myApiToken = "&APPID=e062a41ef6be67c70e0d122fb8e5a583";
        String weatherURL = myAPIurl + userInput + myApiToken;
        URL url = new URL(weatherURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String result = rd.lines().collect(Collectors.joining());
        return result;
            
        }
        else if(searchby == "coord")
        {
        String[] coord = loc.split(" ");
        String myAPIurl = "http://api.openweathermap.org/data/2.5/weather?lat=" + coord[0] + "&lon=" + coord[1];
        String myApiToken = "&APPID=e062a41ef6be67c70e0d122fb8e5a583";
        String weatherURL = myAPIurl + myApiToken;
        URL url = new URL(weatherURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String result = rd.lines().collect(Collectors.joining());
        return result;
             
        }
        else
        {
        String[] location = loc.split(" ");
        String myAPIurl = "http://api.openweathermap.org/data/2.5/weather?q="+location[0]+","+location[1];
        String myApiToken = "&APPID=e062a41ef6be67c70e0d122fb8e5a583";
        String weatherURL = myAPIurl + myApiToken;
        URL url = new URL(weatherURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String result = rd.lines().collect(Collectors.joining());
        return result;
            
        }
    }
   
    public static String currtemp(String json) {
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        JsonObject main = object.getAsJsonObject("main");
        double temp = main.get("temp").getAsDouble();
        temp = (temp - 273.15) * 1.8 + 32;
        temp = Math.round (temp * 10) / 10;  
        return temp + ""; 

}
    public static String high(String json) {
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        JsonObject main = object.getAsJsonObject("main");
        double high = main.get("temp_max").getAsDouble();
        high = (high - 273.15) * 1.8 + 32;
        high = Math.round (high * 10) / 10;  
        return high + ""; 

}
    public static String low(String json) {
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        JsonObject main = object.getAsJsonObject("main");
        double low = main.get("temp_min").getAsDouble();
        low = (low - 273.15) * 1.8 + 32;
        low = Math.round (low * 10) / 10;  
        return low + ""; 

}
    public static String weather(String json) {
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        String condition = "" + object.getAsJsonArray("weather").get(0).getAsJsonObject().get("main");
        return condition; 

}
    public static String location(String json) {
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        String location = object.get("name").getAsString();
        return location; 

}



    
}
