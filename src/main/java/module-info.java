/*
 * Copyright (C) 2023-2025 Dipl.-Inform. Kai Hofmann. All rights reserved!
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements; and to You under the Apache License, Version 2.0.
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
