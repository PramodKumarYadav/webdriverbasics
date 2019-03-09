package com.seleniumsimplified.junit;

import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JunitBeforeAndAfterTest {

    static int additionSum = 4;
    static String name = "Vinod";

    @BeforeAll
    public static void beforeAllTest() {

        String name = "Pramod";
        System.out.println("@Class level : This statement runs Once for the class \n");
    }

    @BeforeAll
    public static void beforeAllTestMultiple() {
        System.out.println("@Class level : This is Another Statement that run before ALL tests \n");
    }
    @BeforeEach
    public void beforeEachTest() {
        System.out.println("This is before each test");
        additionSum = 13;

    }
    @BeforeEach
    public void beforeEachTestAuthorName() {
        System.out.println("This statement runs before each test");
    }
    @Test
    public void thisIsTest1() {
        System.out.println("This is 1 test");
        int newSum = additionSum + 2;
        assertEquals(15,newSum,"13+2 = 5");

    }

    @Test
    public void thisIsTest2() {
        System.out.println("This is 2 test");
        int newSum = additionSum + 2;
        assertTrue(newSum == 15,"Total sum should be 15");
    }

    @Test
    public void thisIsTest3() {
        System.out.println("This is 3 test");
        int newSum = additionSum + 2;
        assertFalse(newSum == 10,"Total sum should be 15");
    }

    @Test
    public void thisIsTest4() {
        System.out.println("This is 3 test");
        int newSum = additionSum + 2;
    }
    @AfterEach
    public void thisIsAfterEachTest() {
        System.out.println("This is after each test \n");
    }

    @AfterAll
    public static void thisIsAfterALLTest() {
        System.out.println("This is after ALL test \n");
    }
}
