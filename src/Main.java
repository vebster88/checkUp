import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // получить 2 строки на вход
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputString = reader.readLine();

        char[] inputStringArray = inputString.toCharArray();
        String string1, string2; // два используемых слова
        int indexOfSpace = inputString.indexOf(' '); // номер индекса знака "пробел" в полученной строке
        int inputStringLength = inputString.length(); // длина входной строки
        char[] stringArray1 = new char[indexOfSpace], stringArray2 = new char[inputStringLength - indexOfSpace - 1]; // массив символов из 1 и 2 слова
        int num = 0;
        int longestString; // длиннейшая строка
        Set usedLetter = new HashSet(); // множество букв, которые уже были использованы во втором слове
        Set usedLetter1 = new HashSet(); // множество букв, которые уже были использованы в 1 слове
        Map<Character, Character> associatedChars = new HashMap<>(); // карта связанных букв
        Character currentLetter = null;
        Character additional = 'я';

        // Разбить на две строки
        for (char character: inputStringArray) {
            if (character == ' ') {
                break;
            }
            stringArray1[num] = character;
            num ++;
        }
        for (int i=0; i < inputStringLength-num-1; i++) {
            stringArray2[i] = inputStringArray[num+i+1];
        }

        // Можно ли превратить первую строку во вторую?

        if (stringArray1.length > stringArray2.length) {  // проверяем длину слова, если 1 слово больше 2
            stringArray2 = wordChanging(stringArray1, stringArray2);
        } else if (stringArray1.length < stringArray2.length) {  // повторяем предыдущий алгоритм если 2 слово больше первого
            stringArray1 = wordChanging(stringArray2, stringArray1);
        }
        longestString = stringArray2.length;    // определяем длину первого слова

        for (int i = 0; i < longestString; i++) {
            if ((stringArray1[i] != stringArray2[i]) && (isCirillic(stringArray2[i])) && // проверка является ли кириллицей и одинаковы ли символы
                    !(isUsedLetter(stringArray2[i], usedLetter)) &&  // проверка использовался ли ранее символ во 2 слове
                    !(isUsedLetter(stringArray1[i], usedLetter1))) { // проверка использовался ли ранее символ в 1 слове
                usedLetter1.add(stringArray1[i]);
                associatedChars.put(stringArray1[i], stringArray2[i]);
                usedLetter.add(stringArray2[i]);
            }
        }

        for (int i=0; i < longestString; i++) {
            if (isUsedLetter(stringArray2[i], usedLetter) &&  // если содержит использованную букву
                    !associatedChars.containsValue(stringArray1[i]) &&  // проверяем есть ли одинаковые буквы в двух словах
                    associatedChars.containsKey(stringArray1[i])) { // проверяем есть ли данная буква в 1 слове
                currentLetter = associatedChars.get(stringArray1[i]);
                stringArray1[i] = currentLetter;
            } else if (associatedChars.containsValue(stringArray1[i]) &&  // если содержит одинаковые буквы в двух словах
                    (isUsedLetter(stringArray1[i], usedLetter1))) {   // использовалась ли данная буква в первом слове
                currentLetter = associatedChars.get(stringArray1[i]);
                for (int j=i; j < longestString; j++) {
                    if (stringArray1[j] == currentLetter) {  // смотрим на каких позициях слова1 есть одинаковые буквы с текущей позицией слова 2
                                stringArray1[j] = additional; // временно заменяем дублирующую букву на новую
                            if (!usedLetter.contains(additional)) {   // проверка использовалась ли ранее
                            associatedChars.put(stringArray1[j], stringArray2[j]); // добавляем новую комбинацию в мапу
                            usedLetter.add(additional);
                        }
                    }
                }
                if (stringArray1[i] != currentLetter) {  // чтобы избежать лишнего присвоения проверка на дубль
                    stringArray1[i] = currentLetter; }
            }
        }

        // если можно вывести 1, если нет то 0
        string1 = String.valueOf(stringArray1);
        string2 = String.valueOf(stringArray2);
        if (string1.equals(string2)) {
            System.out.println(1);  // можно преобразовать
        } else System.out.println(0); // нельзя преобразовать
    }

    static boolean isCirillic(char character) {   // проверка является ли кириллицей в нижнем регистре
        if (((character >= 'а')&&(character <= 'я'))||(character == 'ё')) {
            return true;
        }
        return false;
    }

    static boolean isUsedLetter(char character, Set<Character> usedLetters) {   // проверка является ли кириллицей
        if (usedLetters.contains(character)) {
            return true;
        }
        return false;
    }

    static char[] wordChanging(char[] stringArray1, char[] stringArray2) {
        int longestString = stringArray1.length;   // находим более длинное слово
        char[] addString = stringArray2;      // временно сохраняем слово в доп. массив
        stringArray2 = new char[longestString];  //увеличиваем массив до размера большего слова
        for (int i = 0; i < addString.length; i++ ) {     // копируем данные из временного массива в новый
            stringArray2[i] = addString[i];
        }
        for (int i = addString.length; i < longestString; i++) {   // заполняем пустые позиции массива нулями
            stringArray2[i] = 0;
        }
        return stringArray2;
    }

}
