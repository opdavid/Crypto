package sample;

import sample.domain.Matrix;

import java.util.Map;

public class Utils {

    public static Matrix validateMatrix(Matrix matrix) throws Exception{
        int detA = (matrix.getValOnPos(1,1)*matrix.getValOnPos(2,2) - matrix.getValOnPos(1,2) * matrix.getValOnPos(2,1) +27 )%27;
        System.out.println(detA);
        if (detA == 0)
            throw new Exception("determinant = 0");
        Matrix transpusa = matrix.transpusa();
        Matrix inversa = new Matrix();
        Integer v = getV(27,detA);
        System.out.println(v);
        inversa.setValOnPos(1,1,(v*transpusa.getValOnPos(2,2)) % 27);
        inversa.setValOnPos(1,2,(27-(v* transpusa.getValOnPos(2,1)) % 27));
        inversa.setValOnPos(2,1,(27-(v* transpusa.getValOnPos(1,2)) % 27));
        inversa.setValOnPos(2,2,(v*transpusa.getValOnPos(1,1)) %27);

        return inversa;
    }

    private static Integer getV(Integer a, Integer b) {
        Integer d, q, r, u, v, u2 = 1, v1 = 1, u1 = 0, v2 = 0;

        while (b > 0) {
            q = a / b;
            r = a - q * b;
            u = u2 - q * u1;
            v = v2 - q * v1;

            a = b;
            b = r;
            u2 = u1;
            u1 = u;
            v2 = v1;
            v1 = v;
        }

        v=v2%27;

        if(v<0){
            v=27+v;
        }
        return v;
    }

    public static boolean validateInput(String input) throws Exception{
        char c;
        for(int i = 0; i<input.length();i++){
            c = input.charAt(i);
            if((c > 'z' || c < 'a') && c!=' '){
                throw new Exception("Invalid input");
            }
        }
        return true;
    }

    public static Object getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }
}
