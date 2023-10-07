/*
 * Copyright (C) 2023 Dipl.-Inform. Kai Hofmann. All rights reserved!
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
