package com.eu;

import com.eu.csv.CSVData;
import com.eu.model.City;

import org.json.JSONArray;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CityTest {

  @Test
  public void testGetCSVHeader() {
    City suggestion = new City();
    assertEquals(suggestion.getCSVHeader(), "_id,name,type,latitude,longitude");
  }

  @Test
  public void testGetCSVString() {
    String rawJSON = "[{\"_id\":379604,\"name\":\"Lyon\",\"type\":\"location\",\"geo_position\":{\"latitude\":45.748460,\"longitude\":4.846710}}]";
    JSONArray arr = new JSONArray(rawJSON); 
    List<CSVData> suggestions = City.fromJSON(arr);

    assertEquals(suggestions.get(0).getCSVString(), "379604,Lyon,location,45.748460,4.846710");
  }

  

  @Test
  public void testFromJSONEmptyArray() {
    String nullJSON = "[]";
    JSONArray arr = new JSONArray(nullJSON); 
    List<CSVData> cities = City.fromJSON(arr);

    assertTrue(cities.isEmpty());
  }
  
  @Test
  public void positiveTestCase() {
    String testJSON = "[{\"_id\":376217,\"key\":null,\"name\":\"Berlin\",\"fullName\":\"Berlin, Germany\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Germany\",\"geo_position\":{\"latitude\":52.52437,\"longitude\":13.41053},\"locationId\":8384}]";
    JSONArray arr = new JSONArray(testJSON); 
    City suggestion = (City)City.fromJSON(arr).get(0);

    assertEquals(suggestion.getId(), 376217);
    assertEquals(suggestion.getName(), "Berlin");
    assertEquals(suggestion.getLatitude(), 52.52437, 0.0000001);
    assertEquals(suggestion.getLongitude(), 13.41053, 0.0000001);
  }
}
