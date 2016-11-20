package com.eu.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;



/**
 * This class is used to call an URL.
 *
 */
public class Request {

  private static final Logger logger = LogManager.getLogger(Request.class);

  private String url;

  /**
   * The constructor.
   *
   * @param url - the url to call
   */
  public Request(String url) {
    this.url = url;
  }

  /**
   * Call the url by processing an HTTP request, it sets the read and connection timeout, set the expected content
   * type and the user agent. It then checks if the response content type is valid and returns the response payload.
   *
   * @return the response payload
   */
  public JSONArray get() throws MalformedURLException, IOException  {
	  logger.info("Connecting to URL: " + url);
	  InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONArray json = new JSONArray(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
  }
  
  private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }
}
