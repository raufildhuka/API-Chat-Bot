import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.stream.Collectors;
import com.google.gson.*;
import java.io.IOException;

public class exchangerateAPI {
     public static String main(String country1, String country2,String date, String searchby) throws IOException
     {
        if(searchby == "current")
        {
        String myAPIurl = "https://free.currconv.com/api/v7/convert?q="+country1+"_"+country2+","+country2+"_"+country1+"&compact=ultra&apiKey=249760d030e8c621c2f0";
        URL url = new URL(myAPIurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String result = rd.lines().collect(Collectors.joining());
        return result;
        }
        else
        {
        String myAPIurl = "https://free.currconv.com/api/v7/convert?q="+country1+"_"+country2+","+country2+"_"+country1+"&compact=ultra&date="+date+"&apiKey=249760d030e8c621c2f0";
        URL url = new URL(myAPIurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String result = rd.lines().collect(Collectors.joining());
        return result;
            
        }
         
     }
     public static double getRate1(String json,String country1, String country2)
     {
         JsonObject object = new JsonParser().parse(json).getAsJsonObject();
         String code = country1+"_"+country2;
         double rate = object.getAsJsonPrimitive(code).getAsDouble();
         return rate;
     }
     public static double getRate2(String json,String country1, String country2)
     {
         JsonObject object = new JsonParser().parse(json).getAsJsonObject();
         String code = country2+"_"+country1;
         double rate = object.getAsJsonPrimitive(code).getAsDouble();
         return rate;
     }
     public static double getpastRate1(String json,String country1, String country2, String date)
     {
         JsonObject object = new JsonParser().parse(json).getAsJsonObject();
         String code = country1+"_"+country2;
         JsonObject price = object.getAsJsonObject(code);
         double rate = price.getAsJsonPrimitive(date).getAsDouble();
         return rate;
     }
     public static double getpastRate2(String json,String country1, String country2, String date)
     {
         JsonObject object = new JsonParser().parse(json).getAsJsonObject();
         String code = country2+"_"+country1;
         JsonObject price = object.getAsJsonObject(code);
         double rate = price.getAsJsonPrimitive(date).getAsDouble();
         return rate;
     }
     
    
    
}
