package liu.BasePro;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application.xml", "classpath:mysql_config.xml" })
public class BaseTest {
	public static Logger logger = LoggerFactory.getLogger(BaseTest.class);
}
