package src;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// import org.json.JSONObject;

public class Api {
    public static void main(String[] args)
    {
        String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current=temperature_2m,relative_humidity_2m,is_day,wind_speed_10m";

        try{
            // create a new URL object that can be used with HttpURLConnection
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // sets the Method to GET to get data from the API
            conn.setRequestMethod("GET");

            // prints out 200 if the call was successful
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Buffered Reader reads chunks of text at one time
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            // A string that is Mutable
            StringBuilder response = new StringBuilder();
            
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                response.append(inputLine);
            }
            in.close();

            System.out.println("====================================================");
            System.out.println("Raw Response: " + response.toString());


        }catch (Exception e)
        {
            System.out.println("Error: " + e);
        }
        
        
    }
}
