package xyz.linkq.AliDDNS;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.hutool.core.date.DateTime;
import cn.hutool.http.HttpUtil;

/**
* @author linkq 
* @date : 2019年8月1日 下午9:55:30
*/
public class OutIP {

	public String getOutIp() {
		String ip = "";
		String dataString = HttpUtil.get("https://pv.sohu.com/cityjson?ie=utf-8");
		String regEx = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(dataString);

		while (m.find()) {
			ip = m.group();
			System.out.println(new SimpleDateFormat("[yyyy-MM-dd:HH-mm-ss] ").format(new DateTime())+"获取到本机外网IP为："+ip);
			break;//   加break则提取string中的一个IP
		}
		return ip;
	}
}
