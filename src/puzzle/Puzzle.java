package puzzle;

/*
Для решения задачи применяется поиск в ширину (BFS Breadth-First Search)
С помощью тестов выяснил, что один из самых труднозатратных стартовых массивов: [1, 6, 3, 4, 0, 7, 2, 5]
На данный момент решение для него находится в среднем за 650 ms

UPD: Т.к. нам известен конечный массив, можно запустить двунаправленный поиск в ширину.
Один объект начинает поиск с старта до финиша, а второй с финиша до старта.
После каждого цикла проверяется есть ли совпадения построенных комбинаций,
если да, то берется маршрут до этой комбинации с старта + маршут до этой же комбинации с финиша.
Таким образом время поиска сокрашается в несколько раз.
К примеру по тому же массиву [1, 6, 3, 4, 0, 7, 2, 5] время поиска состовялет ~100ms против 650ms ранее
 */


public class Puzzle {

    public static void main(String[] args) {

        long startTime =System.currentTimeMillis();
        int[] finish = new int[] {1, 2, 3, 4, 0, 5, 6, 7};
        //int[] start = new int[] {0, 1, 2, 3, 4, 5, 6, 7};
        //int[] start = new int[] {2, 1, 3, 4, 0, 5, 6, 7};
        int[] start = new int[] {1, 6, 3, 4, 0, 7, 2, 5}; // самый сложный старт

        Search search1 = new Search(start, finish);
        Search search2 = new Search(finish, start);
        System.out.println(Search.result(search1, search2));

        long timeSpent =System.currentTimeMillis()- startTime;
        System.out.println("Время выполнения: "+ timeSpent +" миллисекунд");
    }
}
