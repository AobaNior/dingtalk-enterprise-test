package com.dfire.dingtalk.enterprise.toC;

import com.alibaba.fastjson.JSONObject;
import com.dfire.dingtalk.enterprise.DingtalkEnterpriseTestApplication;
import com.dfire.dingtalk.enterprise.utils.HttpMethodFacade;
import com.dfire.dingtalk.enterprise.utils.JSONUtil;
import com.dfire.dingtalk.enterprise.testBase.TestBase;
import com.dfire.test.util.JsonDataProvider;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * com.dfire.dingtalk.enterprise.toC
 *
 * @author majianfeng
 * @date 2019/10/22
 * @desc
 */
@EnableAutoConfiguration
@SpringBootTest(classes = DingtalkEnterpriseTestApplication.class)
public class InitialHomePageTest extends TestBase {

    String initialHomePageUrl = "https://meal.2dfire.com/enterprise/takeout/v2/initial_homepage?xtoken=55d27ed5e90b663bb77388cf44b1238c&t=1571726989778&addressId=&enterpriseId=00414332&entrance=4";

    @Test(dataProvider = "JsonDataProvider")
    public void testInitialHomePage(JsonDataProvider jsonDataProvider) {
        JSONObject jsonObject = HttpMethodFacade.getMethodResponse(initialHomePageUrl);
        String expResponse = jsonDataProvider.getJsonStr();
        Assert.assertEquals(jsonObject.toJSONString(), expResponse);
        JSONObject responseJSONObject = jsonDataProvider.getJsonObject();
        Assert.assertTrue(JSONUtil.compareJSONObject(jsonObject, responseJSONObject));
    }
}
