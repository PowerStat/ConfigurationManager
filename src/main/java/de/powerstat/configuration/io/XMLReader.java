/*
 * Copyright (C) 2023 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.configuration.io;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.powerstat.configuration.IReader;
import de.powerstat.configuration.Manager;


/**
 * XML to read the configuration from.
 */
public class XMLReader implements IReader
 {
  /**
   * Logger.
   */
  private static final Logger LOGGER = LogManager.getLogger(XMLReader.class);


  /**
   * Default constructor.
   */
  public XMLReader()
   {
    super();
   }


  /**
   * Read xml configuration from uri.
   *
   * @param manager Configuration manager
   * @param uri URI to read the configuration from
   *
   * &lt;?xml version="1.0" encoding="UTF-8"?&gt;
   * &lt;CONFIGURATIONS xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="Configuration.xsd"&gt;
   *   &lt;ENTRY name="" class=""&gt;&lt;/ENTRY&gt;
   * &lt;/CONFIGURATIONS&gt;
   */
  @Override
  public void readFrom(final Manager manager, final URI uri)
   {
    try
     {
      final var factory = DocumentBuilderFactory.newInstance();
      factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
      factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true); //$NON-NLS-1$
      final var docBuilder = factory.newDocumentBuilder();
      final Document doc = docBuilder.parse(new File(uri));
      final NodeList nl = doc.getElementsByTagName("ENTRY");
      for (int pos = 0; pos < nl.getLength(); ++pos)
       {
        final var node = nl.item(pos);
        final NamedNodeMap attrs = node.getAttributes();
        final String key = attrs.getNamedItem("name").getNodeValue();
        // final String className = attrs.getNamedItem("class").getNodeValue();
        final String value = node.getTextContent();
        final Class<?> clazz = manager.getType(key);
        try
         {
          final var mfactory = clazz.getMethod("of", String.class);
          final Object valueObj = mfactory.invoke(mfactory, value);
          manager.set(key, valueObj);
         }
        catch (final NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
         {
          LOGGER.error("Exception", e); //$NON-NLS-1$
         }
       }
     }
    catch (final ParserConfigurationException | IOException | SAXException e)
     {
      LOGGER.error("", e); //$NON-NLS-1$
     }
   }

 }
