# AliDDNS
利用阿里云解析API编写的动态域名解析应用

## 使用方法

### 配置域名及accessKeyId和accessSecret

* `/src/main/resources/config/ini.settfing` 


```
#配置域名信息，必须是二级域名
Domain = nas.linkq.xyz

#阿里云账户的accessKeyId
accessKeyId = 这里配置accessKeyId

#阿里云账户的accessSecret
accessSecret = 这里配置accessSecret

```


### 设置域名更新间隔时间

* `/src/main/resources/config/cron.setting` 

```
#设置任务执行间隔,目前设置时5分钟检查更新一次
[xyz.linkq.AliDDNS]
Update.update=*/5 * * * *
```