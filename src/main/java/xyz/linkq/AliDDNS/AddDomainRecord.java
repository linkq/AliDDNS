package xyz.linkq.AliDDNS;

import java.text.SimpleDateFormat;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.AddDomainRecordRequest;
import com.aliyuncs.alidns.model.v20150109.AddDomainRecordResponse;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse.Record;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import cn.hutool.core.date.DateTime;
import cn.hutool.setting.Setting;

/**
* @author linkq 
* @date : 2019年8月1日 下午9:53:42
*/
public class AddDomainRecord {

    public  void add(Record record) {
		Setting setting = new Setting("config/ini.setting");
    	String accessKeyId = setting.getStr("accessKeyId");
    	String accessSecret = setting.getStr("accessSecret");
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        AddDomainRecordRequest request = new AddDomainRecordRequest();
        request.setDomainName(record.getDomainName());
        request.setRR(record.getRR());
        request.setType(record.getType());
        request.setValue(record.getValue());

        try {
            AddDomainRecordResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println(new SimpleDateFormat("[yyyy-MM-dd:HH-mm-ss] ").format(new DateTime())+"ErrCode:" + e.getErrCode());
            System.out.println(new SimpleDateFormat("[yyyy-MM-dd:HH-mm-ss] ").format(new DateTime())+"ErrMsg:" + e.getErrMsg());
            System.out.println(new SimpleDateFormat("[yyyy-MM-dd:HH-mm-ss] ").format(new DateTime())+"RequestId:" + e.getRequestId());
        }

    }
}

