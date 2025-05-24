import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONObject;

public class WeatherForcast {
    public static void main(String[] args) {
        
    }
    public static JSONObject getWeatherData(ArrayList<BigDecimal> longAndLat)
    {
        String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + longAndLat.get(0) + "&longitude=" + longAndLat.get(1) + "&current=temperature_2m,relative_humidity_2m,is_day,wind_speed_10m";

        try{
            // create a new URL object that can be used with HttpURLConnection
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // sets the Method to GET to get data from the API
            conn.setRequestMethod("GET");

            // prints out 200 if the call was successful
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code Weather: " + responseCode);

            // Buffered Reader reads chunks of text at one time
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            // A string that is Mutable
            StringBuilder response = new StringBuilder();
            
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject data = new JSONObject(response.toString());
            return data;

        }catch(Exception e)
        {
            System.out.println("Error: " + e);
        }
        return new JSONObject();
        
    }

    public static void DisplayCurrentData(JSONObject data)
    {
        JSONObject current = data.getJSONObject("current");
        JSONObject current_units = data.getJSONObject("current_units");

        BigDecimal temp = current.getBigDecimal("temperature_2m");
        float floatTemp = temp.setScale(2, RoundingMode.DOWN).floatValue();

        floatTemp = (floatTemp * (9.0f/5.0f)) + 32;
        
        String currentTemp = (floatTemp) + " F°"; 
        String currentWind = current.getBigDecimal("wind_speed_10m") + " " + current_units.getString("wind_speed_10m");
        String currentHumidity = current.getBigDecimal("relative_humidity_2m") + "%";

        System.out.println("Current Conditions:");
        System.out.println("");
        System.out.println("Tempurature: " + currentTemp);
        System.out.println("Wind Speed: " + currentWind);
        System.out.println("Humidity: " + currentHumidity);
    }
    
}
