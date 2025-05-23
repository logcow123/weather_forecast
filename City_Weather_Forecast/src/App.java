import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.Iterator;
import java.util.ArrayList;
import java.math.BigDecimal;
import org.json.JSONObject;

// import org.json.JSONObject;

public class App {
    public static void main(String[] args)
    {
        ArrayList<BigDecimal> longNlat = new ArrayList<BigDecimal>();
        BigDecimal lat = new BigDecimal(38.67796);
        BigDecimal lon = new BigDecimal(-121.17606);
        longNlat.add(lat);
        longNlat.add(lon);
        JSONObject data = getWeatherData(longNlat);
        ListKeysAndValues(data);

        System.out.println("=========================================");

        DisplayCurrentData(data);
        
        
    }
    public static JSONObject getWeatherData(ArrayList longAndLat)
    {
        String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + "52.52" + "&longitude=" + "13.41" + "&current=temperature_2m,relative_humidity_2m,is_day,wind_speed_10m";

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

        String currentTemp = current.getBigDecimal("temperature_2m") + " " + current_units.getString("temperature_2m");
        String currentWind = current.getBigDecimal("wind_speed_10m") + " " + current_units.getString("wind_speed_10m");

        System.out.println("Current Conditions:");
        System.out.println("");
        System.out.println("Tempurature: " + currentTemp);
        System.out.println("Wind Speed: " + currentWind);
    }

    public static void ListKeysAndValues(JSONObject json) {
        
        Iterator<String> keys = json.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            Object value = json.get(key);
            System.out.println("Key: " + key + ", Value: " + value + ", Type: " + value.getClass().getSimpleName());
        }
        System.out.println();
    }
}
