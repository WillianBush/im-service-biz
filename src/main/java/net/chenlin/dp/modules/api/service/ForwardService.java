package net.chenlin.dp.modules.api.service;

import net.chenlin.dp.common.exception.RRException;

public interface ForwardService {
    String getResponseDomain(String requestDomain)  throws RRException;

    String getResponseDomain(String requestDomain,String path)  throws RRException;
}
