package zhongqiu.common.jdk5.util;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueDemo {
	private String name;
	private int population;

	public PriorityQueueDemo(String name, int population) {
		this.name = name;
		this.population = population;
	}

	public String getName() {
		return this.name;
	}

	public int getPopulation() {
		return this.population;
	}

	public String toString() {
		return getName() + " - " + getPopulation();
	}

	public static void main(String args[]) {
		// 和 Comparable 的对比
		Comparator<PriorityQueueDemo> OrderIsdn = new Comparator<PriorityQueueDemo>() {
			public int compare(PriorityQueueDemo o1, PriorityQueueDemo o2) {
				// TODO Auto-generated method stub
				int numbera = o1.getPopulation();
				int numberb = o2.getPopulation();
				if (numberb > numbera) {
					return 1;
				} else if (numberb < numbera) {
					return -1;
				} else {
					return 0;
				}

			}

		};
		Queue<PriorityQueueDemo> priorityQueue = new PriorityQueue<PriorityQueueDemo>(11, OrderIsdn);

		PriorityQueueDemo t1 = new PriorityQueueDemo("t1", 1);
		PriorityQueueDemo t3 = new PriorityQueueDemo("t3", 3);
		PriorityQueueDemo t2 = new PriorityQueueDemo("t2", 2);
		PriorityQueueDemo t4 = new PriorityQueueDemo("t4", 0);
		priorityQueue.add(t1);
		priorityQueue.add(t3);
		priorityQueue.add(t2);
		priorityQueue.add(t4);
		System.out.println(priorityQueue.poll().toString());
	}
}
