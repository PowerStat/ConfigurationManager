/*
 * Copyright (C) 2023 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */


/**
 * Configuration module.
 */
module de.powerstat.configuration
 {
  exports de.powerstat.configuration;
  exports de.powerstat.configuration.io;

  requires de.powerstat.validation;
  requires org.apache.logging.log4j;
  requires java.xml;
  requires com.google.gson;

 }
