package zhongqiu.common.test;

/**
 * @author wangzhongqiu
 * @date 2018/1/4.
 */
public class CodeBlock03 {
    public static void main(String[] args) {
        final int i = 3;
        Code code = new Code();
        code.setI(1);
        String str = "sss";
        switch (i)

        {

            case '1':

                System.out.println(1);

                break;

            case 2:

                System.out.println(2);

                break;

            default:

                System.out.println("default");

                break;

        }
    }
}

class Code {
    public int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}

