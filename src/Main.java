import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        // получить 2 строки на вход
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputString = reader.readLine();

        char[] inputStringArray = inputString.toCharArray();
        String string1, string2;
        int indexOfSpace = inputString.indexOf(' ');
        int inputStringLength = inputString.length();
        char[] stringArray1 = new char[indexOfSpace], stringArray2 = new char[inputStringLength - indexOfSpace - 1];
        int num = 0, num1 = 0;

        // Разбить на две строки
        for (char character: inputStringArray) {
            if (character == ' ') {
                break;
            }
            stringArray1[num] = character;
            num ++;

        }
        string1 = String.valueOf(stringArray1);
        for (int i=0; i < inputStringLength-num-1; i++) {
            stringArray2[i] = inputStringArray[num+i+1];
        }
        string2 = String.valueOf(stringArray2);
        // тестовый вывод на экран
        System.out.println(string1);
        System.out.println(string2);


        // Можно ли превратить первую строку во вторую?

        // если можно вывести 1, если нет то 0


    }




}
