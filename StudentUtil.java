package jxau.software.javaproject.util;

import java.util.ArrayList;

import jxau.software.javaproject.domain.Student;

/**
 * ѧ��������
 * 
 * @author dell
 *
 */
public class StudentUtil {
	private StudentUtil() {
	}

	/**
	 * ����ѧ����ѧ���б��в�ѯѧ����Ϣ
	 * 
	 * @param number
	 *            Ҫ��ѯ��ѧ��ѧ��
	 * @param lstStudents
	 *            ѧ���б�
	 * @return ����ѯ�����򷵻ز�ѯ����ѧ�����󣬷��򷵻�null
	 */
	public static Student query(String number, ArrayList<Student> lstStudents) {
		for (Student stu : lstStudents) {
			if (number.equals(stu.getNumber()))
				return stu;
		}
		return null;
	}

	/**
	 * ��ѧ���б������һ��ѧ����¼
	 * 
	 * @param stuҪ��ӵ�ѧ����Ϣ
	 * @param lstStudent
	 *            ѧ���б�
	 * @return ����ӳɹ�������ӵ�ѧ��λ�����������򷵻�-1
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
 * ��ѧ���б���ɾ��һ����¼
 * @param index Ҫɾ����ѧ����Ϣ
 * @param lstStudents ѧ���б�
 * @return ɾ���Ƿ�ɹ�
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
