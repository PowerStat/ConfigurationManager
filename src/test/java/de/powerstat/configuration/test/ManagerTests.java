/*
 * Copyright (C) 2023-2025 Dipl.-Inform. Kai Hofmann. All rights reserved!
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements; and to You under the Apache License, Version 2.0.
 */
package de.powerstat.configuration.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;

import de.powerstat.configuration.Manager;
import de.powerstat.ddd.values.finance.BIC;
import de.powerstat.ddd.values.geo.Country;


/**
 * Manager tests.
 */
final class ManagerTests
 {
  /**
   * Test constant.
   */
  private static final String TEST = "test";

  /**
   * Other constant.
   */
  private static final String OTHER = "other";

  /**
   * BIC for tests constant.
   */
  private static final String BIC_POWSDE30XXX = "POWSDE30XXX";

  /**
   * Illegal argument exception constant.
   */
  private static final String ILLEGAL_ARGUMENT_EXCEPTION = "Illegal argument exception";

  /**
   * Get failed constant.
   */
  private static final String GET_FAILED = "get failed";


  /**
   * Default constructor.
   */
  /* default */ ManagerTests()
   {
    super();
   }


  /**
   * Test method for {@link de.powerstat.configuration.Manager#register(java.lang.String, java.lang.Class)}.
   */
  @Test
  /* default */ void testRegister()
   {
    final Manager test = new Manager();
    test.register(TEST, BIC.class);
    assertEquals(BIC.class, test.getType(TEST), "register failed");
   }


  /**
   * Test set.
   */
  @Test
  /* default */ void testSetOk()
   {
    final Manager test = new Manager();
    test.register(TEST, BIC.class);
    test.set(TEST, BIC.of(BIC_POWSDE30XXX));
    assertEquals(BIC_POWSDE30XXX, ((BIC)test.get(TEST)).stringValue(), "set failed");
   }


  /**
   * Test set with non registered name.
   */
  @Test
  /* default */ void testSetNameNotRegistered()
   {
    final Manager test = new Manager();
    test.register(TEST, BIC.class);
    final BIC bic = BIC.of(BIC_POWSDE30XXX);
    assertThrows(IllegalArgumentException.class, () ->
     {
      test.set(OTHER, bic);
     }, ILLEGAL_ARGUMENT_EXCEPTION
    );
   }


  /**
   * Test set with wrong class.
   */
  @Test
  /* default */ void testSetWrongClass()
   {
    final Manager test = new Manager();
    test.register(TEST, BIC.class);
    final Country country = Country.of("DE");
    assertThrows(IllegalArgumentException.class, () ->
     {
      test.set(TEST, country);
     }, ILLEGAL_ARGUMENT_EXCEPTION
    );
   }


  /**
   * Test getType.
   */
  @Test
  /* default */ void testGetType()
   {
    final Manager test = new Manager();
    test.register(TEST, BIC.class);
    final Class<?> cl = test.getType(TEST);
    assertEquals(BIC.class, cl, "getType failed");
   }


  /**
   * Test get.
   */
  @Test
  /* default */ void testGet1()
   {
    final Manager test = new Manager();
    test.register(TEST, BIC.class);
    test.set(TEST, BIC.of(BIC_POWSDE30XXX));
    assertEquals(BIC_POWSDE30XXX, ((BIC)test.get(TEST)).stringValue(), GET_FAILED);
   }


  /**
   * Test get.
   */
  @Test
  /* default */ void testGet2()
   {
    final Manager test = new Manager();
    test.register(TEST, BIC.class);
    test.set(TEST, BIC.of(BIC_POWSDE30XXX));
    final BIC bic = test.get(TEST);
    assertEquals(BIC_POWSDE30XXX, bic.stringValue(), GET_FAILED);
   }


  /**
   * Test get.
   */
  @Test
  /* default */ void testGet3()
   {
    final Manager test = new Manager();
    test.register(TEST, BIC.class);
    test.set(TEST, BIC.of(BIC_POWSDE30XXX));
    final Object result = test.get(TEST);
    if (result instanceof final BIC bic)
     {
      assertEquals(BIC_POWSDE30XXX, bic.stringValue(), GET_FAILED);
     }
    else
     {
      fail("Not of type BIC");
     }
   }


  /**
   * Test get not found.
   */
  @Test
  /* default */ void testGetNotFound()
   {
    final Manager test = new Manager();
    test.register(TEST, BIC.class);
    test.set(TEST, BIC.of(BIC_POWSDE30XXX));
    final Object result = test.get(OTHER);
    assertNull(result, "Found other");
   }


  /**
   * Test keySet.
   */
  @Test
  /* default */ void testKeySet()
   {
    final Manager test = new Manager();
    test.register(TEST, BIC.class);
    test.set(TEST, BIC.of(BIC_POWSDE30XXX));
    final Set<String> keys = test.keySet();

    final Iterable<String> expected = Collections.singletonList(TEST);
    assertIterableEquals(expected, keys, "keys not as expected");
   }

 }
