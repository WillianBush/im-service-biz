package net.chenlin.dp.modules.biz.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DomainEnum {

    ServerDomain(1),AdvertiseDomain(2);

    private final Integer code;
}
