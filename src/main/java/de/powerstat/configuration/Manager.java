/*
 * Copyright (C) 2023 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.configuration;


import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Configuration manager.
 *
 * TODO source map: String, IReader
 * TODO Iterator
 */
public class Manager
 {
  /**
   * Logger.
   */
  private static final Logger LOGGER = LogManager.getLogger(Manager.class);

  /**
   * Configuration type map.
   */
  private final Map<String, Class<?>> typeMap = new ConcurrentHashMap<>();

  /**
   * Configuration map.
   */
  private final Map<String, Object> configurationMap = new ConcurrentHashMap<>();

  // TODO: private final Map<String, NTuple2<Class<?>, Object>> typeMap = new ConcurrentHashMap<>();
  // TODO: Default value
  // TODO: private final Map<String, NTuple3<Class<?>, Object, String>> typeMap = new ConcurrentHashMap<>();


  /**
   * Default constructor.
   */
  public Manager()
   {
    super();
   }


  /**
   * Register configuration name, type.
   *
   * @param name Configuration name
   * @param type Configuration type
   */
  public void register(final String name, final Class<?> type)
   {
    this.typeMap.put(name, type);
   }


  /**
   * Set configuration.
   *
   * @param name Configuration name
   * @param value Configuration value
   * @throws IllegalArgumentException When the name is not registered or the class of the value is different of the registered one
   */
  public void set(final String name, final Object value)
   {
    if (value.getClass() != this.typeMap.get(name))
     {
      throw new IllegalArgumentException((this.typeMap.get(name) == null) ? "name is not registered!" : "Type of value is not of the same class as registered!");
     }
    this.configurationMap.put(name, value);
   }


  /**
   * Get configuration type.
   *
   * @param name Configuration name
   * @return Configuration class
   */
  public Class<?> getType(final String name)
   {
    return this.typeMap.get(name);
   }


  /**
   * Get configuration.
   *
   * @param name Configuration name
   * @return Configuration value of type T
   * @throws ClassCastException In case of incompatible type
   */
  @SuppressWarnings("unchecked")
  public <T extends Object> T get(final String name)
   {
    return (T)this.configurationMap.get(name);
   }


  /**
   * Key set of property names.
   *
   * @return String set of keys
   */
  public Set<String> keySet()
   {
    return this.configurationMap.keySet();
   }

 }
