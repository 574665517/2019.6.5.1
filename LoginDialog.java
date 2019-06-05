package jxau.software.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import jxau.software.javaproject.util.FileUtil;

/**
 * 登陆界面
 * @author dell
 */
public class LoginDialog extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5805653475140980452L;
	
	private JTextField jtfUserName;// 用户名
	private JPasswordField jpfPwd;// 密码
	private JCheckBox jcbRemember;// 复选框
	private JButton jbLogin, jbCancel;// 登陆，取消按钮

	private String mUserName, mPassword;// 从login.dat文件中读出的用户名和密码

	public LoginDialog() {
		this.setSize(400, 300);
		this.setTitle("登录界面");
		this.setLayout(new GridLayout(5, 1, 5, 5));
		JLabel jlTitle = new JLabel("学生信息管理系统");
		JLabel jlUserName = new JLabel("用户名：");
		JLabel jlPwd = new JLabel("密码：");
		jtfUserName = new JTextField(30);
		jpfPwd = new JPasswordField(30);
		jcbRemember = new JCheckBox("是否记住用户名和密码");
		jbLogin = new JButton("登录");
		jbCancel = new JButton("取消");
		Font titleFont = new Font("宋体", Font.BOLD, 24);
		Font contenFont = new Font("宋体", Font.BOLD, 16);
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
	 * 按钮的点击事件
	 */
	public void actionPerformed(ActionEvent e) {
		/* 登陆的业务逻辑 */
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
				// new ShowInfoFrame(); //shift我的东西
				this.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, "用户名或密码错误请重新输入！");
			}
			this.setVisible(false);
		} else {
			jtfUserName.setText("");
			jpfPwd.setText("");
		}

	}
}
