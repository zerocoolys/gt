import com.alibaba.fastjson.JSON;
import com.gt.app.BootstrapApplication;
import com.gt.app.commons.AccountVO;
import com.gt.app.commons.Response;
import com.gt.app.commons.TransactionVO;
import com.gt.app.constant.MessageConstant;
import com.gt.app.request.NameRequest;
import com.gt.app.request.TransferRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * TransactionTest
 *
 * @author yousheng
 * @since 2018/4/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootstrapApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionTest extends BaseTest {

    private Logger logger = LoggerFactory.getLogger(TransactionTest.class);

    @Test
    public void testTransaction() {

        String n1 = randomEmail.get();
        String n2 = randomEmail.get();

        NameRequest nameRequest1 = new NameRequest().setEmail(n1);
        NameRequest nameRequest2 = new NameRequest().setEmail(n2);

        Assert.assertNull(createAccount(nameRequest1));

        Assert.assertNull(createAccount(nameRequest2));


        Stream.iterate(0, integer -> integer + 1).limit(10).forEach(integer -> {
            AccountVO account1 = getAccount(nameRequest1);
            AccountVO account2 = getAccount(nameRequest2);

            TransferRequest request = new TransferRequest();

            double amount = BigDecimal.valueOf(randomNumber.get()).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP).doubleValue();
            logger.info("start transfer amount is {} .", amount);
            request.setEmail(n1).setTransferee(n2).setAmount(amount);

            String msg = transert(request);
            if (amount > account1.getBalance()) {
                Assert.assertTrue(msg, errorMsg.getMessage(MessageConstant.MSG_ERR_TRANSFER_EXCEED).equals(msg));
                logger.error(msg);
            } else if (amount <= 0) {
                Assert.assertTrue(errorMsg.getMessage(MessageConstant.MSG_ERR_TRANSFER_AMOUNT_INVALID).equals(msg));
                logger.error(msg);

            } else {
                AccountVO account1a = getAccount(nameRequest1);
                AccountVO account2a = getAccount(nameRequest2);

                Assert.assertEquals(account1.getBalance() - amount, account1a.getBalance(), 0.01);
                Assert.assertEquals(account2.getBalance() + amount, account2a.getBalance(), 0.01);

                logger.info("source account balance: {} , destination account balance: {} ", account1a.getBalance(), account2a.getBalance());
            }

        });

    }

    @Test
    public void testTransactionLogs() {
        String s1 = randomEmail.get();
        String s2 = randomEmail.get();
        double amount = Double.MAX_VALUE;
        while (amount < 0 || amount > 10000.0) {
            amount = BigDecimal.valueOf(randomNumber.get()).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP).doubleValue();
        }

        if (createTransaction(s1, s2, amount)) {

            NameRequest nameRequest1 = new NameRequest().setEmail(s1);
            List<TransactionVO> transactionVOS = getTransfer(nameRequest1);
            Assert.assertEquals(1, transactionVOS.size());

            TransactionVO transactionVO = transactionVOS.get(0);
            Assert.assertEquals(s1, transactionVO.getFrom());
            Assert.assertEquals(s2, transactionVO.getTo());
            Assert.assertEquals(amount, transactionVO.getAmount(), 0.01);

            logger.info(JSON.toJSONString(transactionVO, true));

            NameRequest nameRequest2 = new NameRequest().setEmail(s2);
            transactionVOS = getTransfer(nameRequest2);
            Assert.assertEquals(1, transactionVOS.size());

            transactionVO = transactionVOS.get(0);
            Assert.assertEquals(s1, transactionVO.getFrom());
            Assert.assertEquals(s2, transactionVO.getTo());
            Assert.assertEquals(amount, transactionVO.getAmount(), 0.01);

            logger.info(JSON.toJSONString(transactionVO, true));

        }
    }

    public String transert(TransferRequest request) {
        Response response = this.testRestTemplate.postForEntity("/transfer", request, Response.class).getBody();
        return response.getMsg();

    }

    public boolean createTransaction(String s1, String s2, double amount) {

        NameRequest nameRequest1 = new NameRequest().setEmail(s1);
        NameRequest nameRequest2 = new NameRequest().setEmail(s2);

        Assert.assertNull(createAccount(nameRequest1));

        Assert.assertNull(createAccount(nameRequest2));

        TransferRequest request = new TransferRequest();

        logger.info("start transfer amount is {} .", amount);
        request.setEmail(s1).setTransferee(s2).setAmount(amount);

        String msg = transert(request);
        return msg == null;
    }


    public List<TransactionVO> getTransfer(NameRequest nameRequest) {
        Response response = this.testRestTemplate.postForEntity("/get_transactions", nameRequest, Response.class).getBody();
        if (response.isSuccess()) {
            return JSON.parseArray(JSON.toJSONString(response.getData()), TransactionVO.class);
        } else {
            return Collections.EMPTY_LIST;
        }
    }
}
