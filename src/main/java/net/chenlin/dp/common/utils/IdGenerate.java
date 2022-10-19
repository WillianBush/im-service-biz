package net.chenlin.dp.common.utils;

import java.util.UUID;

public class IdGenerate {

    public static String generateUUID(){
        return UUID.randomUUID().toString().toLowerCase().replace("-","");
    }
}
