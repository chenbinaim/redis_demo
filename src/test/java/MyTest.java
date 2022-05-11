import com.alibaba.fastjson.JSONObject;
import com.vvvbin.demo.StartUpApplication;
import com.vvvbin.demo.service.RedisService;
import com.vvvbin.demo.utils.MongoDBUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StartUpApplication.class)
public class MyTest {
    @Autowired
    private RedisService redisService;

    @Autowired
    private MongoDBUtil mongoDBUtil;

    @Test
    public void pushRedis() {
        System.out.println("pushRedis()开始");
        Map<String, String> map = new HashMap<>();
        String str= "{\n\n        \"ndDevice\": {\n\n                \"unionDeviceId\": \"9B8DDB54111\",\n\n                \"deviceType\": \"DEVICE_TYPE_PHONE\",\n\n                \"idfa\": \"\",\n\n                \"imei\": \"f969499a-462d-4129-9859-81fda824eb0a\",\n\n                \"mac\": \"\",\n\n                \"imsi\": \"\",\n\n                \"oaid\": \"\",\n\n                \"androidId\": \"bef9450fdcf45117\",\n\n                \"resolution\": \"1080*1793\",\n\n                \"cpu\": \"arm64-v8a\",\n\n                \"cpuType\": \"hi3660 VTR\",\n\n                \"appList\": null,\n\n                \"deviceBrand\": \"HUAWEI\",\n\n                \"deviceModel\": \"VTR-AL00\",\n\n                \"deviceName\": \"HWVTR\",\n\n                \"deviceBoard\": \"VTR\",\n\n                \"deviceManufacturer\": \"HUAWEI\",\n\n                \"systemName\": \"Android\",\n\n                \"systemVersion\": \"9\",\n\n                \"firmwareVersion\": \"9\",\n\n                \"clientIp\": \"192.168.255.230\",\n\n                \"country\": \"CN\",\n\n                \"carrier\": null,\n\n                \"networkMode\": \"WIFI\",\n\n                \"timezone\": \"GMT+08:00\",\n\n                \"language\": \"zh\"\n\n        },\n\n        \"ydDevice\": {\n\n                \"ydDeviceId\": \"23DeiHgrCQFBC0URBQJ7qssVs5g2FdHY\",\n\n                \"ydDeviceIdMd5\": \"83CBEB3A08842C5CF55E4096C5C16A4A\",\n\n                \"ydSdkType\": 3,\n\n                \"isTampered\": null,\n\n                \"isSimulator\": null,\n\n                \"isRooted\": null,\n\n                \"isMultiRun\": null,\n\n                \"isVpn\": 0,\n\n                \"isProxy\": null,\n\n                \"isHooked\": null,\n\n                \"isInjected\": null,\n\n                \"isDebugged\": null,\n\n                \"isXposed\": null,\n\n                \"isCloud\": 0,\n\n                \"isSuspectCloud\": null,\n\n                \"isRiskRom\": null,\n\n                \"isVm\": null,\n\n                \"isModify\": null,\n\n                \"isModifyApp\": null,\n\n                \"isFlash\": null,\n\n                \"isAutoTouch\": null,\n\n                \"isControlApp\": null,\n\n                \"isScript\": 0,\n\n                \"securityScore\": 90,\n\n                \"isCydiaSubstrate\": 0,\n\n                \"isM1\": 0,\n\n                \"isSpeedUp\": 0,\n\n                \"isAntiJailbreak\": 0\n\n        }\n\n}";
        JSONObject js = JSONObject.parseObject(str);
        for (int i = 0; i < 1; i++) {
            map.put(UUID.randomUUID() + "", js.toString());
        }
        redisService.setHash("20220424-test", map);
        System.out.println("pushRedis()结束");

    }
    @Test
    public  void pushMongoDB() {
        System.out.println("pushMongoDB()开始");

        MongoDBUtil util = mongoDBUtil;
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 90000; i++) {
            String str= "{\n\n        \"ndDevice\": {\n\n                \"unionDeviceId\": \"9B8DDB54111\",\n\n                \"deviceType\": \"DEVICE_TYPE_PHONE\",\n\n                \"idfa\": \"\",\n\n                \"imei\": \"f969499a-462d-4129-9859-81fda824eb0a\",\n\n                \"mac\": \"\",\n\n                \"imsi\": \"\",\n\n                \"oaid\": \"\",\n\n                \"androidId\": \"bef9450fdcf45117\",\n\n                \"resolution\": \"1080*1793\",\n\n                \"cpu\": \"arm64-v8a\",\n\n                \"cpuType\": \"hi3660 VTR\",\n\n                \"appList\": null,\n\n                \"deviceBrand\": \"HUAWEI\",\n\n                \"deviceModel\": \"VTR-AL00\",\n\n                \"deviceName\": \"HWVTR\",\n\n                \"deviceBoard\": \"VTR\",\n\n                \"deviceManufacturer\": \"HUAWEI\",\n\n                \"systemName\": \"Android\",\n\n                \"systemVersion\": \"9\",\n\n                \"firmwareVersion\": \"9\",\n\n                \"clientIp\": \"192.168.255.230\",\n\n                \"country\": \"CN\",\n\n                \"carrier\": null,\n\n                \"networkMode\": \"WIFI\",\n\n                \"timezone\": \"GMT+08:00\",\n\n                \"language\": \"zh\"\n\n        },\n\n        \"ydDevice\": {\n\n                \"ydDeviceId\": \"23DeiHgrCQFBC0URBQJ7qssVs5g2FdHY\",\n\n                \"ydDeviceIdMd5\": \"83CBEB3A08842C5CF55E4096C5C16A4A\",\n\n                \"ydSdkType\": 3,\n\n                \"isTampered\": null,\n\n                \"isSimulator\": null,\n\n                \"isRooted\": null,\n\n                \"isMultiRun\": null,\n\n                \"isVpn\": 0,\n\n                \"isProxy\": null,\n\n                \"isHooked\": null,\n\n                \"isInjected\": null,\n\n                \"isDebugged\": null,\n\n                \"isXposed\": null,\n\n                \"isCloud\": 0,\n\n                \"isSuspectCloud\": null,\n\n                \"isRiskRom\": null,\n\n                \"isVm\": null,\n\n                \"isModify\": null,\n\n                \"isModifyApp\": null,\n\n                \"isFlash\": null,\n\n                \"isAutoTouch\": null,\n\n                \"isControlApp\": null,\n\n                \"isScript\": 0,\n\n                \"securityScore\": 90,\n\n                \"isCydiaSubstrate\": 0,\n\n                \"isM1\": 0,\n\n                \"isSpeedUp\": 0,\n\n                \"isAntiJailbreak\": 0\n\n        }\n\n}";
            JSONObject js = JSONObject.parseObject(str);
            Map<String, Object> map = (Map<String, Object>)js;
            list.add(map);
        }
//        util.saveObj(map, "20220424-test");
        System.out.println(list.size());
        util.saveObjListData(list,"20220424-test");
        System.out.println("pushMongoDB()结束");

    }


}