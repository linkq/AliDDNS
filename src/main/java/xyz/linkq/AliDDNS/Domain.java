package xyz.linkq.AliDDNS;

/**
* @author linkq 
* @date : 2019年8月1日 下午9:55:23
*/
public class Domain {

	public Domain(String domain) {
		String[] strs = domain.split("\\.");
		if (strs.length == 3) {
			rr = strs[0];
			domainName = strs[1] + "." + strs[2];
		} else {
			domainName = domain;
			rr = "www";
		}
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getRr() {
		return rr;
	}

	public void setRr(String rr) {
		this.rr = rr;
	}

	public String domainName;
	public String rr;
}
