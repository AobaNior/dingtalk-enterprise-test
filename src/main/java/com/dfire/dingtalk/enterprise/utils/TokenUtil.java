package com.dfire.dingtalk.enterprise.utils;

import com.alibaba.fastjson.JSONObject;
import com.twodfire.util.MD5Util;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TokenUtil {

    public static Logger logger = LoggerFactory.getLogger("biz-tokenUtil");
    private static final String SIGN_KEY = ",.xcvlasdiqpoikm,. xvz";
    public static String xtoken;

    /**
     * 获取用户 token*
     * @param httpUrl
     * @param unionId
     * @return
     */
    public static String getToken(String httpUrl, String unionId) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String sign = generatorKey(unionId);
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("entity_id", ""));
        nameValuePairs.add(new BasicNameValuePair("unionid", unionId));
        nameValuePairs.add(new BasicNameValuePair("sign", sign));
        HttpGet httpGet = new HttpGet(httpUrl + "/dev/get_token?" + URLEncodedUtils.format(nameValuePairs, "utf-8"));
        try {
            HttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + statusCode);
            } else {
                HttpEntity httpEntity = response.getEntity();
                String body = EntityUtils.toString(httpEntity);
                JSONObject responseJSON = JSONObject.parseObject(body);
                Assert.assertTrue(responseJSON.getBooleanValue("success"));
                xtoken = responseJSON.getJSONObject("model").getString("model");
            }
        } catch (Exception e) {
            logger.error("token获取失败", e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return xtoken;
    }

    public static String generatorKey(String source) {
        return MD5Util.MD5(source + SIGN_KEY);
    }

}
