package xyz.linkq.AliDDNS;

import cn.hutool.core.date.DateTime;
import cn.hutool.setting.Setting;
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse.Record;

import java.text.SimpleDateFormat;
import java.util.List;

/**
* @author linkq 
* @date : 2019年8月1日 下午9:55:36
*/
public class Update {
	public void update() {
		System.out.println(new SimpleDateFormat("[yyyy-MM-dd:HH-mm-ss] ").format(new DateTime())+"开始执行任务：更新DNS解析！");
    	Setting setting = new Setting("config/ini.setting");
    	String domain=setting.getStr("Domain");

    	//获取当前域名解析记录
    	DescribeDomainRecords describeDomainRecords = new DescribeDomainRecords();
    	List<Record> records = describeDomainRecords.getRecord(domain);
        OutIP ip = new OutIP();
        String localOutIp = ip.getOutIp();
    	if(records.size()==0) {
    		//新增解析记录
    		System.out.println(new SimpleDateFormat("[yyyy-MM-dd:HH-mm-ss] ").format(new DateTime())+"DNS解析记录中无此域名信息，新增解析记录！");
    		Record record = new Record();
    		record.setDomainName(new Domain(domain).getDomainName());
    		record.setRR(new Domain(domain).getRr());
    		record.setType("a");
    		record.setValue(localOutIp);
    		AddDomainRecord addDomainRecord = new AddDomainRecord();
    		addDomainRecord.add(record);
    		return;
    	}

        String DnsIp =records.get(0).getValue();
        if(!localOutIp.equals(DnsIp)) {
        	//修改dns解析记录
        	System.out.println(new SimpleDateFormat("[yyyy-MM-dd:HH-mm-ss] ").format(new DateTime())+"修改DNS解析记录！");
        	UpdateDomainRecord update = new UpdateDomainRecord();
        	update.update(records.get(0), localOutIp);
        }else {
			System.out.println(new SimpleDateFormat("[yyyy-MM-dd:HH-mm-ss] ").format(new DateTime())+"DNS解析记录值与本地外网IP相同，无需更改！");
		}
	}
}
