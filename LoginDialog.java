package jxau.software.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import jxau.software.javaproject.util.FileUtil;

/**
 * ��½����
 * @author dell
 */
public class LoginDialog extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5805653475140980452L;
	
	private JTextField jtfUserName;// �û���
	private JPasswordField jpfPwd;// ����
	private JCheckBox jcbRemember;// ��ѡ��
	private JButton jbLogin, jbCancel;// ��½��ȡ����ť

	private String mUserName, mPassword;// ��login.dat�ļ��ж������û���������

	public LoginDialog() {
		this.setSize(400, 300);
		this.setTitle("��¼����");
		this.setLayout(new GridLayout(5, 1, 5, 5));
		JLabel jlTitle = new JLabel("ѧ����Ϣ����ϵͳ");
		JLabel jlUserName = new JLabel("�û�����");
		JLabel jlPwd = new JLabel("���룺");
		jtfUserName = new JTextField(30);
		jpfPwd = new JPasswordField(30);
		jcbRemember = new JCheckBox("�Ƿ��ס�û���������");
		jbLogin = new JButton("��¼");
		jbCancel = new JButton("ȡ��");
		Font titleFont = new Font("����", Font.BOLD, 24);
		Font contenFont = new Font("����", Font.BOLD, 16);
		jlTitle.setFont(titleFont);
		jlUserName.setFont(contenFont);
		jlPwd.setFont(contenFont);
		jtfUserName.setFont(contenFont);
		jpfPwd.setFont(contenFont);
		jbLogin.setFont(contenFont);
		jbCancel.setFont(contenFont);
		JPanel panel1 = new JPanel(new FlowLayout());
		JPanel panel2 = new JPanel(new FlowLayout());
		JPanel panel3 = new JPanel(new FlowLayout());
		JPanel panel4 = new JPanel(new FlowLayout());
		JPanel panel5 = new JPanel(new FlowLayout());
		panel1.add(jlTitle);
		panel2.add(jlUserName);
		panel2.add(jtfUserName);
		panel3.add(jlPwd);
		panel3.add(jpfPwd);
		panel4.add(jcbRemember);
		panel5.add(jbLogin);
		panel5.add(jbCancel);
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.add(panel5);
		jbLogin.addActionListener(this);
		jbCancel.addActionListener(this);
		String[] strs = null;
		try {
			strs = FileUtil.read("login.dat").split("\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		mUserName = strs[0];
		mPassword = strs[1];
		jcbRemember.setSelected(Boolean.parseBoolean(strs[2]));
		if (jcbRemember.isSelected()) {
			jtfUserName.setText(mUserName);
			jpfPwd.setText(mPassword);
		}
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);

	}

	public static void main(String[] args) {
		new LoginDialog();
	}

	/**
	 * ��ť�ĵ���¼�
	 */
	public void actionPerformed(ActionEvent e) {
		/* ��½��ҵ���߼� */
		if (e.getSource() == jbLogin) {
			String userName = jtfUserName.getText();
			String password = new String(jpfPwd.getPassword());
			if (mUserName.equals(userName) && mPassword.equals(mPassword)) {
				String content = userName + "\n" + password + "\n" + Boolean.toString(jcbRemember.isSelected());
				try {
					FileUtil.write("Login.dat", content);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				JFrame frm = null;
				try {
					frm = new StudentInfoManageFrame();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				frm.setExtendedState(JFrame.MAXIMIZED_BOTH);
				// new ShowInfoFrame(); //shift�ҵĶ���
				this.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, "�û���������������������룡");
			}
			this.setVisible(false);
		} else {
			jtfUserName.setText("");
			jpfPwd.setText("");
		}

	}
}
