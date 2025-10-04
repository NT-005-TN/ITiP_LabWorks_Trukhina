package Laba1;

public class Primes {

    public static boolean isPrime(int n){

        for(int d = 2; d <= Math.ceil(Math.sqrt(n)); d++){
            if(n % d == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args){
        for(int i = 2; i <= 100; i++){
            if(isPrime(i) == true){
                System.out.print(i + " ");
            }
        }
    }
}
