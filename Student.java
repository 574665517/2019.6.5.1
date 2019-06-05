package jxau.software.javaproject.domain;

public class Student {
	private String number;
	private String className;
	private String name;
	private String sex;
	private float avgScore;

	/* ���췽�� */
	public Student(String number, String className, String name, String sex, float aygScore) {
		this.number = number;
		this.className = className;
		this.name = name;
		this.sex = sex;
		this.avgScore = aygScore;
	}

	/* ѧ�����Ե�get��set���� */
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public float getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(float avgScore) {
		this.avgScore = avgScore;
	}

	public String toString() {
		return number + "\t" + className + "\t" + name + "\t" + sex + "\t" + avgScore + "\n";
	}

}
