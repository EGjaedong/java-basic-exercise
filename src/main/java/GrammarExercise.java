import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrammarExercise {
    public static void main(String[] args) {
        String firstWordList = "";
        String secondWordList = "";

        Scanner scanner = new Scanner(System.in);
        firstWordList = scanner.nextLine();
        secondWordList = scanner.nextLine();

        System.out.println(firstWordList);
        System.out.println(secondWordList);

        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);

        System.out.println(result);
    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        firstWordList = firstWordList.toLowerCase();
        secondWordList = secondWordList.toLowerCase();

        List<String> firstList = Arrays.asList(firstWordList.split(","));
        List<String> secondList = Arrays.asList(secondWordList.split(","));

        long countOfNoAlphabet1 = firstList.stream()
                .filter(str -> !str.matches("^[a-zA-Z]+$"))
                .count();

        long countOfNoAlphabet2 = secondList.stream()
                .filter(str -> !str.matches("^[a-zA-Z]+$"))
                .count();

        if (countOfNoAlphabet1 > 0 || countOfNoAlphabet2 > 0)
            throw new RuntimeException();

        long emptyString1 = firstList.stream()
                .filter(str -> str.equals(""))
                .count();

        long emptyString2 = secondList.stream()
                .filter(str -> str.equals(""))
                .count();

        if (emptyString1 > 0 || emptyString2 > 0)
            throw new RuntimeException();

        List<String> res = firstList.stream()
                .filter(secondList::contains).distinct().sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.toList())
                .stream().map(String::toUpperCase).map(str->{
                    String r = "";
                    for (int i = 0; i < str.length() - 1; i++) {
                        r = r + str.charAt(i) + " ";
                    }
                    r += str.charAt(str.length()-1);
                    return r;
                }).distinct().collect(Collectors.toList());;

        System.out.println(res);
        return res;
    }
}
