/*
 * Copyright (C) 2023-2025 Dipl.-Inform. Kai Hofmann. All rights reserved!
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements; and to You under the Apache License, Version 2.0.
 */
package de.powerstat.configuration;


import java.net.URI;


/**
 * Interface to write configuration to uri.
 */
public interface IWriter
 {
  /**
   * Write configuration to uri.
   *
   * @param manager Configuration manager
   * @param uri URI to write the configuration to
   */
  void writeTo(Manager manager, URI uri);
 }
