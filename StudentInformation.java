//我哦自己搞得东西
package jxau.software.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentInformation extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -37282670325756674L;
	
	String[] columnNames = { "序号", "学号","班级","姓名","性别","平均分" }; // 定义表格列名数组
	String[][] tableValues = { { "001", "20161888","class1","xx","男","68"}};// 定义表格数据数组
 /*{ "A2", "B2" }, { "A3", "B3" }, { "A4", "B4" }, { "A5", "B5" } */
	public StudentInformation() {
		this.setSize(600, 600);
		this.setTitle("学生信息列表");
		this.setLayout(new BorderLayout());
		JTable table = new JTable(tableValues, columnNames);// 创建指定列名和数据的表格
		JScrollPane scrollPane = new JScrollPane(table);// 创建显示表格的滚动面板
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new StudentInformation();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
