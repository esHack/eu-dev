package com.eu;

import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eu.csv.CSVData;
import com.eu.csv.CSVWriter;
import com.eu.exception.CSVException;
import com.eu.model.City;
import com.eu.model.Request;
import com.eu.util.Util;
import com.google.gson.JsonSyntaxException;

public class App 
{
  private static final String OUTPUT_FILENAME = "./data.csv";

  public static void main(String[] args) {
    Logger logger = LogManager.getLogger(App.class);

    String city = Util.parseInput(args);
    logger.info("invoking API client  for getting info about city  {}", city);

    try {
      Request request = new Request(Util.formRequestURL(city));
      List<CSVData> cities = City.fromJSON(request.get());

      if (cities.isEmpty()) {
        logger.info("no cities found. No CSV generated");
      }
      else{
      String outputFilename = CSVWriter.toFile(OUTPUT_FILENAME, cities);
      logger.info("added  {} records to csv {}", cities.size(), outputFilename);
      }
  
    }catch(MalformedInputException e){ 
    } catch (JsonSyntaxException e) {
      logger.error("unable  to parse API response");
    } catch (CSVException e) {
      logger.error("error while writing to file");
    }
    catch (IOException e) {
        logger.error("error in connecting to API " + Util.formRequestURL(city));
      }
  }
}
