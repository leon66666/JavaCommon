package zhongqiu.common.base;

import java.util.Enumeration;

public class EnumerationDemo implements Enumeration{
    int count; // 计数器
    int length; //存储的数组的长度
    Object[] dataArray; // 存储数据数组的引用
    //构造器
    EnumerationDemo(int count,int length,Object[] dataArray){
        this.count = count;
        this.length= length;
        this.dataArray=dataArray;
    }
    public boolean hasMoreElements() {
        return (count< length);
    }
    public Object nextElement() {
        return dataArray[count++];
    }
}
