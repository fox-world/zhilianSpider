package com.lucumt.zhaopin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lucumt.zhaopin.service.ParseJobService;

/**
 * Unit test for simple App.
 */
public class ParseJobTest {

	private ParseJobService parseJobService;
	
	@Before
	public void setUp(){
		parseJobService=new ParseJobService();
	}
	
	@Test
	public void testApp(){
		parseJobService.praseContent("PHP","北京");
	}
	
	@After
	public void tearDown(){
		parseJobService=null;
	}
}
