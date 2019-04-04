import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class Counter {

    final private static String url = "https://www.random.org/integers/?num=100&min=0&max=1&col=1&base=10&format=html&rnd=new";

    public static void main(String[] args) throws IOException {

        StringBuffer buffer = new StringBuffer();
//        StringBuffer buffer = new StringBuffer("1234567897897891999999999999999999314157177171");

        for (int i = 0; i < 100; i++) {
            Document document = Jsoup.connect(url).get();
            Elements data = document.getElementsByAttributeValue("class", "data");
            String numb = data.text().replaceAll("\\p{Cntrl}", "");
            buffer.append(numb);
        }

        List<String> tripleNumbers = new ArrayList<>();
        int start = 0;
        int end = 2;

        for (int i = 0; i < buffer.length(); i += 2) {
            tripleNumbers.add(buffer.substring(start, end));
            start = start + 2;
            end = end + 2;
        }

        Set<String> uniqueTripleNumbers = new HashSet<String>(tripleNumbers);
        for (String word : uniqueTripleNumbers) {
            System.out.println(word + ": " + (Collections.frequency(tripleNumbers, word) * 100 / tripleNumbers.size()) + " %");
        }
    }
}



