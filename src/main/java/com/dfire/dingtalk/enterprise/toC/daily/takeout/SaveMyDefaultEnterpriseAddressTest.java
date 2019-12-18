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

import static com.dfire.dingtalk.enterprise.testBase.TestBase.enterpriseId;
import static com.dfire.dingtalk.enterprise.testBase.TestBase.xtoken;

/**
 * 首页-获取地址列表
 * Created by dongcai on 2019/12/18.
 */
public class SaveMyDefaultEnterpriseAddressTest extends TestBase{
    public static Logger logger = LoggerFactory.getLogger(SaveMyDefaultEnterpriseAddressTest.class);
    StringBuilder sb = new StringBuilder();

    @Test(dataProvider = "JsonDataProvider")
    public void testSaveMyDefaultEnterpriseAddressTest(JsonDataProvider jsonDataProvider) {
        //调用http服务
        try {
            List<String> pathUrl = getPathUrl("enterprise/takeout/v1/save_my_default_enterprise_address");
            Map<String, String> param = new HashMap<>();
            param.put("xtoken", xtoken);
            param.put("address_id","2");
            param.put("enterprise_id", enterpriseId);
            param.put("t", String.valueOf(System.currentTimeMillis()));
            Response response = httpRequest.post(pathUrl,param);
            Assert.assertEquals(response.getStatus(), 200, "接口调用失败");
            //createJsonFileByWriter(response.getResponseStr());
            Assert.assertEquals(response.getResponseStr(),jsonDataProvider.getJsonStr());

        } catch (Exception e) {
            logger.error(this.getClass().getName() + "调用异常");
            sb.append(e);
        }
        Assert.assertTrue(StringUtil.isEmpty(sb.toString()));
    }

}
