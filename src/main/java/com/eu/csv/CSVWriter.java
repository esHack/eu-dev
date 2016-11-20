package com.eu.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.eu.exception.CSVException;

public class CSVWriter {
  private static final String LINE_SEPARATOR = System.lineSeparator();

  private static final Logger logger = LogManager.getLogger(CSVWriter.class);

  public static String toFile(String filename, List<CSVData> records) throws CSVException {
    File file = FileSystems.getDefault().getPath(filename).toFile();

    try {
      FileWriter fileWriter = new FileWriter(file);

      fileWriter.write(records.get(0).getCSVHeader());
      fileWriter.write(LINE_SEPARATOR);

      for (CSVData record : records) {
        fileWriter.write(record.getCSVString());
        fileWriter.write(LINE_SEPARATOR);
      }

      fileWriter.close();

      return file.getAbsolutePath();
    } catch (IOException e) {
      logger.error("error while writing to csv file", e);
      throw new CSVException("error while writing to csv file");
    }
  }
}
