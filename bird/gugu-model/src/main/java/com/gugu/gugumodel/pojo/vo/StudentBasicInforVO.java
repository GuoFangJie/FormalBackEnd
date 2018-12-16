package com.gugu.gugumodel.pojo.vo;

/**
 * @author ljy
 * 管理员修改学生信息，传给后端的学生信息
 */
public class StudentBasicInforVO {
   String studentAccount;
   String studentName;
   String studentEmail;

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
