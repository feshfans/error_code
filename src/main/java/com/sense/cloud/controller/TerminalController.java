package com.sense.cloud.controller;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.sense.cloud.entity.Terminal;
import com.sense.cloud.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2016/6/29.
 */

@Controller
@RequestMapping("/terminal")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

    @RequestMapping("add")
    @ResponseBody
    public String add(Terminal terminal){

        terminal.setId(UUID.randomUUID().toString());
        try {
            terminalService.add(terminal);
            return "1";
        }catch (Exception e){
            e.printStackTrace();
            if(e instanceof DuplicateKeyException){
                return "-1";
            }
            return "0";
        }


    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(@RequestParam String id){
        System.out.println("id:"+id);
        try {
            int count=terminalService.delete(id);
            return String.valueOf(count);
        }catch (Exception exception){
            exception.printStackTrace();
            System.out.println("===="+exception.getClass());
            if(exception instanceof DataIntegrityViolationException){
                return "-1";
            }
            return "0";

        }

    }

    @RequestMapping("update")
    @ResponseBody
    public String update(Terminal terminal){

        try {
            terminalService.update(terminal);
            return "1";
        }catch (Exception e){
            e.printStackTrace();
            return "0";
        }

    }

    @RequestMapping("all")
    @ResponseBody
    public List<Terminal> getAll(){
        List<Terminal> list= terminalService.getAll();
        return list;
    }
}
