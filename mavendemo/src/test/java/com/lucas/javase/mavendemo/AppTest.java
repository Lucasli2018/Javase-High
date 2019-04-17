package com.lucas.javase.mavendemo;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	@Test
    public void testApp()
    {
        assertTrue( true );
    }
	
	@Test
	public void testSum(){
		assertEquals(3,new App().sum(1, 2));
		assertEquals(4,new App().sum(2, 2));
	}
}
