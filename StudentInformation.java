//��Ŷ�Լ���ö���
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
	
	String[] columnNames = { "���", "ѧ��","�༶","����","�Ա�","ƽ����" }; // ��������������
	String[][] tableValues = { { "001", "20161888","class1","xx","��","68"}};// ��������������
 /*{ "A2", "B2" }, { "A3", "B3" }, { "A4", "B4" }, { "A5", "B5" } */
	public StudentInformation() {
		this.setSize(600, 600);
		this.setTitle("ѧ����Ϣ�б�");
		this.setLayout(new BorderLayout());
		JTable table = new JTable(tableValues, columnNames);// ����ָ�����������ݵı��
		JScrollPane scrollPane = new JScrollPane(table);// ������ʾ���Ĺ������
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
