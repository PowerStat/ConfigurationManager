/*
 * Copyright (C) 2023 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.configuration.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import org.junit.jupiter.api.Test;

import de.powerstat.configuration.Manager;
import de.powerstat.validation.values.BIC;
import de.powerstat.validation.values.Country;


/**
 * Manager tests.
 */
class ManagerTests
 {
  /**
   * Test method for {@link de.powerstat.configuration.Manager#register(java.lang.String, java.lang.Class)}.
   */
  @Test
  void testRegister()
   {
    final Manager test = new Manager();
    test.register("test", BIC.class);
    assertEquals(BIC.class, test.getType("test"));
   }


  /**
   * Test set.
   */
  @Test
  void testSetOk()
   {
    final Manager test = new Manager();
    test.register("test", BIC.class);
    test.set("test", BIC.of("POWSDE30XXX"));
    assertEquals("POWSDE30XXX", ((BIC)test.get("test")).stringValue());
   }


  /**
   * Test set with non registered name.
   */
  @Test
  void testSetNameNotRegistered()
   {
    final Manager test = new Manager();
    test.register("test", BIC.class);
    assertThrows(IllegalArgumentException.class, () ->
     {
      test.set("other", BIC.of("POWSDE30XXX"));
     }, "Illegal argument exception"
    );
   }


  /**
   * Test set with wrong class.
   */
  @Test
  void testSetWrongClass()
   {
    final Manager test = new Manager();
    test.register("test", BIC.class);
    assertThrows(IllegalArgumentException.class, () ->
     {
      test.set("test", Country.of("DE"));
     }, "Illegal argument exception"
    );
   }


  /**
   * Test getType.
   */
  @Test
  void testGetType()
   {
    final Manager test = new Manager();
    test.register("test", BIC.class);
    final Class<?> cl = test.getType("test");
    assertEquals(BIC.class, cl);
   }


  /**
   * Test get.
   */
  @Test
  void testGet1()
   {
    final Manager test = new Manager();
    test.register("test", BIC.class);
    test.set("test", BIC.of("POWSDE30XXX"));
    assertEquals("POWSDE30XXX", ((BIC)test.get("test")).stringValue());
   }


  /**
   * Test get.
   */
  @Test
  void testGet2()
   {
    final Manager test = new Manager();
    test.register("test", BIC.class);
    test.set("test", BIC.of("POWSDE30XXX"));
    final BIC bic = test.get("test");
    assertEquals("POWSDE30XXX", bic.stringValue());
   }


  /**
   * Test get.
   */
  @Test
  void testGet3()
   {
    final Manager test = new Manager();
    test.register("test", BIC.class);
    test.set("test", BIC.of("POWSDE30XXX"));
    final Object result = test.get("test");
    if (result instanceof final BIC bic)
     {
      assertEquals("POWSDE30XXX", bic.stringValue());
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
  void testGetNotFound()
   {
    final Manager test = new Manager();
    test.register("test", BIC.class);
    test.set("test", BIC.of("POWSDE30XXX"));
    final Object result = test.get("other");
    assertNull(result, "Found other");
   }


  /**
   * Test keySet.
   */
  @Test
  void keySet()
   {
    final Manager test = new Manager();
    test.register("test", BIC.class);
    test.set("test", BIC.of("POWSDE30XXX"));
    final Set<String> keys = test.keySet();

    final Iterable<String> expected = new ArrayList<>(Arrays.asList("test"));
    assertIterableEquals(expected, keys, "keys not as expected");
   }

 }
