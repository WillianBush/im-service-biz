package net.chenlin.dp.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadResp {
    private String filePath;
    private String md5Key;
}
