/*
 * Copyright (C) 2023 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.configuration.io.test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.junit.jupiter.api.Test;

import de.powerstat.configuration.Manager;
import de.powerstat.configuration.io.JsonReader;
import de.powerstat.validation.values.BIC;


/**
 * Json reader tests.
 */
public class JsonReaderTests
 {
  /**
   * Default constructor.
   */
  public JsonReaderTests()
   {
    super();
   }


  /**
   * Read from tests.
   *
   * @throws IOException IO exception
   */
  @Test
  public void readFrom() throws IOException
   {
    final JsonReader reader = new JsonReader();
    final Manager manager = new Manager();
    manager.register("test.bic", BIC.class);
    final File file = new File("src/test/resources/test.json");
    final URI uri = file.toURI();
    reader.readFrom(manager, uri);
    assertEquals("POWSDE30XXX", ((BIC)manager.get("test.bic")).stringValue(), "BIC not as expected");
   }

 }
