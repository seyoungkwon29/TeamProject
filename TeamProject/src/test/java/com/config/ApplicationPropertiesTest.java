package com.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assume.assumeThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@WebAppConfiguration
public class ApplicationPropertiesTest {

	@Value("${file.basedir}")
	String basedir;
	
	@Test
	public void testFileBaseDirOnWindows() {
		assumeThat(getOsName(), containsString("windows"));
		assertThat(basedir).isEqualTo("file:/C:/mail_upload");
	}
	
	@Test
	public void testFileBaseDirOnMac() {
		assumeThat(getOsName(), containsString("mac"));
		assertThat(basedir).endsWith("/files/");
	}
	
	private String getOsName() {
		return System.getProperty("os.name").toLowerCase(); 
	}
}
