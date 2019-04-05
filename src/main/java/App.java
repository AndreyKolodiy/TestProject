import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws Exception {
        App app = new App();

        List<Integer> randomList = app.collectAllIntegers();

        //1. Количество 0, и количество 1
        Map<String, Integer> oneComboMap = app.countCombinationsOfDigitsInList(randomList, 1);

        //2. Количество последовательностей из двух цифр (00, 01, 10, 11).
        Map<String, Integer> twoComboMap = app.countCombinationsOfDigitsInList(randomList, 2);

        //3. Количество последовательностей из трех цифр (000, 001, 010, и т.д.)
        Map<String, Integer> threeComboMap = app.countCombinationsOfDigitsInList(randomList, 3);

        //4. Каждый из предыдущих пунктов выразить в виде процентного соотношения. Например:
        //Из десяти сгенерированных цифр, нулей - 7, единиц 3. В процентах, соответственно, 70%/30%.
        app.showResults(oneComboMap);
        app.showResults(twoComboMap);
        app.showResults(threeComboMap);
    }

    private Map<String, Integer> countCombinationsOfDigitsInList(List<Integer> randomList, int digitNumber) {
        Map<String, Integer> comboMap = new HashMap<>();
        for (int i = 0; i < randomList.size() - digitNumber + 1; i++) {
            StringBuilder combo = new StringBuilder();
            for (int j = 0; j < digitNumber; j++) {
                combo.append(randomList.get(i + j));
            }
            String key = combo.toString();
            int value = 1;
            if (comboMap.containsKey(key)) {
                value += comboMap.get(key);
            }
            comboMap.put(key, value);
        }
        return comboMap;
    }

    private void showResults(Map<String, Integer> comboMap) {
        double comboTotal = comboMap.values().stream().mapToInt(Integer::intValue).sum();
        for (Map.Entry<String, Integer> entry : comboMap.entrySet()) {
            long percent = Math.round(entry.getValue() * 100 / comboTotal);
            System.out.format("Combination: %s, Matches: %s, Percent: %s %%\n", entry.getKey(), entry.getValue(), percent);
        }
        System.out.println(IntStream.range(1, 50).mapToObj(i -> "-").collect(Collectors.joining()));
    }

    private List<Integer> collectAllIntegers() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        List<RequestRandomTask> tasks = Stream.generate(
                RequestRandomTask::new)
                .limit(100)
                .collect(Collectors.toList());

        List<Future<List<Integer>>> results = executor.invokeAll(tasks);

        executor.shutdown();

        List<Integer> allResults = new ArrayList<>();

        for (Future<List<Integer>> intLists : results) {
            allResults.addAll(intLists.get());
        }

        return allResults;
    }
}