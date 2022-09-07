

create table web_domain.app_promotion
(
    id bigint auto_increment
        primary key,
    promotion_url varchar(20) not null comment '推广url',
    app_resigned_d bigint null comment '重签后的AppID',
    app_base_id int not null comment 'AppID原包',
    app_name varchar(50) not null comment 'app名字',
    promotion_domain varchar(50) null comment '推广域名',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    expire_time timestamp default CURRENT_TIMESTAMP not null comment '过期时间',
    constraint app_promotion_promotion_url_promotion_domain_uindex
        unique (promotion_url, promotion_domain)
)
    comment '域名URL';

