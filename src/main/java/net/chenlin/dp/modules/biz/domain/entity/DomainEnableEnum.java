package net.chenlin.dp.modules.biz.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DomainEnableEnum {

    Enable(1),Disable(2);

    private final Integer code;
}
