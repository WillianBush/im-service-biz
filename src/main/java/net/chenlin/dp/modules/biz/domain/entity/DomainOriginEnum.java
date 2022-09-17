package net.chenlin.dp.modules.biz.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DomainOriginEnum {

    // 1:自己购买的域名,2:三方团队的域名
    OfficialDomain(1),ThirdDomain(2);

    private final Integer code;
}
