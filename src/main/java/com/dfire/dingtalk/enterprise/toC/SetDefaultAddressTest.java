package com.dfire.dingtalk.enterprise.toC;

import com.dfire.dingtalk.enterprise.DingtalkEnterpriseTestApplication;
import com.dfire.dingtalk.enterprise.testBase.TestBase;
import com.dfire.test.util.JsonDataProvider;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

/**
 * com.dfire.dingtalk.enterprise
 *
 * @author majianfeng
 * @date 2019/10/15
 * @desc 修改默认地址
 */
@EnableAutoConfiguration
@SpringBootTest(classes = DingtalkEnterpriseTestApplication.class)
public class SetDefaultAddressTest extends TestBase {

    @Test(dataProvider = "JsonDataProvider")
    public void testSetDefaultAddress(JsonDataProvider jsonDataProvider) {
        String str = jsonDataProvider.getJsonStr();
        System.out.println(str);
    }
}
