package com.manage.webmgr.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.manage.core.exception.BizException;
import com.manage.core.exception.BizExceptionCode;
import com.manage.webmgr.result.ResultCode;

public class ExcelUtil {

	/**
	 * @Title: getDataFromExcel
	 * @Description: 导入excel表中数据
	 * @author ChenXiaofen
	 * @date 2019年3月20日
	 */
	public static Workbook getDataFromExcel(String filePath)
    {
        
        FileInputStream fis =null;
        Workbook wookbook = null;
        //判断是否为excel类型文件
        if(!filePath.endsWith(".xls")&&!filePath.endsWith(".xlsx")) {
        	System.out.println("文件不是excel类型");
        	throw new BizException("文件不是excel类型",String.valueOf(ResultCode.FAIL_CODE));
        }
       
        try {
        	//获取一个绝对地址的流
            fis = new FileInputStream(filePath);
        	if(filePath.endsWith(".xls")) {
        		//2003版
        		wookbook = new HSSFWorkbook(fis);//得到工作簿
        	}else if(filePath.endsWith(".xlsx")) {
        		//2007版本的excel，用.xlsx结尾
        		wookbook = new XSSFWorkbook(fis);//得到工作簿
        	}
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wookbook;
    }
	
}
