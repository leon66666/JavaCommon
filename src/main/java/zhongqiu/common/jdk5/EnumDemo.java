package zhongqiu.common.jdk5;

import java.util.EnumSet;

//枚举
/*
1、普通枚举，通过name获取枚举，通过默认序号ordinal获取枚举
2、自定义枚举
3、枚举的switch用法
4、枚举实现接口
5、枚举集合的使用。java.util.EnumSet和java.util.EnumMap是两个枚举集合。
  EnumSet保证集合中的元素不重复；EnumMap中的key是enum类型，而value则可以是任意类型。*/
public class EnumDemo {
    public static void main(String[] args) {
        EnumSwitch();
    }

    //普通枚举
    public enum Color {
        RED, GREEN, BLANK, YELLOW;

        //通过name获取枚举
        public static Color getByName(String name) {
            Color color = Color.RED;
            switch (name) {
                case "RED":
                    color = Color.RED;
                    break;
                case "GREEN":
                    color = Color.GREEN;
                    break;
                case "BLANK":
                    color = Color.BLANK;
                    break;
                case "YELLOW":
                    color = Color.YELLOW;
                    break;
                default:
                    color = null;
                    break;
            }
            return color;
        }

        //通过默认序号ordinal获取枚举
        public static Color valueOf(int ordinal) {
            if (ordinal >= 0 && ordinal < values().length) {
                return values()[ordinal];
            } else {
                throw new IndexOutOfBoundsException("Invalid ordinal");
            }
        }
    }

    // 自定义枚举
    public enum ColorExt {
        RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLOW("黄色", 4);
        // 成员变量
        private String name;
        private int index;

        // 构造方法
        private ColorExt(String name, int index) {
            this.name = name;
            this.index = index;
        }

        // 普通方法
        public static String getName(int index) {
            for (ColorExt c : ColorExt.values()) {
                if (c.getIndex() == index) {
                    return c.name;
                }
            }
            return null;
        }

        // 覆盖方法
        @Override
        public String toString() {
            return this.index + "_" + this.name;
        }

        // get set 方法
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

    // 枚举的switch用法
    public static void EnumSwitch() {
        Color color = Color.RED;
        System.out.println(color);
        switch (color) {
            case RED:
                color = Color.GREEN;
                System.out.println(color);
                break;
            case YELLOW:
                color = Color.RED;
                System.out.println(color);
                break;
            case GREEN:
                color = Color.YELLOW;
                System.out.println(color);
                break;
        }
    }

    // 枚举实现接口
    public interface Behaviour {
        void print();

        String getInfo();
    }

    public enum ColorImpl implements Behaviour {
        RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLOW("黄色", 4);
        // 成员变量
        private String name;
        private int index;

        // 构造方法
        private ColorImpl(String name, int index) {
            this.name = name;
            this.index = index;
        }

        // 接口方法
        @Override
        public String getInfo() {
            return this.name;
        }

        // 接口方法
        @Override
        public void print() {
            System.out.println(this.index + ":" + this.name);
        }
    }

    //枚举集合的使用，java.util.EnumSet和java.util.EnumMap是两个枚举集合。
    EnumSet<Color> repayApplyStatus = EnumSet.of(Color.BLANK,Color.GREEN,Color.RED);
}
