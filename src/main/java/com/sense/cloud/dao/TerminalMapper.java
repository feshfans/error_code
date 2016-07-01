package com.sense.cloud.dao;

import com.sense.cloud.entity.Terminal;

import java.util.List;

public interface TerminalMapper {
    int deleteByPrimaryKey(String id);

    int insert(Terminal record);

    int insertSelective(Terminal record);

    Terminal selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Terminal record);

    int updateByPrimaryKey(Terminal record);

    List<Terminal> getAll();

}