import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Counter {

    public static void main(String[] args) throws IOException {
        //random.org/integers
        String url = "https://www.random.org/integers/?num=10000&min=0&max=1&col=1&base=10&format=html&rnd=new";
        Document document = Jsoup.connect(url).get();

        List<String> numbers = new ArrayList<>();

        Elements data = document.getElementsByAttributeValue("class", "data");
        data.forEach(date ->{
            String numb = date.text();
            numbers.add(numb);
        });

        numbers.forEach(numb ->{
            numb.replaceAll("\\s+","");
            System.out.println(numb.length());
        });
    }
}


