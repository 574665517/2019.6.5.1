//���Լ����
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
	
	private JLabel jsno; // ѧ��
	private JLabel jclass;// �༶
	private JLabel jname;// ����
	private JLabel jsex; // �Ա�
	private JRadioButton jman, jwoman;
	private JLabel javeragemark; // ƽ���ɼ�
	private JTextField psno, pclass, pname, paveragemark;
	private JButton jcheck, jcancel; // ȷ�� ��ȡ��

	public ShowInfoFrame() {
		this.setSize(500, 500);
		this.setTitle("��Ӻ��޸�");
		this.setLayout(new GridLayout(6, 1, 5, 5));
		jsno = new JLabel("ѧ��");
		jclass = new JLabel("�༶");
		jname = new JLabel("����");
		jsex = new JLabel("�Ա�");
		javeragemark = new JLabel("ƽ���ɼ�");
		psno = new JTextField(30);
		pclass = new JTextField(30);
		pname = new JTextField(30);
		paveragemark = new JTextField(30);
		jman = new JRadioButton("��", true);
		jwoman = new JRadioButton("Ů");
		JPanel jp = new JPanel(); // ��������ťװһ��
		jp.add(jman);
		jp.add(jwoman);
		jcheck = new JButton("ȷ��");
		jcheck.addActionListener(this); //��һ��������
		jcancel = new JButton("ȡ��");
		jcancel.addActionListener(this);
		JPanel jp1 = new JPanel(); // ��������Ҳװһ��
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
