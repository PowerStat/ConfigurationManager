/*
 * Copyright (C) 2023 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.configuration.io;


import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.powerstat.configuration.IWriter;
import de.powerstat.configuration.Manager;
import de.powerstat.validation.interfaces.IValueObject;


/**
 * Properties to write configuration to.
 */
public class PropertiesWriter implements IWriter
 {
  /**
   * Logger.
   */
  private static final Logger LOGGER = LogManager.getLogger(PropertiesWriter.class);


  /**
   * Default constructor.
   */
  public PropertiesWriter()
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
    final var props = new Properties();
    final Set<String> keys = manager.keySet();
    for (final String key : keys)
     {
      props.setProperty(key, ((IValueObject)manager.get(key)).stringValue());
     }
    try (var out = Files.newBufferedWriter(Paths.get(uri)))
     {
      props.store(out, "PowerStat's configuration manager");
     }
    catch (final IOException e)
     {
      LOGGER.error("Exception", e); //$NON-NLS-1$
     }
   }

 }
