package zhongqiu.common.base.algorithm;

/**
 * Created by wangzhongqiu on 2017/8/16.
 */
/**
  * 好像Java答案不多，我来献丑吧。
  * 正如大家之前所说，这个题主要是考细节。所以我只说说代码的细节：
  *   1. 读到文件路径，先用substring截取文件名。
  *   2. 用Entry对象保存单个记录和出现次数。
  *   3. 用Map来区分是否为新纪录,这要求Entry重新equals和hashCode。
  *   4. 真正使用的是LinkedHashMap，使得遍历key时可以按照插入顺序遍历，满足了题目要求。
  *   5. Arrays.sort对引用类型采用插入排序+归并排序，保证了排序稳定性，满足了题目要求。
  *   6. Entry实现了Comparable接口，其实更好是写一个Comparator，因为是刷题无所谓了。
  *   7. 对文件名长度的判定在Entry的toString中，这样写不好，因为是刷题无所谓了。
  **/
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class ErrorRecords {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        HashMap<Entry, Entry> records = new LinkedHashMap<Entry, Entry>();
        while (scan.hasNext()) {
            String name = scan.next();
            int lines = scan.nextInt();
            int index = name.lastIndexOf('\\');
            // 截取文件名
            if (index != -1)
                name = name.substring(index + 1);
            // 生成Entry
            Entry entry = new Entry(name, lines);
            if (records.containsKey(entry)) {
                // 合并
                Entry old = records.get(entry);
                old.count++;
            } else {
                // 新的
                records.put(entry, entry);
            }
        }
        scan.close(); // 获得结果
        Entry[] result = new Entry[records.size()];
        result = records.keySet().toArray(result);
        Arrays.sort(result);
        // 输出结果
        int size = Math.min(8, result.length);
        for (int i = 0; i < size; i++) {
            System.out.println(result[i]);
        }
    }

    private static class Entry implements Comparable<Entry> {
        public String name;
        public int lines;
        public int count;

        public Entry(String n, int l) {
            this.name = n;
            this.lines = l;
            this.count = 1;
        }

        @Override
        public int compareTo(Entry e) {
            if (this.count < e.count)
                return 1;
            else if (this.count == e.count)
                return 0;
            else
                return -1;
        }

        @Override
        public int hashCode() {
            return name.hashCode() + lines;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this)
                return true;
            if (obj instanceof Entry) {
                Entry e = (Entry) obj;
                if (e.name.equals(this.name) && e.lines == this.lines)
                    return true;
            }
            return false;
        }

        @Override
        public String toString() {
            if (name.length() <= 16)
                return name + " " + lines + " " + count;
            else
                return name.substring(name.length() - 16, name.length()) + " " + lines + " " + count;
        }
    }
}
