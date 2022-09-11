create table web_domain.app_base
(
    id bigint auto_increment
        primary key,
    app_name varchar(20) not null comment 'app名字',
    app_android_origin_address varchar(255) null comment '安卓原包地址',
    app_ios_origin_addrss varchar(255) null comment 'ios原包地址',
    app_android_origin_package_name varchar(50) null comment '安卓原始包名',
    app_ios_origin_package_name varchar(50) null comment 'ios包名',
    app_android_keystore_name varchar(50) null comment '安卓证书名',
    app_android_keystore_password varchar(50) null comment '安卓证书密码',
    app_android_keystore_address varchar(255) null comment '安卓证书地址',
    app_home_page_server_ids varchar(20) null  comment '落地页服务器id',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by varchar(20) default '' not null comment '创建人',
    update_by varchar(20) default '' not null comment '更新人',
    constraint app_app_name_uindex
        unique (app_name)
)
    comment 'app基本信息' collate=utf8mb4_unicode_ci;

create table web_domain.app_domain
(
    id bigint auto_increment
        primary key,
    domain_type int not null comment '1: 服务器域名；2 短域名',
    domain_name varchar(30) not null comment '域名',
    app_base_name varchar(20) not null comment 'app原包名',
    android_resigned_package_name varchar(255) null comment '安卓重签后的包名',
    app_resigned_id bigint not null comment '安卓重签后的id',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by varchar(20) default '' not null comment '创建人',
    update_by varchar(20) default '' not null comment '更新人'
);

create index idx_app_resign
    on web_domain.app_domain (app_resigned_id);

create index idx_app_resign_name
    on web_domain.app_domain (android_resigned_package_name);

create index idx_domain
    on web_domain.app_domain (domain_name);

create index idx_domain_type
    on web_domain.app_domain (domain_type);

create index idx_time_create
    on web_domain.app_domain (create_time);

create index idx_time_update
    on web_domain.app_domain (update_time);

create table web_domain.app_resigned
(
    id bigint auto_increment
        primary key,
    android_resigned_download_address varchar(255) null,
    ios_download_addrss varchar(255) null,
    android_download_times int null,
    ios_download_times int null,
    app_base_id bigint not null,
    app_base_name varchar(20) not null,
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    android_resigned_package_name varchar(255) null comment '安卓重签后的报名',
    create_by varchar(20) default '' not null comment '创建人',
    update_by varchar(20) default '' not null comment '更新人'
)
    comment '重签后的app信息' collate=utf8mb4_unicode_ci;

create table web_domain.domain
(
    id bigint auto_increment
        primary key,
    domain_type int not null comment '1: 服务器域名；2 短域名',
    domain_name varchar(30) not null comment '域名',
    domain_enable tinyint default 2 not null comment '1:启用；2：禁用',
    is_blocked int default 1 not null comment '是否被封， 1：正常 2：不可用',
    https_tomcat_jks_address varchar(255) null comment '域名证书地址',
    https_tomcat_pass varchar(20) null comment '域名证书密码',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by varchar(20) default '' not null comment '创建人',
    update_by varchar(20) default '' not null comment '更新人',
    constraint idx_domain
        unique (domain_name)
)
    collate=utf8mb4_unicode_ci;

create index idx_domain_type
    on web_domain.domain (domain_type);

create index idx_time_create
    on web_domain.domain (create_time);

create index idx_time_update
    on web_domain.domain (update_time);


create table web_domain.server
(
    id int auto_increment
        primary key,
    ssh_account_name varchar(20) not null comment 'SSH账号',
    ssh_account_password varchar(30) not null comment 'SSH密码',
    ssh_account_type tinyint default 1 not null comment '证书类型:  1:密码 , 2证书',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    create_by varchar(20) default '' not null comment '创建人',
    update_by varchar(20) default '' not null comment '更新人',
    server_ip varchar(60) null comment '服务器IP',
    server_name varchar(20) null comment '服务器名'
)
    comment '服务器基本信息' collate=utf8mb4_unicode_ci;


create table web_domain.app_promotion
(
    id bigint auto_increment
        primary key,
    promotion_url varchar(20) not null comment '推广url',
    app_resigned_d bigint null comment '重签后的AppID',
    app_base_id int not null comment 'AppID原包',
    app_name varchar(50) not null comment 'app名字',
    promotion_domain varchar(50) null comment '短域名',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    expire_time timestamp default CURRENT_TIMESTAMP not null comment '过期时间',
    constraint app_promotion_promotion_url_promotion_domain_uindex
        unique (promotion_url, promotion_domain)
)
    comment '域名URL';


