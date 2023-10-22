/*
 * Copyright (C) 2023 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.configuration.io.test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.net.URI;

import org.junit.jupiter.api.Test;

import de.powerstat.configuration.Manager;
import de.powerstat.configuration.io.XMLReader;
import de.powerstat.validation.values.BIC;


/**
 * XML reader tests.
 */
final class XMLReaderTests
 {
  /**
   * Test bic constant.
   */
  private static final String TEST_BIC = "test.bic";


  /**
   * Default constructor.
   */
  /* default */  XMLReaderTests()
   {
    super();
   }


  /**
   * Read from tests.
   */
  @Test
  /* default */  void testReadFrom()
   {
    final XMLReader reader = new XMLReader();
    final Manager manager = new Manager();
    manager.register(TEST_BIC, BIC.class);
    final File file = new File("src/test/resources/test.xml");
    final URI uri = file.toURI();
    reader.readFrom(manager, uri);
    assertEquals("POWSDE30XXX", ((BIC)manager.get(TEST_BIC)).stringValue(), "BIC not as expected");
   }

 }
