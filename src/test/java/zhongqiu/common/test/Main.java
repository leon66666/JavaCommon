package zhongqiu.common.test;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main main = new Main();
        int x = sc.nextInt();//x房租
        int f = sc.nextInt();//有f水果
        int d = sc.nextInt();//有d钱
        int p = sc.nextInt();//每个水果p钱
        main.setX(x);
        main.setF(f);
        main.setD(d);
        main.setP(p);
        int y = 0;
        while (main.getOneF()) {
            while (main.getOneX()) {
                y++;
            }
        }
        System.out.println(y);
    }

    public boolean getOneF() {
        if (getF() - 1 >= 0) {
            setF(getF() - 1);
            return true;
        } else {
            if (getD() - getP() >= 0) {
                setD(getD() - getP());
                return true;
            }
        }
        return false;
    }

    public boolean getOneX() {
        if (getD() - x >= 0) {
            setD(getD() - x);
            return true;
        }
        return false;
    }

    private int x;
    private int f;
    private int d;
    private int p;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }
}
