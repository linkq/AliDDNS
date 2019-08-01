package xyz.linkq.AliDDNS;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsRequest;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse.Record;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;

import cn.hutool.core.date.DateTime;
import cn.hutool.setting.Setting;

/**
* @author linkq 
* @date : 2019年8月1日 下午9:55:13
*/
public class DescribeDomainRecords {
	public List<Record> getRecord(String domainStr) {

		Setting setting = new Setting("config/ini.setting");
		String accessKeyId = setting.getStr("accessKeyId");
		String accessSecret = setting.getStr("accessSecret");
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
		IAcsClient client = new DefaultAcsClient(profile);
		Domain domain = new Domain(domainStr);
		DescribeDomainRecordsRequest request = new DescribeDomainRecordsRequest();
		request.setDomainName(domain.getDomainName());
		request.setTypeKeyWord("a");
		request.setRRKeyWord(domain.getRr());
		List<Record> records = new ArrayList<DescribeDomainRecordsResponse.Record>();
		try {
			DescribeDomainRecordsResponse response = client.getAcsResponse(request);
			List<Record> responseRecords = response.getDomainRecords();
			for (Record record : responseRecords) {
				if (record.getRR().equals(domain.getRr())) {
					records.add(record);
				}
			}
			return records;
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			System.out.println(new SimpleDateFormat("[yyyy-MM-dd:HH-mm-ss] ").format(new DateTime())+"ErrCode:" + e.getErrCode());
			System.out.println(new SimpleDateFormat("[yyyy-MM-dd:HH-mm-ss] ").format(new DateTime())+"ErrMsg:" + e.getErrMsg());
			System.out.println(new SimpleDateFormat("[yyyy-MM-dd:HH-mm-ss] ").format(new DateTime())+"RequestId:" + e.getRequestId());
		}
		return records;

	}
}
