package Helpers;

public class Print {

    //For testing
    public void print(String text) {
        System.out.println(text);
    }

    public void printLbL(String text) {
        for (int i = 0; i < text.length(); i++) {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < 50) {
                System.out.print(text.charAt(i));
            }
        }
    }
}
