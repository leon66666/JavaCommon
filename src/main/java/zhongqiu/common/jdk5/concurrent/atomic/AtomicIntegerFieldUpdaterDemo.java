package zhongqiu.common.jdk5.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author wangzhongqiu
 * @date 2018/2/11.
 */
public class AtomicIntegerFieldUpdaterDemo {
    public static void main(String[] args) {
        Person person = new Person("zhangsan", 11);
        AtomicIntegerFieldUpdater<Person> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Person.class, "age");
        atomicIntegerFieldUpdater.addAndGet(person, 12);
    }

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "[name: " + this.name + ", age: " + this.age + "]";
        }
    }
}
