/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webapinobelprize;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author bergeron
 */
public class NobelPrizeController {
    static class NobelPrizeLaureate
    {
        private String firstname;
        private String surname;
        private String category;
        private int year;
        
        public NobelPrizeLaureate(String firstname, String surname, String category, int year)
        {
            this.firstname = firstname;
            this.surname = surname;
            this.category = category;
            this.year = year;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }
    
    private static String server = "http://api.nobelprize.org/v1/";

    private static String nobelPrizeRequest(String category, int year)
    {
        String requestUrlStr = server + "prize.json?category="+category+"&year="+year;
        System.out.println(requestUrlStr);
        
        try
        {
            URL requestURL = new URL(requestUrlStr);
            URLConnection connection = requestURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            
            String inputLine = "";
            String jsonOutput = "";
            
            while ((inputLine = in.readLine()) != null)
            {
                jsonOutput += inputLine;
            }
            
            in.close();
            
            return jsonOutput;            
        }
        catch(Exception e)
        {
            System.out.println();
            return null;
        } 
    }
    
    public static NobelPrizeLaureate[] getNobelPrizeLaureates(String category, int year)
    {
        String json = nobelPrizeRequest(category, year);
        NobelPrizeLaureate[] laureate = new NobelPrizeLaureate[3];

        JsonObject root = new JsonParser().parse(json).getAsJsonObject();
        
        JsonArray array = root.get("prizes").getAsJsonArray();
        
        for(int i = 0; i < array.size(); i++){
            JsonObject temp = array.get(i).getAsJsonObject();
            
            if(temp.get("year").getAsInt() == year && temp.get("category").getAsString().equals(category)) {
                JsonArray peopleList = temp.get("laureates").getAsJsonArray();
                for(int j = 0; j < peopleList.size(); j++) {
                    String firstname = peopleList.get(j).getAsJsonObject().get("firstname").getAsString();
                    String surname = peopleList.get(j).getAsJsonObject().get("surname").getAsString();
                    
                    laureate[j] = new NobelPrizeLaureate(firstname, surname, category, year);
                }
            }
        }

        return laureate;
    }
    
    
}

