package com.sense.cloud.service.impl;

import com.sense.cloud.dao.ErrorCodeMapper;
import com.sense.cloud.entity.ErrorCode;
import com.sense.cloud.service.CodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 */
@Service("codeService")
public class CodeServiceImpl implements CodeService {

    @Resource
    private ErrorCodeMapper errorCodeMapper;

    public int add(ErrorCode errorCode) {
        return errorCodeMapper.insert(errorCode);
    }

    public int delete(String id) {
        return errorCodeMapper.deleteByPrimaryKey(id);
    }

    public int update(ErrorCode errorCode) {
        return errorCodeMapper.updateByPrimaryKey(errorCode);
    }

    public List<ErrorCode> getListByTerminal(String terminalId) {
        return errorCodeMapper.getListByTerminal(terminalId);
    }

    public List<ErrorCode> search(String searchText) {
        return errorCodeMapper.searchCodeOrDesc(searchText);
    }

    public List<ErrorCode> getList() {
        return errorCodeMapper.getAll();
    }
}
