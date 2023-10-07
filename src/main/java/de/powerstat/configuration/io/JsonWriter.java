/*
 * Copyright (C) 2023 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.configuration.io;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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
    final Gson gson = new Gson();
    final Set<String> keys = manager.keySet();
    final Map<String, String> stringMap = new LinkedHashMap<>();
    for (final String key : keys)
     {
      stringMap.put(key, ((IValueObject)manager.get(key)).stringValue());
     }
    final String json = gson.toJson(stringMap);

    try (BufferedWriter out = new BufferedWriter(new FileWriter(new File(uri))))
     {
      out.write(json);
     }
    catch (final IOException e)
     {
      LOGGER.error("Exception", e); //$NON-NLS-1$
     }
   }

 }
