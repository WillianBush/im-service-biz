package net.chenlin.dp.modules.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QiLingResponse {

    private String  msg;
    private Boolean statu;
    private Integer code;
    private Integer count;
    private String  reason;
    private String  describe;
    private String  url;

}
