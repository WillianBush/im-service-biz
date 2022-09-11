# 最多每次60个域名，否则证书会有问题

-- 服务器域名
-- 妙遇
INSERT INTO web_domain.`domain` (domain_type,domain_name,domain_enable,is_blocked,https_tomcat_jks_address,https_tomcat_pass,create_time,update_time,create_by,update_by,server_id,server_name) VALUES
    (1,'',1,1,NULL,NULL,now(),now(),'','系统检测',1,'47.242.14.229');

INSERT INTO web_domain.app_domain (domain_type,domain_name,app_base_name,android_resigned_package_name,app_resigned_id,create_time,update_time,create_by,update_by) VALUES
    (1,'','miaoyu','1',1,now(),now(),'System','System');

-- 悦爱
INSERT INTO web_domain.`domain` (domain_type,domain_name,domain_enable,is_blocked,https_tomcat_jks_address,https_tomcat_pass,create_time,update_time,create_by,update_by,server_id,server_name) VALUES
    (1,'',1,1,NULL,NULL,now(),now(),'','系统检测',1,'47.242.14.229');

INSERT INTO web_domain.app_domain (domain_type,domain_name,app_base_name,android_resigned_package_name,app_resigned_id,create_time,update_time,create_by,update_by) VALUES
    (1,'','yueai','',2,now(),now(),'System','System');

-- 友约
INSERT INTO web_domain.`domain` (domain_type,domain_name,domain_enable,is_blocked,https_tomcat_jks_address,https_tomcat_pass,create_time,update_time,create_by,update_by,server_id,server_name) VALUES
    (1,'',1,1,NULL,NULL,now(),now(),'','系统检测',1,'47.242.14.229');

INSERT INTO web_domain.app_domain (domain_type,domain_name,app_base_name,android_resigned_package_name,app_resigned_id,create_time,update_time,create_by,update_by) VALUES
    (1,'','youyue','',4,now(),now(),'System','System');

-- 初恋
INSERT INTO web_domain.`domain` (domain_type,domain_name,domain_enable,is_blocked,https_tomcat_jks_address,https_tomcat_pass,create_time,update_time,create_by,update_by,server_id,server_name) VALUES
    (1,'',1,1,NULL,NULL,now(),now(),'','系统检测',1,'47.242.14.229');

INSERT INTO web_domain.app_domain (domain_type,domain_name,app_base_name,android_resigned_package_name,app_resigned_id,create_time,update_time,create_by,update_by) VALUES
    (1,'','chulian','',3,now(),now(),'System','System');


-- 短域名

INSERT INTO web_domain.`domain` (domain_type,domain_name,domain_enable,is_blocked,https_tomcat_jks_address,https_tomcat_pass,create_time,update_time,create_by,update_by,server_id,server_name) VALUES
    (2,'',1,1,NULL,NULL,now(),now(),'','系统检测',2,'8.210.136.237');

INSERT INTO web_domain.app_domain (domain_type,domain_name,app_base_name,android_resigned_package_name,app_resigned_id,create_time,update_time,create_by,update_by) VALUES
    (2,'','chulian','',3,now(),now(),'System','System');

INSERT INTO web_domain.app_domain (domain_type,domain_name,app_base_name,android_resigned_package_name,app_resigned_id,create_time,update_time,create_by,update_by) VALUES
    (2,'','youyue','',4,now(),now(),'System','System');

INSERT INTO web_domain.app_domain (domain_type,domain_name,app_base_name,android_resigned_package_name,app_resigned_id,create_time,update_time,create_by,update_by) VALUES
    (2,'','yueai','',2,now(),now(),'System','System');

INSERT INTO web_domain.app_domain (domain_type,domain_name,app_base_name,android_resigned_package_name,app_resigned_id,create_time,update_time,create_by,update_by) VALUES
    (2,'','miaoyu','',1,now(),now(),'System','System');


