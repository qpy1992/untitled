import java.math.BigDecimal;

public class Test2 {
    public static void main(String[] args) {
        BigDecimal bb = new BigDecimal("4296");
        bb = bb.divide(BigDecimal.valueOf(100),2,0);
        System.out.println(bb);
    }
}
