package com.sense.cloud.dao;

import com.sense.cloud.entity.ErrorCode;

import java.util.List;

public interface ErrorCodeMapper {
    int deleteByPrimaryKey(String id);

    int insert(ErrorCode record);

    int insertSelective(ErrorCode record);

    ErrorCode selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ErrorCode record);

    int updateByPrimaryKey(ErrorCode record);

    List<ErrorCode> getListByTerminal(String terminalId);

    List<ErrorCode> searchCodeOrDesc(String searchText);

    List<ErrorCode> getAll();
}