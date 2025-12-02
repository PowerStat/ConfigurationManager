/*
 * Copyright (C) 2023-2025 Dipl.-Inform. Kai Hofmann. All rights reserved!
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements; and to You under the Apache License, Version 2.0.
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
  requires org.checkerframework.checker.qual;
  requires org.jmolecules.ddd;

  requires com.github.spotbugs.annotations;
  requires org.junit.jupiter.api;
  requires org.junit.jupiter.params;
  requires org.junit.platform.launcher;
  requires org.junit.platform.suite.api;
  // requires nl.jqno.equalsverifier;

 }
