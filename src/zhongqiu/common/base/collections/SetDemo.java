package zhongqiu.common.base.collections;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

//set
//Set和List的区别
//1. Set 接口实例存储的是无序的，不重复的数据。List 接口实例存储的是有序的，可以重复的元素。
//2. Set检索效率低下，删除和插入效率高，插入和删除不会引起元素位置改变 <实现类有HashSet,TreeSet>。
//3. List和数组类似，可以动态增长，根据实际存储的数据的长度自动增长List的长度。
//   查找元素效率高，插入删除效率低，因为会引起其他元素位置改变 <实现类有ArrayList,LinkedList,Vector> 。
public class SetDemo {
	public static void main(String[] args) {

	}

	// HashSet类按照哈希算法来存取集合中的对象，存取速度比较快。HashSet类还有一个子类LinkedHashSet类，它不仅实现了哈希算法，而且实现了链表数据结构。
	HashSet<String> hSet = new HashSet<>();
	
    LinkedHashSet<String> linkedHashSet=new LinkedHashSet<>();
	// TreeSet类实现了SortedSet接口，具有排序功能。TreeSet支持两种排序方式：自然排序和客户化排序，在默认情况下TreeSet采用自然排序方式。
	TreeSet<String> tSet = new TreeSet<>();
}
