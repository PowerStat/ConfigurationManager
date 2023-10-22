/*
 * Copyright (C) 2023 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.configuration.io.test;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.net.URI;

import org.junit.jupiter.api.Test;

import de.powerstat.configuration.Manager;
import de.powerstat.configuration.io.PropertiesWriter;
import de.powerstat.validation.values.BIC;


/**
 * Properties writer tests.
 */
final class PropertiesWriterTests
 {
  /**
   * Test bic constant.
   */
  private static final String TEST_BIC = "test.bic";


  /**
   * Default constructor.
   */
  /* default */  PropertiesWriterTests()
   {
    super();
   }


  /**
   * Test write to.
   */
  @Test
  /* default */  void testWriteTo()
   {
    final PropertiesWriter pw = new PropertiesWriter();
    final Manager manager = new Manager();
    manager.register(TEST_BIC, BIC.class);
    manager.set(TEST_BIC, BIC.of("POWSDE30XXX"));
    final File file = new File("target/test.properties");
    final URI uri = file.toURI();
    pw.writeTo(manager, uri);
    assertTrue(file.exists(), "test.properties has not been created");
    // TODO Verify content
    assertTrue(file.delete(), "File could not be deleted");
   }

 }
