import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Counter {

    final private static String url = "https://www.random.org/integers/?num=10000&min=0&max=1&col=1&base=10&format=html&rnd=new";

    public static void main(String[] args) throws IOException {

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 100; i++) {
            Document document = Jsoup.connect(url).get();
            Elements data = document.getElementsByAttributeValue("class", "data");
            String numb = data.text().replaceAll("\\p{Cntrl}", "");
            buffer.append(numb);
        }

        Calc calc = new Calc();

        System.out.println("Обработанно " + buffer.length() + " симфолов из них повторяются : ");

        calc.calculete(buffer, 1, 1);
        System.out.println();
        calc.calculete(buffer,2,2);
        System.out.println();
        calc.calculete(buffer,3,3);

    }
}

class Calc {

    public void calculete(StringBuffer buffer, int numbersSize, int interval) {
        List<String> tripleNumbers = new ArrayList<>();
        int start = 0;
        int end = numbersSize;

        if (numbersSize % 2 == 0 || numbersSize == 1) {
            for (int i = 0; i < buffer.length(); i += interval) {
                tripleNumbers.add(buffer.substring(start, end));
                start = start + interval;
                end = end + interval;
            }
        } else {
            for (int i = 0; i < buffer.length() - 1; i += interval) {
                tripleNumbers.add(buffer.substring(start, end));
                start = start + interval;
                end = end + interval;
            }
        }

        Set<String> uniqueTripleNumbers = new HashSet<String>(tripleNumbers);
        for (String word : uniqueTripleNumbers) {
            System.out.println(word + ": " + Collections.frequency(tripleNumbers, word) + " " + ((Collections.frequency(tripleNumbers, word) * 100 / tripleNumbers.size())) + " %");
        }
    }


}



