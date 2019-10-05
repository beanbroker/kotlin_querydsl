package com.beanbroker.sample;

import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

public class SampleTest {

    @BeforeAll
    public static void beforeAll(){
        System.out.println("BeforeAll");
    }

    @BeforeEach
    public void beforeEach(){

        System.out.println("BeforeEach");
    }

    @AfterEach
    public void afterEach(){
        System.out.println("AfterEach");
    }

    @AfterAll
    public static void afterAll(){
        System.out.println("AfterAll");
    }

    @Test
    public void testCodeOne(){
        System.out.println("testCodeOne start ");
    }

    @Test
    public void testCodeTwo(){
        System.out.println("testCodetwo start ");
    }
}
