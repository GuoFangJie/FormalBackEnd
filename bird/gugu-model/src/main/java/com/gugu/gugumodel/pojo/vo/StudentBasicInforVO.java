package com.gugu.gugumodel.pojo.vo;

/**
 * @author ljy
 * 管理员端展示学生信息
 */
public class StudentBasicInforVO {
   Long studentId;
   String studentAccount;
   String studentName;
   String studentEmail;

   public Long getStudentId() {
      return studentId;
   }

   public void setStudentId(Long studentId) {
      this.studentId = studentId;
   }

   public String getStudentAccount() {
      return studentAccount;
   }

   public void setStudentAccount(String studentAccount) {
      this.studentAccount = studentAccount;
   }

   public String getStudentName() {
      return studentName;
   }

   public void setStudentName(String studentName) {
      this.studentName = studentName;
   }

   public String getStudentEmail() {
      return studentEmail;
   }

   public void setStudentEmail(String studentEmail) {
      this.studentEmail = studentEmail;
   }
}
