package zhongqiu.common.jdk8;

//接口的默认方法
public class InterfaceDefaultMethod {
    interface Formula {
        double calculate(int a);

        default double sqrt(int a) {
            return Math.sqrt(a);
        }
    }

    public static class FormulaImpl implements Formula {

        @Override
        public double calculate(int a) {
            // TODO Auto-generated method stub
            return 0;
        }

    }

    public static void main(String[] args) {
        FormulaImpl formulaImpl = new FormulaImpl();
        System.out.println(formulaImpl.sqrt(36));
    }
}
