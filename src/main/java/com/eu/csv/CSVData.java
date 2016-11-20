package com.eu.csv;

/**
 * Represents an object that can be serialized to a csv record.
 *
 */
public interface CSVData {

  /**
   * Get the csv header.
   *
   */
  String getCSVHeader();

  /**
   * Get the csv record in string format.
   *
   */
  String getCSVString();
}
