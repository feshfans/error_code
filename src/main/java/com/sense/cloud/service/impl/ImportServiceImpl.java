package com.sense.cloud.service.impl;

import com.sense.cloud.dao.ErrorCodeMapper;
import com.sense.cloud.entity.ErrorCode;
import com.sense.cloud.service.ImportService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Administrator on 2016/7/1.
 */

@Service("importService")
public class ImportServiceImpl implements ImportService {

    @Resource
    private ErrorCodeMapper errorCodeMapper;

    public int importExcel(String excel,String terminalId) {

        try {

            int successCount=0;

            POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream(excel));

            Workbook workbook=new HSSFWorkbook(fs);
            Sheet sheet= workbook.getSheetAt(0);

            int rowNum=sheet.getLastRowNum();
            for(int i=1;i<rowNum;i++){
                Row row=sheet.getRow(i);

                Cell codeCell= row.getCell(0);
                Cell descCell=row.getCell(1);
                Cell remarkCell=row.getCell(2);

                ErrorCode errorCode=new ErrorCode();
                errorCode.setId(UUID.randomUUID().toString());
                errorCode.setCode(codeCell.getStringCellValue());
                errorCode.setDescription(descCell.getStringCellValue());
                errorCode.setRemarks(remarkCell.getStringCellValue());
                errorCode.setTerminalId(terminalId);

                successCount+= errorCodeMapper.insert(errorCode);

            }

            return successCount;

        } catch (IOException e) {
            e.printStackTrace();
        }


        return -1;
    }
}
