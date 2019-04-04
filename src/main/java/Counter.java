import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

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
    }
}



