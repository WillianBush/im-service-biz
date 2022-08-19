alter table app_promotion
    add column promotion_domain varchar(50) null comment '推广域名';

alter table app_promotion
    add column  create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间';

alter table app_promotion
    add column  expire_time timestamp default current_timestamp not null comment '过期时间';

drop index app_promotion_promotion_url_uindex on app_promotion;

create unique index app_promotion_promotion_url_promotion_domain_uindex
    on app_promotion (promotion_url desc, promotion_domain desc);