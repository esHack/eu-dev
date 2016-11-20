package com.eu.model;

import com.eu.csv.CSVData;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;

/**
 * This class represents a City, it contains an id, a name, a type and a position. 
 *
 * 
 */
public class City implements CSVData {
  private static final String CSV_HEADER = "_id,name,type,latitude,longitude";

  private static final String TO_CSV_FORMAT = "%d,%s,%s,%f,%f";

  @SerializedName(value = "_id")
  private int id;

  @SerializedName(value = "name")
  private String name;

  @SerializedName(value = "type")
  private String type;

  @SerializedName(value = "geo_position")
  private GeoPosition position;

  /**
   * Get the id of the suggestion.
   *
   * @return the id of the suggestion
   */
  public int getId() {
    return this.id;
  }

  /**
   * Get the name of the suggestion.
   *
   * @return the name of the suggestion
   */
  public String getName() {
    return this.name;
  }

  /**
   * Get the type of the suggestion.
   *
   * @return the type of the suggestion
   */
  public String getType() {
    return this.type;
  }

  /**
   * Get the latitude of the suggestion.
   *
   * @return the latitude of the suggestion
   */
  public double getLatitude() {
    return this.position.getLatitude();
  }

  /**
   * Get the longitude of the suggestion.
   *
   * @return the longitude of the suggestion
   */
  public double getLongitude() {
    return this.position.getLongitude();
  }

  /**
   * Get the csv header of the suggestion.
   *
   * @return the csv header of the suggestion
   */
  public String getCSVHeader() {
    return CSV_HEADER;
  }

  /**
   * Get the csv string of the suggestion.
   *
   * @return the csv string of the suggestion
   */
  public String getCSVString() {
    return String.format(Locale.ROOT, TO_CSV_FORMAT, this.getId(), this.getName(), this.getType(), this.getLatitude(), this.getLongitude());
  }

  /**
   * Deserialize a json payload in a list of suggestion.
   *
   * @param json - the json payload
   * @return the list of locations
   * @throws JsonSyntaxException
   */
  public static List<CSVData> fromJSON(JSONArray json) throws JsonSyntaxException {
    Gson gson = new Gson();
    return gson.fromJson(json.toString(), new TypeToken<ArrayList<City>>(){}.getType());
  }
}

/**
 * This class represents a geo position, it is nested is the Suggestion class.
 *
 * @see com.eu.model.City
 * @author jordanabderrachid
 */
class GeoPosition {
  @SerializedName(value = "latitude")
  private double latitude;

  @SerializedName(value = "longitude")
  private double longitude;

  /**
   * Get the latitude of the geo position.
   *
   * @return the latitude of the geo position
   */
  public double getLatitude() {
    return this.latitude;
  }

  /**
   * Get the longitude of the geo position.
   *
   * @return the longitude of the geo position
   */
  public double getLongitude() {
    return this.longitude;
  }
}
