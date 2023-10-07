/*
 * Copyright (C) 2023 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.configuration.io.test;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;

import de.powerstat.configuration.Manager;
import de.powerstat.configuration.io.XMLWriter;
import de.powerstat.validation.values.BIC;


/**
 * XMLWriter tests.
 */
public class XMLWriterTests
 {
  /**
   * Default constructor.
   */
  public XMLWriterTests()
   {
    super();
   }


  /**
   * Test write to.
   *
   * @throws URISyntaxException URI syntax excption
   * @throws MalformedURLException Malformed URL exception
   */
  @Test
  public void writeTo() throws URISyntaxException, MalformedURLException
   {
    final XMLWriter pw = new XMLWriter();
    final Manager manager = new Manager();
    manager.register("test.bic", BIC.class);
    manager.set("test.bic", BIC.of("POWSDE30XXX"));
    final File file = new File("target/test.xml");
    final URI uri = file.toURI();
    pw.writeTo(manager, uri);
    assertTrue(file.exists(), "test.xml has not been created");
    // TODO Verify content
    assertTrue(file.delete(), "File could not be deleted");
   }

 }
