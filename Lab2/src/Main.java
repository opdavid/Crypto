import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        int n;
        System.out.println("Give n:");
        Scanner sc = new Scanner(System.in);
        while(true) {
            try {
                n = sc.nextInt();
                System.out.print("Following are the prime numbers ");
                System.out.println("smaller than or equal to " + n);
                SieveOfEratosthenes g = new SieveOfEratosthenes();
                g.sieveOfEratosthenes(n);
            } catch (Exception e) {
                System.out.println("Invalid number");
            }
        }
    }
}
