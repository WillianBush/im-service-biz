# 最多每次60个域名，否则证书会有问题

INSERT INTO web_domain.`domain` (domain_type,domain_name,domain_enable,is_blocked,https_tomcat_jks_address,https_tomcat_pass,create_time,update_time,create_by,update_by,server_id,server_name) VALUES
    (1,'',1,1,NULL,NULL,now(),now(),'','系统检测',1,'ip');


INSERT INTO web_domain.app_domain (domain_type,domain_name,app_base_name,android_resigned_package_name,app_resigned_id,create_time,update_time,create_by,update_by) VALUES
    (1,'','youyue','1',1,now(),now(),'System','System');