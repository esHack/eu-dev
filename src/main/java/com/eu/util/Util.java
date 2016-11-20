package com.eu.util;

/**
 * Class for all common functions
 *
 */
public class Util {

	private static final String URL_BASE = "http://api.goeuro.com/api/v2/position/suggest/en/";

	/**
	 * Parse the CLI arguments and returns the name of the requested city.
	 *
	 * @param arg the CLI arguments
	 * @return the name of the requested city
	 */
	public static String parseInput(String[] args) {
		return String.join(" ", args);
	}

	/**
	 * build the request url according to the provided city.
	 *
	 */
	public static String formRequestURL(String city) {
		return URL_BASE + city.replaceAll("\\s+","");
	}
}
