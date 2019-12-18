package com.dfire.dingtalk.enterprise.toC.daily.takeout;

import com.dfire.dingtalk.enterprise.testBase.TestBase;
import com.dfire.test.util.JsonDataProvider;
import com.dfire.test.util.StringUtil;
import com.dfire.test.util.http.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页-获取餐卡列表
 * Created by shanzhajiang on 2019/12/3.
 */
public class GetMealSettingTest extends TestBase {
    public static Logger logger = LoggerFactory.getLogger(GetMealSettingTest.class);
    StringBuilder sb = new StringBuilder();

    @Test(dataProvider = "JsonDataProvider")
    public void testGetMealSettingTest(JsonDataProvider jsonDataProvider) {
        //调用http服务
        try {
            List<String> pathUrl = getPathUrl("enterprise/takeout/v2/get_meal_setting");
            Map<String, String> param = new HashMap<>();
            param.put("xtoken", xtoken);
            param.put("enterpriseId", enterpriseId);
            param.put("t", String.valueOf(System.currentTimeMillis()));

            Response response = httpRequest.get(pathUrl, param);
            Assert.assertEquals(response.getStatus(), 200, "接口调用失败");
            Assert.assertEquals(response.getResponseStr(),jsonDataProvider.getJsonStr());

        } catch (Exception e) {
            logger.error(this.getClass().getName() + "调用异常");
            sb.append(e);
        }
        Assert.assertTrue(StringUtil.isEmpty(sb.toString()));
    }
}