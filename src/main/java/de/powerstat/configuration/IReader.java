/*
 * Copyright (C) 2023 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.configuration;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;


/**
 * Interface to read the configuration from uri.
 */
public interface IReader
 {
  /**
   * Read configuration from uri.
   *
   * @param manager Configuration manager
   * @param uri URI to read the configuration from
   * @throws FileNotFoundException File not found
   * @throws IOException IO exception
   */
  void readFrom(Manager manager, URI uri) throws IOException;
 }
