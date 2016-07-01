package com.sense.cloud.service;

import com.sense.cloud.entity.ErrorCode;

import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 */
public interface CodeService {
    int add(ErrorCode errorCode);
    int delete(String id);
    int update(ErrorCode errorCode);
    List<ErrorCode> getListByTerminal(String terminalId);
    List<ErrorCode> search(String searchText);
    List<ErrorCode> getList();
}
