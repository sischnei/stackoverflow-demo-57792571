package nl.martinvw.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private DemoConfiguration.MainUIFrame test;

	@Test
	public void contextLoads() {
		if (test == null) {
			throw new IllegalStateException("fail!");
		}
	}
}
