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
import de.powerstat.configuration.io.PropertiesReader;
import de.powerstat.validation.values.BIC;


/**
 * Properties reader tests.
 */
public class PropertiesReaderTests
 {
  /**
   * Default constructor.
   */
  public PropertiesReaderTests()
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
    final PropertiesReader reader = new PropertiesReader();
    final Manager manager = new Manager();
    manager.register("test.bic", BIC.class);
    final File file = new File("src/test/resources/test.properties");
    final URI uri = file.toURI();
    reader.readFrom(manager, uri);
    assertEquals("POWSDE30XXX", ((BIC)manager.get("test.bic")).stringValue(), "BIC not as expected");
   }

 }
