import com.gt.app.BootstrapApplication;
import com.gt.app.commons.AccountVO;
import com.gt.app.constant.MessageConstant;
import com.gt.app.request.NameRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

/**
 * WebTest
 *
 * @author yousheng
 * @since 2018/4/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootstrapApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountTest extends BaseTest {

    private Logger logger = LoggerFactory.getLogger(AccountTest.class);


    @Test
    public void createTest() {
        NameRequest nameRequest = new NameRequest().setEmail("test4@test.com");

        Assert.assertNull(createAccount(nameRequest));

        AccountVO accountVO = getAccount(nameRequest);

        Assert.assertNotNull(accountVO);

        Assert.assertEquals(10000.0, accountVO.getBalance(), Double.MIN_VALUE);
    }

    @Test
    public void duplicateTest() {
        String name = "duplicate@test.com";

        NameRequest nameRequest = new NameRequest().setEmail(name);
        NameRequest duplicateRequest = new NameRequest().setEmail(name);

        Assert.assertNull(createAccount(nameRequest));
        String msg = createAccount(duplicateRequest);
        Assert.assertNotNull(msg, msg);
    }

    @Test
    public void invalidTest() {

        Stream.iterate(0, integer -> integer + 1).limit(100).forEach(integer -> {
            String name = randomName.get();
            String msg = createAccount(new NameRequest().setEmail(name));

            Assert.assertTrue(msg, msg.equals(errorMsg.getMessage(MessageConstant.MSG_ERR_EMAIL_INVALID)));
            logger.info(name);
        });

    }
}
