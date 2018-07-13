package com.circleci.demojavaspring;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

@ContextConfiguration
@TestPropertySource("/test.properties")
public class DemoJavaSpringApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void InduceFailure() {
	fail("Let us make this test fail always");
		
	}

	@Test
	public void SampleTest1() {
	assertTrue(true);
	}

	@Test
	public void SampleTest2() {
	assertTrue(true);
	}
	
	@Test
	public void SampleTest3() {
	assertTrue(true);
	}

	@Test
	public void SampleTest4() {
	assertTrue(true);
	}
	
	@Test
	public void SampleTest5() {
	assertTrue(true);
	}
	
	@Test
	public void SampleTest6() {
	assertTrue(false);
	}

	@Test
	public void SampleTest7() {
	assertTrue(false);
	}
}
