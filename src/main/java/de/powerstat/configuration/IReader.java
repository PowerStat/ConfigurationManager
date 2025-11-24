/*
 * Copyright (C) 2023-2025 Dipl.-Inform. Kai Hofmann. All rights reserved!
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements; and to You under the Apache License, Version 2.0.
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
