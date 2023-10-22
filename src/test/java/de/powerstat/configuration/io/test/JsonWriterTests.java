/*
 * Copyright (C) 2023 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.configuration.io.test;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.net.URI;

import org.junit.jupiter.api.Test;

import de.powerstat.configuration.Manager;
import de.powerstat.configuration.io.JsonWriter;
import de.powerstat.validation.values.BIC;


/**
 * Json writer tests.
 */
final class JsonWriterTests
 {
  /**
   * Test bic constant.
   */
  private static final String TEST_BIC = "test.bic";


  /**
   * Default constructor.
   */
  /* default */  JsonWriterTests()
   {
    super();
   }


  /**
   * Test write to.
   */
  @Test
  /* default */  void testWriteTo()
   {
    final JsonWriter pw = new JsonWriter();
    final Manager manager = new Manager();
    manager.register(TEST_BIC, BIC.class);
    manager.set(TEST_BIC, BIC.of("POWSDE30XXX"));
    final File file = new File("target/test.json");
    final URI uri = file.toURI();
    pw.writeTo(manager, uri);
    assertTrue(file.exists(), "test.json has not been created");
    // TODO Verify content
    assertTrue(file.delete(), "File could not be deleted");
   }

 }
