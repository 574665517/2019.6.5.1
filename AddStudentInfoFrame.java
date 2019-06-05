package jxau.software.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import jxau.software.javaproject.domain.Student;
import jxau.software.javaproject.util.FontUtil;
import jxau.software.javaproject.util.StudentUtil;

public class AddStudentInfoFrame extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4237250864994570691L;
	
	private StudentInfoManageFrame frm;
	private JButton jbAdd, jbCancel;// 添加的按钮
	private JRadioButton jrbBoy, jrbGirl;// 单选按钮
	private JTextField jtfNumber, jtfName, jtfAvgScore;// 用来改的文本框
	private JComboBox<String> jcbClassName; // 下拉列表来显示班级

	public AddStudentInfoFrame(StudentInfoManageFrame frm) {
		this.frm = frm;
		initGUI();
	}

	private void initGUI() {
		this.setSize(300, 300);
		this.setTitle("添加学生信息界面");
		this.setLayout(new GridLayout(6, 1));
		JPanel[] panels = new JPanel[6];
		for (int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
			this.add(panels[i]);
		}
		panels[5].setLayout(new FlowLayout(FlowLayout.CENTER));

		JLabel jlNumber = new JLabel("学  号：");
		JLabel jlClassName = new JLabel("班  级：");
		JLabel jlName = new JLabel("姓  名：");
		JLabel jlSex = new JLabel("性  别：");
		JLabel jlAvgScore = new JLabel("平均分：");
		jtfNumber = new JTextField(20);
		Vector<String> classNames = new Vector<String>();
		for (int i = 0; i < 15; i++) {
			String className = "软件" + (1601 + i) + "班";
			classNames.add(className);
		}
		jcbClassName = new JComboBox<String>(classNames);
		jtfName = new JTextField(20);
		ButtonGroup bg = new ButtonGroup();
		jrbBoy = new JRadioButton("男", true);
		jrbGirl = new JRadioButton("女");
		bg.add(jrbBoy);
		bg.add(jrbGirl);
		jtfAvgScore = new JTextField(20);
		jbAdd = new JButton("添加");
		jbCancel = new JButton("取消");
		panels[0].add(jlNumber);
		panels[0].add(jtfNumber);
		panels[1].add(jlClassName);
		panels[1].add(jcbClassName);
		panels[2].add(jlName);
		panels[2].add(jtfName);
		panels[3].add(jlSex);
		panels[3].add(jrbBoy);
		panels[3].add(jrbGirl);
		panels[4].add(jlAvgScore);
		panels[4].add(jtfAvgScore);
		panels[5].add(jbAdd);
		panels[5].add(jbCancel);

		jlNumber.setFont(FontUtil.contenFont);
		jlClassName.setFont(FontUtil.contenFont);
		jlName.setFont(FontUtil.contenFont);
		jlSex.setFont(FontUtil.contenFont);
		jlAvgScore.setFont(FontUtil.contenFont);
		jtfNumber.setFont(FontUtil.contenFont);
		jcbClassName.setFont(FontUtil.contenFont);
		jtfName.setFont(FontUtil.contenFont);
		jtfAvgScore.setFont(FontUtil.contenFont);
		jrbBoy.setFont(FontUtil.contenFont);
		jrbGirl.setFont(FontUtil.contenFont);
		jbAdd.setFont(FontUtil.contenFont);
		jbCancel.setFont(FontUtil.contenFont);

		this.setLocationRelativeTo(null);
		this.setVisible(true);
		jbAdd.addActionListener(this);
		jbCancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbAdd) {
			addStuInfo();
		} else {
			this.dispose();
		}
	}

	private void addStuInfo() {
		String number = jtfNumber.getText().trim();
		String className = (String) jcbClassName.getSelectedItem();
		String name = jtfName.getText().trim();
		String sex = "男";
		if (jrbGirl.isSelected()) {
			sex = "女";
		}
		float avgScore = Float.parseFloat(jtfAvgScore.getText().trim());
		if (!number.isEmpty() && !name.isEmpty()) {
			Student stu = new Student(number, className, name, sex, avgScore);
			int index = StudentUtil.add(stu, frm.lstStudents);
			if (index == -1) {
				JOptionPane.showMessageDialog(this, "该学号的学生已存在，不能添加！", "提示", JOptionPane.WARNING_MESSAGE);
			} else {
				frm.loadPage(index / frm.PAGE_SIZE);
				this.dispose();
			}
		} else {
			JOptionPane.showMessageDialog(this, "学生信息填写不全，不能添加！", "提示", JOptionPane.WARNING_MESSAGE);
		}
	}
}
