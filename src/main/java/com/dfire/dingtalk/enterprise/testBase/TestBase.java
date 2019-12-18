package com.dfire.dingtalk.enterprise.testBase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dfire.test.util.JsonDataProvider;
import com.dfire.test.util.http.HttpRequestEx;
import com.dfire.test.util.http.Response;
import com.twodfire.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.twodfire.util.KeyUtil.generatorKey;

/**
 * com.dfire.dingtalk.enterprise.test.testBase
 *
 * @author majianfeng
 * @date 2019/10/14
 * @desc
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestBase extends AbstractTestNGSpringContextTests {
    public static Logger logger = LoggerFactory.getLogger(TestBase.class);

    public static String jsonFileDir;
    public static String hostSuffix;
    public static String host;
    public static String xtoken;
    public static String entityId;
    public static String unionId;
    public static String enterpriseId;
    public static String corpid;
    public  HttpRequestEx httpRequest = new HttpRequestEx(host);


    @DataProvider(name = "JsonDataProvider")
    public Object[][] data() throws Exception {
        return new Object[][]{
                {new JsonDataProvider(getJsonPath())},
        };
    }

    public String getJsonPath() {
        String folderName = this.getClass().getPackage().getName().replaceAll("com.dfire.dingtalk.enterprise.", "").replace(".","/");
        return jsonFileDir + folderName + "/" + this.getClass().getSimpleName() + ".json";
    }

    static {
        Map<String, String> propertiesMap = null;
        try {
            propertiesMap = PropertiesUtil.readProperties("application.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonFileDir = propertiesMap.get("jsonFileDir");
        host = propertiesMap.get("host");
        hostSuffix = propertiesMap.get("hostSuffix");
        entityId = propertiesMap.get("entityId");
        unionId = propertiesMap.get("unionId");
        enterpriseId = propertiesMap.get("enterpriseId");
        corpid = propertiesMap.get("corpid");
        xtoken = getToken(entityId,unionId);
    }


    //获取token
    public static String getToken(String entityId, String unionId) {
        HttpRequestEx request = new HttpRequestEx(host);
        try {
            List<String> pathUrl = new ArrayList<String>();
            pathUrl.add(hostSuffix);
            pathUrl.add("dev/get_token");

            String signKey = generatorKey(entityId + unionId);

            Map<String, String> query = new HashMap<String,String>();
            query.put("entity_id", entityId);
            query.put("unionid", unionId);
            query.put("sign", signKey);
            Response response = request.get(pathUrl, query);
            JSONObject resp = JSON.parseObject(response.getResponseStr());
            Assert.assertEquals(resp.get("success").toString(), "true");
            JSONObject model = resp.getJSONObject("model");

            Assert.assertEquals(model.getString("success"), "true");
            xtoken = model.getString("model");

        } catch (Exception e) {
            logger.error("token获取失败", e);
        }
        return xtoken;
    }


    public List<String> getPathUrl(String apiUrl){
        List<String> pathUrl = new ArrayList<String>();
        pathUrl.add(hostSuffix);
        pathUrl.add(apiUrl);
        return pathUrl;
    }


    //校验用.json文件生成,首次编写接口时使用
    public void createJsonFileByWriter(String jsonStr){
        try {
            String path = getJsonPath();
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            write.write(jsonStr);
            write.close();
        }catch (IOException e){
            logger.error("创建json文件失败",e);
        }

    }

}
