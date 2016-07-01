package com.sense.cloud.service.impl;

import com.sense.cloud.dao.TerminalMapper;
import com.sense.cloud.entity.Terminal;
import com.sense.cloud.service.TerminalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/6/29.
 */
@Service("terminalService")
public class TermianlServiceImpl implements TerminalService {

    @Resource
    private TerminalMapper terminalMapper;

    public void add(Terminal terminal) {
        terminalMapper.insert(terminal);
    }

    public int delete(String id) {
        return terminalMapper.deleteByPrimaryKey(id);
    }

    public void update(Terminal terminal) {
        terminalMapper.updateByPrimaryKey(terminal);
    }

    public List<Terminal> getAll() {
        return terminalMapper.getAll();
    }
}
