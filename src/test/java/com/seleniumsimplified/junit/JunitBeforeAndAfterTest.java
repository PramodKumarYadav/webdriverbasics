package com.seleniumsimplified.junit;

import org.junit.jupiter.api.*;

public class JunitBeforeAndAfterTest {

    static int additionSum = 4;

    @BeforeAll
    public static void beforeAllTest() {
        additionSum = 13;
        System.out.println("This is before ALL test \n");
    }

    @BeforeAll
    public static void beforeAllTestMultiple() {
        System.out.println("This is Another Statement run before ALL test \n");
    }
    @BeforeEach
    public void beforeEachTest() {
        System.out.println("This is before each test");
    }
    @BeforeEach
    public void beforeEachTestAuthorName() {
        System.out.println("This is from Pramod");
    }
    @Test
    public void thisIsTest1() {
        System.out.println("This is 1 test");
    }

    @Test
    public void thisIsTest2() {
        System.out.println("This is 2 test");
    }

    @Test
    public void thisIsTest3() {
        System.out.println("This is 3 test");
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
