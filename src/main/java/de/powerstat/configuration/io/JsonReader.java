/*
 * Copyright (C) 2023 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.configuration.io;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.powerstat.configuration.IReader;
import de.powerstat.configuration.Manager;


/**
 * JSON file to read the configuration from.
 */
public class JsonReader implements IReader
 {
  /**
   * Logger.
   */
  private static final Logger LOGGER = LogManager.getLogger(JsonReader.class);


  /**
   * Default constructor.
   */
  public JsonReader()
   {
    super();
   }


  /**
   * Read configuration from json file.
   *
   * @param manager Configuration manager
   * @param uri URI to read the configuration from
   * @throws IOException IO exception
   * @throws FileNotFoundException File not found
   */
  @Override
  public void readFrom(final Manager manager, final URI uri) throws IOException
   {
    final var gson = new Gson();
    final TypeToken<Map<String, String>> mapType = new TypeToken<>(){};
    final var json = new StringBuilder();
    try (var in = Files.newBufferedReader(Paths.get(uri)))
     {
      final String line = in.readLine();
      json.append(line);
     }
    final Map<String, String> stringMap = gson.fromJson(json.toString(), mapType);
    for (final Map.Entry<String, String> entry : stringMap.entrySet())
     {
      final String key = entry.getKey();
      final Class<?> clazz = manager.getType(key);
      final String value = entry.getValue();
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
