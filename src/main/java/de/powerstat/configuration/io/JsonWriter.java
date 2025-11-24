/*
 * Copyright (C) 2023-2025 Dipl.-Inform. Kai Hofmann. All rights reserved!
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements; and to You under the Apache License, Version 2.0.
 */
package de.powerstat.configuration.io;


import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import de.powerstat.configuration.IWriter;
import de.powerstat.configuration.Manager;
import de.powerstat.validation.interfaces.IValueObject;


/**
 * Json to write configuration to.
 */
public class JsonWriter implements IWriter
 {
  /**
   * Logger.
   */
  private static final Logger LOGGER = LogManager.getLogger(JsonWriter.class);


  /**
   * Default constructor.
   */
  public JsonWriter()
   {
    super();
   }


  /**
   * Write configuration to uri.
   *
   * @param manager Configuration manager
   * @param uri URI to write the configuration to
   */
  @Override
  public void writeTo(final Manager manager, final URI uri)
   {
    final var gson = new Gson();
    final Set<String> keys = manager.keySet();
    final Map<String, String> stringMap = new ConcurrentHashMap<>();
    for (final String key : keys)
     {
      stringMap.put(key, ((IValueObject)manager.get(key)).stringValue());
     }
    final String json = gson.toJson(stringMap);

    try (var out = Files.newBufferedWriter(Paths.get(uri)))
     {
      out.write(json);
     }
    catch (final IOException e)
     {
      LOGGER.error("Exception", e); //$NON-NLS-1$
     }
   }

 }
