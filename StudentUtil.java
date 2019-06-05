package jxau.software.javaproject.util;

import java.util.ArrayList;

import jxau.software.javaproject.domain.Student;

/**
 * 学生工具类
 * 
 * @author dell
 *
 */
public class StudentUtil {
	private StudentUtil() {
	}

	/**
	 * 根据学号在学生列表中查询学生信息
	 * 
	 * @param number
	 *            要查询额学生学号
	 * @param lstStudents
	 *            学生列表
	 * @return 若查询到，则返回查询到的学生对象，否则返回null
	 */
	public static Student query(String number, ArrayList<Student> lstStudents) {
		for (Student stu : lstStudents) {
			if (number.equals(stu.getNumber()))
				return stu;
		}
		return null;
	}

	/**
	 * 在学生列表中添加一条学生记录
	 * 
	 * @param stu要添加的学生信息
	 * @param lstStudent
	 *            学生列表
	 * @return 若添加成功返回添加的学生位置索引，否则返回-1
	 */
	public static int add(Student stu, ArrayList<Student> lstStudents) {
		if (query(stu.getNumber(), lstStudents) == null) {
			lstStudents.add(stu);
//			Collections.sort(lstStudents);
			FileUtil.write("Student.dat", lstStudents);
			return lstStudents.indexOf(stu);
		} else { 
			return -1;
		}
	}
/**
 * 在学生列表中删除一条记录
 * @param index 要删除的学生信息
 * @param lstStudents 学生列表
 * @return 删除是否成功
 */
	public static boolean delete(int index, ArrayList<Student> lstStudents) {
		if (index >= 0 && index < lstStudents.size()) {
			lstStudents.remove(index);
			FileUtil.write("Student.dat", lstStudents);
			return true;
		} else {
			return false;
		}
	}
	public static boolean update(int index,Student stu, ArrayList<Student> lstStudents) {
		if(index >= 0 && index < lstStudents.size()) {
			lstStudents.set(index, stu);
			FileUtil.write("Student.dat",lstStudents);
			return true;
		}else {
			return false;
		}
	}

}
