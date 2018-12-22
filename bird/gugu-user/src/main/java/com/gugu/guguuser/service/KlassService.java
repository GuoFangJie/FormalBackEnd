package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.*;
import com.gugu.gugumodel.entity.KlassEntity;
import com.gugu.gugumodel.entity.StudentEntity;
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
    @Autowired
    KlassRoundDao klassRoundDao;
    @Autowired
    KlassSeminarDao klassSeminarDao;
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
        klassStudentDao.deleteByKlassId(klassId);
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
                studentEntity.setStudentName(row.getCell(1).getStringCellValue().replaceAll(" ",""));
                studentEntity.setAccount(row.getCell(0).getStringCellValue().replaceAll(" ",""));
                studentDao.newStudent(studentEntity);
                studentId=studentEntity.getId();
            }
            klassStudentDao.newStudentToClass(klassId,studentId,klassDao.getCourseIdByKlass(klassId));
        }
        return true;
    }

    /**
     * 删除班级及其子数据
     * @param klassId
     * @return
     */
    public boolean deleteClass(Long klassId){
        klassStudentDao.deleteByKlassId(klassId);
        klassRoundDao.deleteKlassRoundByKlassId(klassId);
        klassSeminarDao.deleteKlassSeminar(klassId);
        klassDao.deleteKlassById(klassId);
        return true;
    }

    /**
     * 根据学生和课程获取班级id
     * @param courseId
     * @param studentId
     * @return
     */
    public Long getKlassIdByCourseAndStudent(Long courseId,Long studentId){
        return klassStudentDao.getKlassIdByCourseAndStudent(courseId,studentId);
    }
}
