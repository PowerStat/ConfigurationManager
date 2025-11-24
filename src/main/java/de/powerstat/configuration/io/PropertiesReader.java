/*
 * Copyright (C) 2023-2025 Dipl.-Inform. Kai Hofmann. All rights reserved!
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements; and to You under the Apache License, Version 2.0.
 */
package de.powerstat.configuration.io;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.powerstat.configuration.IReader;
import de.powerstat.configuration.Manager;


/**
 * Properties to read the configuration from.
 */
public class PropertiesReader implements IReader
 {
  /**
   * Logger.
   */
  private static final Logger LOGGER = LogManager.getLogger(PropertiesReader.class);


  /**
   * Default constructor.
   */
  public PropertiesReader()
   {
    super();
   }


  /**
   * Read configuration from propeties file.
   *
   * @param manager Configuration manager
   * @param uri URI to read the configuration from
   * @throws FileNotFoundException File not found exception
   * @throws IOException IO exception
   */
  @Override
  public void readFrom(final Manager manager, final URI uri) throws IOException
   {
    final var props = new Properties();
    try (var in = Files.newBufferedReader(Paths.get(uri)))
     {
      props.load(in);
     }
    for (final Map.Entry<Object, Object> entry : props.entrySet())
     {
      final String key = (String)entry.getKey();
      final Class<?> clazz = manager.getType(key);
      final String value = (String)entry.getValue();
      try
       {
        final var factory = clazz.getMethod("of", String.class);
        final Object valueObj = factory.invoke(factory, value);
        manager.set(key, valueObj);
       }
      catch (final NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
       {
        LOGGER.error("Exception", e); //$NON-NLS-1$
       }
     }
   }

 }
