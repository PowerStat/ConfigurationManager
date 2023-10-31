/*
 * Copyright (C) 2023 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
 

/**
 * Configuration module.
 */
open module de.powerstat.configuration
 {
  exports de.powerstat.configuration;

  requires de.powerstat.validation;
  requires org.apache.logging.log4j;
  requires java.xml;
  requires com.google.gson;

  requires com.github.spotbugs.annotations;
  requires org.junit.jupiter.api;
  requires org.junit.jupiter.params;
  requires org.junit.platform.launcher;
  requires org.junit.platform.suite.api;
  // requires nl.jqno.equalsverifier;

 }
