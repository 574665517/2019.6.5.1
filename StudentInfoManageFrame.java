package jxau.software.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import jxau.software.javaproject.domain.Student;
import jxau.software.javaproject.util.FileUtil;
import jxau.software.javaproject.util.FontUtil;
import jxau.software.javaproject.util.StudentUtil;

public class StudentInfoManageFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3143787571749351200L;
	
	static final int PAGE_SIZE = 20;
	private JTable jtStudentsInfo;// 学生信息表格
	private JScrollPane pane;
	private JLabel jlPageInfo;
	private JButton jbFirstPage, jbPageUp, jbPageDown, jbLastPage; // 首页 最后一页 的按钮
	private JButton jbAdd, jbDel, jbQuery, jbUpdate;// 添加 删除 查询 更新 的按钮
	private JTextField jtfStuNumer;// 要查询的学生学号输入框
	private int currentPage, pageCount;// 当前的页码，总页数
	public ArrayList<Student> lstStudents;
	private Vector<String> columnNames; // 学生信息列表表头

	public StudentInfoManageFrame() throws Exception {
		inti();
		loadStudentInfo();
	}

	/* 加载数据 */
	private void loadStudentInfo() throws Exception {
		columnNames = new Vector<String>();
		columnNames.add("序号");
		columnNames.add("学号");
		columnNames.add("班级");
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("平均分");
		jtStudentsInfo = new JTable(null, columnNames);
		pane.setViewportView(jtStudentsInfo);
		/* 读取文件中学生的信息，并保存至ArrayList的学生列表中 */
		String[] stusInfo = FileUtil.read("student.dat").split("\n");
		lstStudents = new ArrayList<Student>();
		for (int i = 0; i < stusInfo.length; i++) {
			String[] stus = stusInfo[i].split("\t"); // 分隔文件中的数据 用\t来
			if (stusInfo.length < 5) {
				JOptionPane.showMessageDialog(this, "学生信息数据格式有误，请核对!", "提示", JOptionPane.WARNING_MESSAGE);
				continue;
			}
			Student stu = new Student(stus[0], stus[1], stus[2], stus[3], Float.parseFloat(stus[4]));
			lstStudents.add(stu);
		}
		loadPage(0);
		setTabelFormat();
	}

	private void setTabelFormat() {
		/* 设置内容字体 */
		jtStudentsInfo.setFont(FontUtil.contenFont);
		/* 设置表头字体 */
		jtStudentsInfo.getTableHeader().setFont(FontUtil.tableHeaderFont);
		/* 设置列宽 */
		jtStudentsInfo.getColumn("序号").setMaxWidth(50);
		jtStudentsInfo.getColumn("性别").setMaxWidth(50);
		/* 设置行高 */
		jtStudentsInfo.setRowHeight(30);
		/* 设置内容居中显示 */
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		jtStudentsInfo.setDefaultRenderer(Object.class, tcr);
		/* 设置选中行的前景色和背景色 */
		jtStudentsInfo.setSelectionBackground(Color.YELLOW);
		jtStudentsInfo.setSelectionForeground(Color.RED);
	}

	/* 根据页码加载某一页的学生信息 */
	public void loadPage(int pageIndex) {
		currentPage = pageIndex;
		pageCount = getPageCount();
		setButtonEnable();
		if (pageIndex >= pageCount) {
			JOptionPane.showMessageDialog(this, "页码" + pageIndex + "不存在", "提示", JOptionPane.WARNING_MESSAGE);
		} else {
			jlPageInfo.setText("共" + pageCount + "页,当前第" + (currentPage + 1) + "页");
		}
		int beginIndex = pageIndex + PAGE_SIZE;
		int endIndex = beginIndex + PAGE_SIZE;
		if (pageIndex == pageCount - 1) {
			endIndex = lstStudents.size();
		}
		Vector<Vector<Object>> rowData = new Vector<Vector<Object>>();
		for (int index = beginIndex; index < endIndex; index++) {
			Vector<Object> row = new Vector<Object>();
			row.add(index + 1);
			Student stu = lstStudents.get(index);
			row.add(stu.getNumber());
			row.add(stu.getClassName());
			row.add(stu.getName());
			row.add(stu.getSex());
			row.add(stu.getAvgScore());
			rowData.add(row);
		}
		jtStudentsInfo = new JTable(rowData, columnNames);
		pane.setViewportView(jtStudentsInfo);
		setTabelFormat();
	}

	private void setButtonEnable() {
		if (pageCount <= 0) {
			jbFirstPage.setEnabled(false);
			jbPageUp.setEnabled(false);
			jbPageDown.setEnabled(false);
			jbLastPage.setEnabled(false);
		}
		if (pageCount == 0) {
			jbFirstPage.setEnabled(false);
			jbPageUp.setEnabled(false);
		} else {
			jbFirstPage.setEnabled(true);
			jbPageUp.setEnabled(false);
		}
		if (currentPage == pageCount - 1) {
			jbPageDown.setEnabled(false);
			jbLastPage.setEnabled(false);
		} else {
			jbPageDown.setEnabled(true);
			jbLastPage.setEnabled(true);
		}
	}

	private int getPageCount() {
		if (lstStudents == null)
			return 0;
		int count = lstStudents.size() / PAGE_SIZE;
		if (lstStudents.size() % PAGE_SIZE == 0)
			return count;
		else
			return count + 1;
	}

	// 初始化界面
	public void inti() {
		this.setTitle("学生管理界面");
		this.setLayout(new BorderLayout());
		JLabel jlTitle = new JLabel("学生信息列表");
		jbFirstPage = new JButton("首页");
		jbPageUp = new JButton("上一页");
		jbPageDown = new JButton("下一页");
		jbLastPage = new JButton("末页");
		jlPageInfo = new JLabel();
		jbAdd = new JButton("添加");
		jbDel = new JButton("删除");
		jbQuery = new JButton("查询");
		jbUpdate = new JButton("修改");
		jtfStuNumer = new JTextField(20);
		pane = new JScrollPane();// 学生列表信息的容器
		this.add(pane, BorderLayout.CENTER);
		JPanel[] panels = new JPanel[6];
		for (int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel();
		}
		/* panels[0] --- 学生信息列表标题 */
		panels[0].setLayout(new FlowLayout());
		panels[0].add(jlTitle);
		this.add(panels[0], BorderLayout.NORTH);
		/* panels[1] --- 管理界面的底部三行 */
		panels[1].setLayout(new GridLayout(4, 1));
		this.add(panels[1], BorderLayout.SOUTH);
		panels[1].add(panels[2]);
		panels[1].add(panels[3]);
		panels[1].add(panels[4]);
		panels[1].add(panels[5]);
		/* panels[2] --- 分页的信息 */
		panels[2].setLayout(new FlowLayout());
		panels[2].add(jlPageInfo);
		/* panels[3] --- 分页的按钮 */
		panels[3].setLayout(new FlowLayout());
		panels[3].add(jbFirstPage);
		panels[3].add(jbPageUp);
		panels[3].add(jbPageDown);
		panels[3].add(jbLastPage);
		/* panels[4] --- 添、删、改的按钮 */
		panels[4].setLayout(new FlowLayout());
		panels[4].add(jbAdd);
		panels[4].add(jbDel);
		panels[4].add(jbUpdate);
		/* panels[5] --- 查询按钮及查询的学号输入框 */
		panels[5].setLayout(new FlowLayout());
		panels[5].add(jbQuery);
		panels[5].add(jtfStuNumer);
		/* 设置控件字体 */
		jlTitle.setFont(FontUtil.titleFont);
		jtfStuNumer.setFont(FontUtil.contenFont);
		jlPageInfo.setFont(FontUtil.contenFont);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		jbFirstPage.addActionListener(this);
		jbPageUp.addActionListener(this);
		jbPageDown.addActionListener(this);
		jbLastPage.addActionListener(this);
		jbAdd.addActionListener(this);
		jbDel.addActionListener(this);
		jbQuery.addActionListener(this);
		jbUpdate.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbFirstPage) {
			loadPage(0);
		}
		if (e.getSource() == jbPageUp) {
			loadPage(--currentPage);
		}
		if (e.getSource() == jbPageDown) {
			loadPage(++currentPage);
		}
		if (e.getSource() == jbLastPage) {
			loadPage(pageCount - 1);
		}
		if (e.getSource() == jbQuery) {
			String number = jtfStuNumer.getText().trim();
			if (number.isEmpty()) {
				JOptionPane.showMessageDialog(this, "请输入要查询的学生学号:", "提示", JOptionPane.WARNING_MESSAGE);
			} else {
				Student stu = StudentUtil.query(number, lstStudents);
				String msg = "";
				if (stu == null) {
					msg = "您要查询的学号为" + number + "的学号不存在";
				} else {/* 报错的提示 */
					msg = "学号:" + stu.getNumber() + "\n" + "班级:" + stu.getClassName() + "\n" + "姓名:" + stu.getName()
							+ "\n" + "性别:" + stu.getSex() + "\n" + "平均分:" + stu.getAvgScore() + "\n";
				}
				JOptionPane.showMessageDialog(this, msg, "查询结果", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (e.getSource() == jbAdd) {
			new AddStudentInfoFrame(this);
		}
		if (e.getSource() == jbDel) {
			int selectedRow = jtStudentsInfo.getSelectedRow();
			if (selectedRow < 0 || selectedRow > PAGE_SIZE - 1) {
				JOptionPane.showMessageDialog(this, "请选择你要删的数据:", "提示", JOptionPane.WARNING_MESSAGE);
			} else {
				int index = currentPage * PAGE_SIZE + selectedRow;
				StudentUtil.delete(index, lstStudents);
				int newPageCount = getPageCount();// 新的page
				if (pageCount != newPageCount) {
					/* 刷新页面 */
					loadPage(currentPage - 1);
				} else {
					loadPage(currentPage);
				}
			}
		}
		if (e.getSource() == jbUpdate) {
			new UpdateStudentInfo();
		}
	}

}
