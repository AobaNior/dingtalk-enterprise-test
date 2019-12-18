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
public class GetEnterpriseForDingTest extends TestBase {

    String getEnterpriseForDingUrl = "https://meal.2dfire.com/enterprise/takeout/v1/get_enterprise_for_ding?xtoken=c2f843cf317c6bacba9b6a2a09bbb144&t=1571732172993&corpId=dinge1a1972fdfaae0f9";

    @Test(dataProvider = "JsonDataProvider")
    public void testGetEnterpriseForDing(JsonDataProvider jsonDataProvider) {
        JSONObject jsonObject = HttpMethodFacade.getMethodResponse(getEnterpriseForDingUrl);
        String expResponse = jsonDataProvider.getJsonStr();
        Assert.assertEquals(jsonObject.toJSONString(), expResponse);
        JSONObject responseJSONObject = jsonDataProvider.getJsonObject();
        Assert.assertTrue(JSONUtil.compareJSONObject(jsonObject, responseJSONObject));
    }
}
