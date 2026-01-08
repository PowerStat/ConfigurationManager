/*
 * Copyright (C) 2023-2026 Dipl.-Inform. Kai Hofmann. All rights reserved!
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements; and to You under the Apache License, Version 2.0.
 */
package de.powerstat.configuration.io.test;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.net.URI;

import org.junit.jupiter.api.Test;

import de.powerstat.configuration.Manager;
import de.powerstat.configuration.io.XMLWriter;
import de.powerstat.ddd.values.finance.BIC;


/**
 * XMLWriter tests.
 */
final class XMLWriterTests
 {
  /**
   * Test bic constant.
   */
  private static final String TEST_BIC = "test.bic";


  /**
   * Default constructor.
   */
  /* default */  XMLWriterTests()
   {
    super();
   }


  /**
   * Test write to.
   */
  @Test
  /* default */  void testWriteTo()
   {
    final XMLWriter pw = new XMLWriter();
    final Manager manager = new Manager();
    manager.register(TEST_BIC, BIC.class);
    manager.set(TEST_BIC, BIC.of("POWSDE30XXX"));
    final File file = new File("target/test.xml");
    final URI uri = file.toURI();
    pw.writeTo(manager, uri);
    assertTrue(file.exists(), "test.xml has not been created");
    // TODO Verify content
    assertTrue(file.delete(), "File could not be deleted");
   }

 }
