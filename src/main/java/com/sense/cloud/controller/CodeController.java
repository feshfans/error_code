package com.sense.cloud.controller;

import com.sense.cloud.entity.ErrorCode;
import com.sense.cloud.entity.ResponseMessage;
import com.sense.cloud.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2016/6/30.
 */
@Controller
@RequestMapping("/code")
public class CodeController {

    @Autowired
    private CodeService codeService;

    @RequestMapping("add")
    @ResponseBody
    public ResponseMessage add(ErrorCode errorCode){
        ResponseMessage message=new ResponseMessage();

        try {
            errorCode.setId(UUID.randomUUID().toString());
            codeService.add(errorCode);
            message.setCode(1);
            message.setMessage("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            if(e instanceof DuplicateKeyException){
                message.setCode(-1);
            }else {
                message.setCode(0);
            }
            message.setMessage("添加失败");
        }

        return message;
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResponseMessage delete(String id){
        ResponseMessage message=new ResponseMessage();

        try {
            codeService.delete(id);
            message.setCode(1);
            message.setMessage("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            message.setCode(0);
            message.setMessage("删除失败");
        }

        return message;
    }


    @RequestMapping("update")
    @ResponseBody
    public ResponseMessage update(ErrorCode errorCode){
        ResponseMessage message=new ResponseMessage();
        try {
            codeService.update(errorCode);
            message.setCode(1);
            message.setMessage("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            message.setCode(0);
            message.setMessage("修改失败");
        }

        return message;
    }

    @RequestMapping("search")
    @ResponseBody
    public List<ErrorCode> getSearchList(String searchText){
        try {
            return codeService.search(searchText);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @RequestMapping("listByTerminal")
    @ResponseBody
    public List<ErrorCode> getListByTerminal(String id){
        try {
            return codeService.getListByTerminal(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @RequestMapping("listAll")
    @ResponseBody
    public List<ErrorCode> getAll(){
        try {
            return codeService.getList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
