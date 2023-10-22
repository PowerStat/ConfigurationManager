/*
 * Copyright (C) 2023 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.configuration.io;


import java.io.File;
import java.net.URI;
import java.util.Set;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;

import de.powerstat.configuration.IWriter;
import de.powerstat.configuration.Manager;
import de.powerstat.validation.interfaces.IValueObject;


/**
 * XML to write configuration to.
 */
public class XMLWriter implements IWriter
 {
  /**
   * Logger.
   */
  private static final Logger LOGGER = LogManager.getLogger(XMLWriter.class);

  /**
   * All constant.
   */
  private static final String ALL = "all";


  /**
   * Default constructor.
   */
  public XMLWriter()
   {
    super();
   }


  /**
   * Write xml document to file.
   *
   * @param document DOM document
   * @param filename Filename
   */
  private static void writeXml2File(final Document document, final String filename)
   {
    try
     {
      final var factory = TransformerFactory.newInstance();
      factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, ALL);
      // factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
      factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, ALL);
      factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

      final var transformer = factory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //$NON-NLS-1$
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2"); //$NON-NLS-1$ //$NON-NLS-2$
      transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no"); //$NON-NLS-1$
      transformer.setOutputProperty(OutputKeys.METHOD, "xml"); //$NON-NLS-1$
      final var file = new File(filename);
      /* final boolean success = */ file.getParentFile().mkdirs();
      transformer.transform(new DOMSource(document), new StreamResult(file));
     }
    catch (final TransformerException e)
     {
      LOGGER.error("", e); //$NON-NLS-1$
     }
   }


  /**
   * Write configuration in xml format to uri.
   *
   * @param manager Configuration manager
   * @param uri URI to write the configuration to
   *
   * &lt;?xml version="1.0" encoding="UTF-8"?&gt;
   * &lt;CONFIGURATIONS xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="Configuration.xsd"&gt;
   *   &lt;ENTRY name="" class=""&gt;&lt;/ENTRY&gt;
   * &lt;/CONFIGURATIONS&gt;
   */
  @Override
  public void writeTo(final Manager manager, final URI uri)
   {
    try
     {
      final var factory = DocumentBuilderFactory.newInstance();
      factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
      factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true); //$NON-NLS-1$
      // TODO factory.setSchema(null);
      final var docBuilder = factory.newDocumentBuilder();
      final var doc = docBuilder.newDocument();
      doc.setXmlVersion("1.0");
      final var rootElement = doc.createElement("CONFIGURATIONS");
      // TODO rootElement.setAttributeNodeNS(null);
      doc.appendChild(rootElement);

      final Set<String> keys = manager.keySet();
      for (final String key : keys)
       {
        final var element = doc.createElement("ENTRY");
        element.setAttribute("name", key);
        element.setAttribute("class", manager.getType(key).getName());
        element.appendChild(doc.createTextNode(((IValueObject)manager.get(key)).stringValue()));
        rootElement.appendChild(element);
       }
      writeXml2File(doc, new File(uri).getAbsolutePath());
     }
    catch (final ParserConfigurationException e)
     {
      LOGGER.error("Exception", e); //$NON-NLS-1$
     }
   }

 }
