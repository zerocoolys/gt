import com.alibaba.fastjson.JSON;
import com.gt.app.BootstrapApplication;
import com.gt.app.commons.AccountVO;
import com.gt.app.commons.Response;
import com.gt.app.constant.ErrorMsg;
import com.gt.app.request.NameRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.function.Supplier;

/**
 * BaseTest
 *
 * @author yousheng
 * @since 2018/4/25
 */

public class BaseTest {

    @Autowired
    protected ErrorMsg errorMsg;

    @Autowired
    protected TestRestTemplate testRestTemplate;

    final UniformRandomProvider rng = RandomSource.create(RandomSource.MT);
    protected Supplier<Integer> randomNumber = () -> rng.nextInt(1000000);

    protected Supplier<Double> randomAmount = () -> rng.nextDouble();

    protected Supplier<String> randomName = () -> RandomStringUtils.randomAlphanumeric(16);

    protected Supplier<String> randomEmail = () -> RandomStringUtils.randomAlphanumeric(8) + "@" + RandomStringUtils.randomAlphanumeric(5) + ".com";

    protected String createAccount(NameRequest nameRequest) {
        return this.testRestTemplate.postForEntity("/accounts", nameRequest, Response.class).getBody().getMsg();
    }

    protected AccountVO getAccount(NameRequest nameRequest) {
        Response response = this.testRestTemplate.postForEntity("/get_account", nameRequest, Response.class).getBody();
        if (response.isSuccess()) {
            return JSON.parseObject(JSON.toJSONString(response.getData()), AccountVO.class);
        } else {
            return null;
        }
    }
}
