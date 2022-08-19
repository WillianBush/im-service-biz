package net.chenlin.dp.modules.biz.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DomainBlockEnum {
    Blocked(2),OK(1);

    private final Integer code;
}
