package test;

import java.io.FileReader;
import java.io.IOException;

/**
 * @author ywx
 * @ date 2018年11月29日
 */
public class Test {

	public static void main(String[] args) {
		FileReader fr = null;
		try {
			fr = new FileReader("f.dat");
			//调用read方法输出到控制台上
			int len = 0;
			while((len = fr.read())!= -1) {
				System.out.println((char)len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
