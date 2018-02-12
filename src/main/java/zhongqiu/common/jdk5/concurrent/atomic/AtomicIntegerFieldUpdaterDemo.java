package zhongqiu.common.jdk5.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author wangzhongqiu
 * @date 2018/2/11.
 */
public class AtomicIntegerFieldUpdaterDemo {
    public static void main(String[] args) {
        Person person = new Person("zhangsan", 11, 170);
        person.setHobby(new Hobby("打球", "足球，篮球"));
        AtomicIntegerFieldUpdater<Person> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Person.class, "age");
        atomicIntegerFieldUpdater.addAndGet(person, 12);

        AtomicLongFieldUpdater<Person> atomicLongFieldUpdater = AtomicLongFieldUpdater.newUpdater(Person.class, "height");
        atomicLongFieldUpdater.addAndGet(person, 180);

        AtomicReferenceFieldUpdater<Person, Hobby> atomicReferenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(Person.class, Hobby.class, "hobby");
        atomicReferenceFieldUpdater.getAndSet(person, new Hobby("打球", "排球，羽毛球"));
    }

    static class Person {
        private String name;
        private int age;
        private long height;

        private Hobby hobby;

        public Person(String name, int age, long height) {
            this.name = name;
            this.age = age;
            this.height = height;
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

        public long getHeight() {
            return height;
        }

        public void setHeight(long height) {
            this.height = height;
        }

        public Hobby getHobby() {
            return hobby;
        }

        public void setHobby(Hobby hobby) {
            this.hobby = hobby;
        }

        @Override
        public String toString() {
            return "[name: " + this.name + ", age: " + this.age + "]";
        }
    }

    static class Hobby {
        private String hobbyName;

        private String info;

        public Hobby(String hobbyName, String info) {
            this.hobbyName = hobbyName;
            this.info = info;
        }
    }
}
