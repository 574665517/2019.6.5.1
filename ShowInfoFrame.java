//我自己搞的
package jxau.software.frame;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ShowInfoFrame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1102980963940178078L;
	
	private JLabel jsno; // 学号
	private JLabel jclass;// 班级
	private JLabel jname;// 姓名
	private JLabel jsex; // 性别
	private JRadioButton jman, jwoman;
	private JLabel javeragemark; // 平均成绩
	private JTextField psno, pclass, pname, paveragemark;
	private JButton jcheck, jcancel; // 确认 ，取消

	public ShowInfoFrame() {
		this.setSize(500, 500);
		this.setTitle("添加和修改");
		this.setLayout(new GridLayout(6, 1, 5, 5));
		jsno = new JLabel("学号");
		jclass = new JLabel("班级");
		jname = new JLabel("姓名");
		jsex = new JLabel("性别");
		javeragemark = new JLabel("平均成绩");
		psno = new JTextField(30);
		pclass = new JTextField(30);
		pname = new JTextField(30);
		paveragemark = new JTextField(30);
		jman = new JRadioButton("男", true);
		jwoman = new JRadioButton("女");
		JPanel jp = new JPanel(); // 把两个按钮装一起
		jp.add(jman);
		jp.add(jwoman);
		jcheck = new JButton("确认");
		jcheck.addActionListener(this); //加一个监听器
		jcancel = new JButton("取消");
		jcancel.addActionListener(this);
		JPanel jp1 = new JPanel(); // 把这两个也装一起
		jp1.add(jcheck);
		jp1.add(jcancel);

		JPanel panel1 = new JPanel(new FlowLayout());
		JPanel panel2 = new JPanel(new FlowLayout());
		JPanel panel3 = new JPanel(new FlowLayout());
		JPanel panel4 = new JPanel(new FlowLayout());
		JPanel panel5 = new JPanel(new FlowLayout());
		JPanel panel6 = new JPanel(new FlowLayout());

		panel1.add(jsno);
		panel1.add(psno);
		panel2.add(jclass);
		panel2.add(pclass);
		panel3.add(jname);
		panel3.add(pname);
		panel4.add(jsex);
		panel4.add(jp);
		panel5.add(javeragemark);
		panel5.add(paveragemark);
		panel6.add(jp1);

		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.add(panel5);
		this.add(panel6);

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main() {
		new ShowInfoFrame();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jcheck){
		new StudentInformation();
		this.setVisible(false);
		if(e.getSource() == jcancel) {
			System.exit(0);
		}
	}  
	}
}
