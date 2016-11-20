package com.eu;

import static org.junit.Assert.*;
import org.junit.Test;

import com.eu.util.Util;

public class UtilTest {
  /**
   * test static `parseCity` method. This method parse the CLI arguments and extract the name of the requested city.
   */
  @Test
  public void testParseCity() {
    assertEquals(Util.parseInput(new String[]{}), "");
    assertEquals(Util.parseInput(new String[]{"Berlin"}), "Berlin");
    assertEquals(Util.parseInput(new String[]{"Los", "Angeles"}), "Los Angeles");
  }
}
