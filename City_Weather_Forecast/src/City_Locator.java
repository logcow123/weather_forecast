import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class City_Locator {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        

        JSONObject data = getApiCityData("Tokyo");
        App.ListKeysAndValues(data);


    }
    public static ArrayList<BigDecimal> getLongAndLat(String city)
    {
        JSONObject cityData = getApiCityData(city);
        Set<String> keys = cityData.keySet();

        ArrayList<BigDecimal> longAndLat = new ArrayList<BigDecimal>();
        if(keys.size() == 0)
        {
            return longAndLat;
        }else
        {
            longAndLat.add(cityData.getBigDecimal("latitude"));
            longAndLat.add(cityData.getBigDecimal("longitude"));
        }
        
        return longAndLat;
    }

    public static JSONObject getApiCityData(String city)
    {
        String apiUrl = "https://geocoding-api.open-meteo.com/v1/search?name=" + city + "&count=1&language=en&format=json";
        try
        {
            // create a new URL object that can be used with HttpURLConnection
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // sets the Method to GET to get data from the API
            conn.setRequestMethod("GET");

            // prints out 200 if the call was successful
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code Geolocate: " + responseCode);

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

            Set<String> keys = data.keySet();
            if(keys.size() == 1)
            {
                return new JSONObject();
            }else
            {
                JSONArray cities = data.getJSONArray("results");
                JSONObject cityData = cities.getJSONObject(0);
                return cityData;
            }
        }catch (Exception e)
        {
            System.out.println("Error: " + e);
        }
        return new JSONObject();
    }
}
