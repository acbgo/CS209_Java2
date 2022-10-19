package lab3;

import java.util.ArrayList;
import java.util.Scanner;

public class practice {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            int funcNo = getFuncNo();
            boolean flag = ifQuit(funcNo);
            if (flag)
                break;
            int[] elements = getElements(getInputSize());
            ArrayList<Integer> result = filter(elements,funcNo);
            System.out.println("Filter results:");
            System.out.println(result);
        }
    }
    public static int getFuncNo(){
        System.out.println("Please input the function No:");
        System.out.println("1 - Get even numbers");
        System.out.println("2 - Get odd numbers");
        System.out.println("3 - Get prime numbers");
        System.out.println("4 - Get prime numbers that are bigger than 5");
        System.out.println("0 - Quit");
        int funcNo = sc.nextInt();
        return funcNo;
    }
    public static boolean ifQuit(int funcNo){
        if (funcNo == 0)
            return true;
        else
            return false;
    }
    public static int getInputSize(){
        System.out.println("Input size of the list:");
        int InputSize = sc.nextInt();
        return InputSize;
    }
    public static int[] getElements(int InputSize){
        System.out.println("Input elements of the list:");
        int[] elements = new int[InputSize];
        for (int i = 0; i < InputSize; i++) {
            elements[i] = sc.nextInt();
        }
        return elements;
    }
    public static ArrayList<Integer> filter(int[] elements, int funcNo){
        ArrayList<Integer> result = new ArrayList<>();
        if (funcNo == 1){
            for(int i : elements){
                if (i%2==0)
                    result.add(i);
            }
        }
        else if (funcNo == 2){
            for (int i : elements){
                if (i%2 == 1)
                    result.add(i);
            }
        }
        else if (funcNo == 3 || funcNo == 4){
            ArrayList<Integer> temp;
            for (int i : elements)
                if (isPrime(i))
                    result.add(i);
            if (funcNo == 3)
                return result;
            else{
                temp = new ArrayList<>(result);
                result.clear();
                for (int i : temp)
                    if (i > 5)
                        result.add(i);
            }
        }
        return result;
    }
    public static boolean isPrime(int num){
        double sqrt = Math.sqrt(num);
        if (num < 2)
            return false;
        else if (num == 2 || num == 3)
            return true;
        else if (num % 2 == 0)
            return false;
        else {
            for (int i = 3; i <= sqrt; i+=2) {
                if (num % i == 0)
                    return false;
            }
        }
        return true;
    }
}
