package xyz.linkq.AliDDNS;

import java.text.SimpleDateFormat;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse.Record;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordRequest;
import com.aliyuncs.alidns.model.v20150109.UpdateDomainRecordResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;

import cn.hutool.core.date.DateTime;
import cn.hutool.setting.Setting;

/**
* @author linkq 
* @date : 2019年8月1日 下午9:55:42
*/
public class UpdateDomainRecord {

	public void update(Record record, String updateIp) {
		Setting setting = new Setting("config/ini.setting");
		String accessKeyId = setting.getStr("accessKeyId");
		String accessSecret = setting.getStr("accessSecret");
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
		IAcsClient client = new DefaultAcsClient(profile);

		UpdateDomainRecordRequest request = new UpdateDomainRecordRequest();
		request.setRecordId(record.getRecordId());
		request.setRR(record.getRR());
		request.setType(record.getType());
		request.setValue(updateIp);

		try {
			UpdateDomainRecordResponse response = client.getAcsResponse(request);
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
