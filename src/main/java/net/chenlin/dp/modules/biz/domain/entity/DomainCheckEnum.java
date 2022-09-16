package net.chenlin.dp.modules.biz.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DomainCheckEnum {

    // 1:普通域名,2:qq绿标域名
    NormalDomain(1),QQCheckedDomain(2);

    private final Integer code;
}
