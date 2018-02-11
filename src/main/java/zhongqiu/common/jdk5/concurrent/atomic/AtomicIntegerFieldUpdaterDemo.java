package zhongqiu.common.jdk5.concurrent.atomic;

/**
 * @author wangzhongqiu
 * @date 2018/2/11.
 */
public class AtomicIntegerFieldUpdaterDemo {
    public static void main(String[] args) {

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
