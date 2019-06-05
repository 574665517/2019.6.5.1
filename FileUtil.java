package jxau.software.javaproject.util;

import java.io.*;
import java.util.ArrayList;

import jxau.software.javaproject.domain.Student;

public class FileUtil {
	private FileUtil() {

	}

	/**
	 * ��ȡ�ļ�����
	 * 
	 * @param fileName
	 *            �ļ�������
	 * @return �ı��ļ�����
	 * @throws Exception
	 */

	public static String read(String fileName) throws Exception {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		br = new BufferedReader(new FileReader(fileName));
		String line;
		line = br.readLine();
		while (line != null) {
			sb.append(line + "\n");
			line = br.readLine();
		}
		br.close();
		return sb.toString();
	}

	/**
	 * д�ļ�����
	 * 
	 * @param fileName
	 *            Ҫд���ļ���
	 * @param content
	 *            ���ļ���д�������
	 * @throws IOException
	 */
	public static void write(String fileName, String content) throws IOException {
		BufferedWriter tw = new BufferedWriter(new FileWriter(fileName));
		tw.write(content);
		tw.close();
	}

	public static void write(String fileName, ArrayList<Student> lstStudents) {
		StringBuffer sb = new StringBuffer();
		for (Student stu : lstStudents) {
			sb.append(stu.toString());
		}
		try {
			write(fileName, sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
