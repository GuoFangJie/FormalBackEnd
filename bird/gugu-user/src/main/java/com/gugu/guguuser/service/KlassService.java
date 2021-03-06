package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.*;
import com.gugu.gugumodel.entity.KlassEntity;
import com.gugu.gugumodel.entity.KlassSeminarEntity;
import com.gugu.gugumodel.entity.StudentEntity;
import com.gugu.guguuser.util.SerialUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

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

    @Autowired
    SerialUtil serialUtil;
    /**
     * 新建班级
     * @param klassEntity
     * @return
     */
    public Long newKlass(KlassEntity klassEntity) {
        //计算klass_serial
        ArrayList<Byte> klassSerial=klassDao.getSerial(klassEntity.getCourseId());
        serialUtil.setSerialList(klassSerial);
        klassEntity.setKlassSerial(serialUtil.calcuSerial());
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
        //删除之前旧的班级名单
        klassStudentDao.deleteByKlassId(klassId);
        for(int i=2;i<numOfRow;i++){
            Row row=sheet.getRow(i);
            Long studentId=studentDao.getStudentByAccount(row.getCell(0).getStringCellValue().replace(" ",""));
            if(studentId==null) {
                StudentEntity studentEntity = new StudentEntity();
                String studentName=row.getCell(1).getStringCellValue().replace(" ","");
                String account=row.getCell(0).getStringCellValue().replace(" ","");
                studentEntity.setStudentName(studentName.replace(" ",""));
                studentEntity.setAccount(account.replace(" ",""));
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


    /**@author ljy
     * 按照id获取班级下讨论课
     * @param classId
     * @return
     */
    public KlassEntity getKlassById(Long classId){
         return klassDao.getKlassById(classId);
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
