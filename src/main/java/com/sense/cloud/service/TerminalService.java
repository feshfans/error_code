package com.sense.cloud.service;

import com.sense.cloud.entity.Terminal;

import java.util.List;

/**
 * Created by Administrator on 2016/6/29.
 */
public interface TerminalService {

    public void add(Terminal terminal);
    public int delete(String id);
    public void update(Terminal terminal);

    public List<Terminal> getAll();
}
