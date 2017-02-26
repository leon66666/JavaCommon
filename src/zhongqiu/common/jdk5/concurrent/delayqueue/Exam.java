package zhongqiu.common.jdk5.concurrent.delayqueue;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
//DelayQueue：DelayQueue 对元素进行持有直到一个特定的延迟到期。注入其中的元素必须实现 java.util.concurrent.Delayed 接口。
/*
 模拟一个考试的日子，考试时间为120分钟，30分钟后才可交卷，当时间到了，或学生都交完卷了考试结束。
这个场景中几个点需要注意：
考试时间为120分钟，30分钟后才可交卷，初始化考生完成试卷时间最小应为30分钟
对于能够在120分钟内交卷的考生，如何实现这些考生交卷
对于120分钟内没有完成考试的考生，在120分钟考试时间到后需要让他们强制交卷
在所有的考生都交完卷后，需要将控制线程关闭
*/
public class Exam {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		int studentNumber = 20;
		DelayQueue<Student> students = new DelayQueue<Student>();
		Random random = new Random();
		for (int i = 0; i < studentNumber; i++) {
			students.put(new Student("student" + (i + 1), 30 + random.nextInt(120)));
		}
		students.put(new Student("student",120));
		Thread teacherThread = new Thread(new Teacher(students));
		teacherThread.start();
	}
}

class Student implements Runnable, Delayed {

	private String name;
	public long workTime;
	private long submitTime;
	private boolean isForce = false;

	public Student() {
	}

	public Student(String name, long workTime) {
		this.name = name;
		this.workTime = workTime;
		this.submitTime = TimeUnit.NANOSECONDS.convert(workTime, TimeUnit.NANOSECONDS) + System.nanoTime();// 纳秒级别
	}

	@Override
	public int compareTo(Delayed o) {
		// TODO Auto-generated method stub
		if (o == null || !(o instanceof Student))
			return 1;
		if (o == this)
			return 0;
		Student s = (Student) o;
		if (this.workTime > s.workTime) {
			return 1;
		} else if (this.workTime == s.workTime) {
			return 0;
		} else {
			return -1;
		}
	}

	@Override
	public long getDelay(TimeUnit unit) {
		// TODO Auto-generated method stub
		return unit.convert(submitTime - System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (isForce) {
			System.out.println(name + " 交卷，实际用时 120分钟");
		} else {
			System.out.println(name + " 交卷," + "实际用时 " + workTime + " 分钟");
		}
	}

	public boolean isForce() {
		return isForce;
	}

	public void setForce(boolean isForce) {
		this.isForce = isForce;
	}

}

class Teacher implements Runnable {
	private int counter = 20;
	private DelayQueue<Student> students;

	public Teacher(DelayQueue<Student> students) {
		this.students = students;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println(" test start");
			while (counter > 0) {
				Student student = students.poll();
				if (student.workTime<120) {
					student.run();
					if (counter > 0) {
						counter--;
					}
				} else {
					System.out.println(" 考试时间到，全部交卷！");
					Student tmpStudent;
					for (Iterator<Student> iterator2 = students.iterator(); iterator2.hasNext();) {
						tmpStudent = iterator2.next();
						tmpStudent.setForce(true);
						tmpStudent.run();
						if (counter > 0) {
							counter--;
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
