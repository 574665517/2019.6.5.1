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
	private JTable jtStudentsInfo;// ѧ����Ϣ���
	private JScrollPane pane;
	private JLabel jlPageInfo;
	private JButton jbFirstPage, jbPageUp, jbPageDown, jbLastPage; // ��ҳ ���һҳ �İ�ť
	private JButton jbAdd, jbDel, jbQuery, jbUpdate;// ��� ɾ�� ��ѯ ���� �İ�ť
	private JTextField jtfStuNumer;// Ҫ��ѯ��ѧ��ѧ�������
	private int currentPage, pageCount;// ��ǰ��ҳ�룬��ҳ��
	public ArrayList<Student> lstStudents;
	private Vector<String> columnNames; // ѧ����Ϣ�б��ͷ

	public StudentInfoManageFrame() throws Exception {
		inti();
		loadStudentInfo();
	}

	/* �������� */
	private void loadStudentInfo() throws Exception {
		columnNames = new Vector<String>();
		columnNames.add("���");
		columnNames.add("ѧ��");
		columnNames.add("�༶");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("ƽ����");
		jtStudentsInfo = new JTable(null, columnNames);
		pane.setViewportView(jtStudentsInfo);
		/* ��ȡ�ļ���ѧ������Ϣ����������ArrayList��ѧ���б��� */
		String[] stusInfo = FileUtil.read("student.dat").split("\n");
		lstStudents = new ArrayList<Student>();
		for (int i = 0; i < stusInfo.length; i++) {
			String[] stus = stusInfo[i].split("\t"); // �ָ��ļ��е����� ��\t��
			if (stusInfo.length < 5) {
				JOptionPane.showMessageDialog(this, "ѧ����Ϣ���ݸ�ʽ������˶�!", "��ʾ", JOptionPane.WARNING_MESSAGE);
				continue;
			}
			Student stu = new Student(stus[0], stus[1], stus[2], stus[3], Float.parseFloat(stus[4]));
			lstStudents.add(stu);
		}
		loadPage(0);
		setTabelFormat();
	}

	private void setTabelFormat() {
		/* ������������ */
		jtStudentsInfo.setFont(FontUtil.contenFont);
		/* ���ñ�ͷ���� */
		jtStudentsInfo.getTableHeader().setFont(FontUtil.tableHeaderFont);
		/* �����п� */
		jtStudentsInfo.getColumn("���").setMaxWidth(50);
		jtStudentsInfo.getColumn("�Ա�").setMaxWidth(50);
		/* �����и� */
		jtStudentsInfo.setRowHeight(30);
		/* �������ݾ�����ʾ */
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		jtStudentsInfo.setDefaultRenderer(Object.class, tcr);
		/* ����ѡ���е�ǰ��ɫ�ͱ���ɫ */
		jtStudentsInfo.setSelectionBackground(Color.YELLOW);
		jtStudentsInfo.setSelectionForeground(Color.RED);
	}

	/* ����ҳ�����ĳһҳ��ѧ����Ϣ */
	public void loadPage(int pageIndex) {
		currentPage = pageIndex;
		pageCount = getPageCount();
		setButtonEnable();
		if (pageIndex >= pageCount) {
			JOptionPane.showMessageDialog(this, "ҳ��" + pageIndex + "������", "��ʾ", JOptionPane.WARNING_MESSAGE);
		} else {
			jlPageInfo.setText("��" + pageCount + "ҳ,��ǰ��" + (currentPage + 1) + "ҳ");
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

	// ��ʼ������
	public void inti() {
		this.setTitle("ѧ���������");
		this.setLayout(new BorderLayout());
		JLabel jlTitle = new JLabel("ѧ����Ϣ�б�");
		jbFirstPage = new JButton("��ҳ");
		jbPageUp = new JButton("��һҳ");
		jbPageDown = new JButton("��һҳ");
		jbLastPage = new JButton("ĩҳ");
		jlPageInfo = new JLabel();
		jbAdd = new JButton("���");
		jbDel = new JButton("ɾ��");
		jbQuery = new JButton("��ѯ");
		jbUpdate = new JButton("�޸�");
		jtfStuNumer = new JTextField(20);
		pane = new JScrollPane();// ѧ���б���Ϣ������
		this.add(pane, BorderLayout.CENTER);
		JPanel[] panels = new JPanel[6];
		for (int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel();
		}
		/* panels[0] --- ѧ����Ϣ�б���� */
		panels[0].setLayout(new FlowLayout());
		panels[0].add(jlTitle);
		this.add(panels[0], BorderLayout.NORTH);
		/* panels[1] --- �������ĵײ����� */
		panels[1].setLayout(new GridLayout(4, 1));
		this.add(panels[1], BorderLayout.SOUTH);
		panels[1].add(panels[2]);
		panels[1].add(panels[3]);
		panels[1].add(panels[4]);
		panels[1].add(panels[5]);
		/* panels[2] --- ��ҳ����Ϣ */
		panels[2].setLayout(new FlowLayout());
		panels[2].add(jlPageInfo);
		/* panels[3] --- ��ҳ�İ�ť */
		panels[3].setLayout(new FlowLayout());
		panels[3].add(jbFirstPage);
		panels[3].add(jbPageUp);
		panels[3].add(jbPageDown);
		panels[3].add(jbLastPage);
		/* panels[4] --- ��ɾ���ĵİ�ť */
		panels[4].setLayout(new FlowLayout());
		panels[4].add(jbAdd);
		panels[4].add(jbDel);
		panels[4].add(jbUpdate);
		/* panels[5] --- ��ѯ��ť����ѯ��ѧ������� */
		panels[5].setLayout(new FlowLayout());
		panels[5].add(jbQuery);
		panels[5].add(jtfStuNumer);
		/* ���ÿؼ����� */
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
				JOptionPane.showMessageDialog(this, "������Ҫ��ѯ��ѧ��ѧ��:", "��ʾ", JOptionPane.WARNING_MESSAGE);
			} else {
				Student stu = StudentUtil.query(number, lstStudents);
				String msg = "";
				if (stu == null) {
					msg = "��Ҫ��ѯ��ѧ��Ϊ" + number + "��ѧ�Ų�����";
				} else {/* �������ʾ */
					msg = "ѧ��:" + stu.getNumber() + "\n" + "�༶:" + stu.getClassName() + "\n" + "����:" + stu.getName()
							+ "\n" + "�Ա�:" + stu.getSex() + "\n" + "ƽ����:" + stu.getAvgScore() + "\n";
				}
				JOptionPane.showMessageDialog(this, msg, "��ѯ���", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (e.getSource() == jbAdd) {
			new AddStudentInfoFrame(this);
		}
		if (e.getSource() == jbDel) {
			int selectedRow = jtStudentsInfo.getSelectedRow();
			if (selectedRow < 0 || selectedRow > PAGE_SIZE - 1) {
				JOptionPane.showMessageDialog(this, "��ѡ����Ҫɾ������:", "��ʾ", JOptionPane.WARNING_MESSAGE);
			} else {
				int index = currentPage * PAGE_SIZE + selectedRow;
				StudentUtil.delete(index, lstStudents);
				int newPageCount = getPageCount();// �µ�page
				if (pageCount != newPageCount) {
					/* ˢ��ҳ�� */
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
