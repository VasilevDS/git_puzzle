package puzzle;

/*
Для решения задачи применяется поиск в ширину (BFS Breadth-First Search)
С помощью тестов выяснил, что один из самых труднозатратных стартовых массивов: [1, 6, 3, 4, 0, 7, 2, 5]
На данный момент решение находится в среднем за 650 ms
 */


public class Puzzle {

    public static void main(String[] args) {

        long startTime =System.currentTimeMillis();
        int[] finish = new int[] {1, 2, 3, 4, 0, 5, 6, 7};
        //int[] start = new int[] {2, 1, 3, 4, 0, 5, 6, 7};
        int[] start = new int[] {1, 6, 3, 4, 0, 7, 2, 5}; // самый сложный старт

        Search search = new Search(start, finish);
        System.out.println(search.outResult(search.run()));

        long timeSpent =System.currentTimeMillis()- startTime;
        System.out.println("Время выполнения: "+ timeSpent +" миллисекунд");
    }
}
