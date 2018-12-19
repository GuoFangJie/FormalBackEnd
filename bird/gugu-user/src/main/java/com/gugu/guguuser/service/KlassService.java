package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.*;
import com.gugu.gugumodel.pojo.entity.KlassEntity;
import com.gugu.gugumodel.pojo.entity.StudentEntity;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author ren
 */
@Service
public class KlassService {
    @Autowired
    KlassDao klassDao;
    @Autowired
    StudentDao studentDao;
    @Autowired
    KlassStudentDao klassStudentDao;
    /**
     * 新建班级
     * @param klassEntity
     * @return
     */
    public Long newKlass(KlassEntity klassEntity) {
        return klassDao.newKlass(klassEntity);
    }

    /**
     * 导入学生名单
     * @param multipartFile
     * @param klassId
     * @return
     */
    public boolean importStudentList(MultipartFile multipartFile, Long klassId){
        Workbook workbook=null;
        try {
            workbook=new XSSFWorkbook(multipartFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet=workbook.getSheetAt(0);
        int numOfRow=sheet.getLastRowNum();
        for(int i=2;i<numOfRow;i++){
            Row row=sheet.getRow(i);
            Long studentId=studentDao.getStudentByAccount(row.getCell(0).getStringCellValue());
            if(studentId==null) {
                StudentEntity studentEntity = new StudentEntity();
                studentEntity.setStudentName(row.getCell(1).getStringCellValue());
                studentEntity.setAccount(row.getCell(0).getStringCellValue());
                studentDao.newStudent(studentEntity);
                studentId=studentEntity.getId();
            }
            klassStudentDao.newStudentToClass(klassId,studentId,klassDao.getCourseIdByKlass(klassId));
        }
        return true;
    }

    /**
     * 删除班级及相关的数据
     * @param klassId
     * @return
     */
    public boolean deleteClass(Long klassId){
        return klassDao.deleteKlassById(klassId);
    }
}
