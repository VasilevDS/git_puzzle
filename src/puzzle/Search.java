package puzzle;

import java.util.*;

public class Search {
   private int [] start;
   private int [] finish;
   private int combNumb;
   private int firstCombNumb;
   private int secondCombNumb;
   private int level;
   private Queue<int[]> queueList; // очередь комбинаций
   private ArrayList<Integer> HistoryMoves; // история ходов
   private ArrayList<Integer> LvlList;     // история уровней, нужна для восстановления истории ходов
   private HashMap<String, Integer>VisitComb;  // массив для отслеживания уже проверенных комбинаций

    public Search(int[] start, int[] finish) {
        this.start = start;
        this.finish = finish;
        this.combNumb = 1;
        this.secondCombNumb = 0;
        this.level = 0;
        this.queueList = new LinkedList<>();
        this.queueList.add(start);
        this.HistoryMoves = new ArrayList<>();
        this.HistoryMoves.add(0,0);
        this.LvlList = new ArrayList<>();
        this.LvlList.add(0,0);
        this.VisitComb = new HashMap<>();
        this.VisitComb.put(null, 0);

        if(Arrays.equals(start, finish)) this.firstCombNumb = 0;
        else this.firstCombNumb = -1;
    }

    public static String result(Search searchFromStart, Search searchFromFinish) {

        String result = "";

        if(searchFromStart.firstCombNumb >= 0) {
            if(searchFromStart.secondCombNumb > 0) // если поиск удался с двух объектов
                result = searchFromStart.resultList(searchFromStart.firstCombNumb,
                                                    searchFromStart.secondCombNumb,
                                                    searchFromFinish
                                                    ).toString();

            else { // если поиск удался с одного объекта
                ArrayList<Integer> listRes = searchFromStart.resultList(searchFromStart.firstCombNumb);
                Collections.reverse(listRes);  // требуется перевернуть массив т.к. эта история передвижений с конца
                result = listRes.toString();
            }

        }
        searchFromStart.BFS2(searchFromFinish); // поиск по ширине с стартового массива


        if(searchFromFinish.firstCombNumb >= 0 && result.isEmpty()) {
            if(searchFromStart.secondCombNumb > 0) // если поиск удался с двух объектов
                result = searchFromStart.resultList(searchFromStart.firstCombNumb,
                                                    searchFromStart.secondCombNumb,
                                                    searchFromFinish
                                                    ).toString();

            else  // если поиск удался с одного объекта
                result = searchFromFinish.resultList(searchFromFinish.firstCombNumb).toString();

        }
        searchFromFinish.BFS2(searchFromStart);  // поиск по ширине с финального массива

        if(!result.isEmpty()) return result;
        // рекурсия до тех пор пока не будет найдено решение
        return result(searchFromStart, searchFromFinish);
    }

    public void BFS2(Search s2) {
        int[] comb = queueList.poll();
        int zero = this.zeroCell(comb);
        for (int i = 0; i < comb.length; i++) {

            if (this.neighbors(zero, i) &&
                    comb[i] != HistoryMoves.get(level)) {
                int[] temp = comb.clone();
                temp[i] = 0;
                temp[zero] = comb[i];

                String listStr = Arrays.toString(temp);
                if (!VisitComb.containsKey(listStr)) {
                    VisitComb.put(listStr, combNumb);
                    queueList.add(temp);
                    HistoryMoves.add(combNumb, comb[i]);
                    LvlList.add(combNumb, level);
                    if (Arrays.equals(temp, finish)) firstCombNumb = combNumb;
                    if (s2.VisitComb.containsKey(listStr)) {  // проверка есть ли такая же комбинация во втором объекте
                        firstCombNumb = combNumb;
                        secondCombNumb = s2.VisitComb.get(listStr);
                    }
                    combNumb++;
                }
            }
        } level++;
    }

    // наполнение массива истории передвижения с одного объекта
    public ArrayList resultList (int combNumb) {

        if(combNumb == 0) return new ArrayList<>();

        ArrayList<Integer> tempList = new ArrayList<>();
        tempList.add(HistoryMoves.get(combNumb));

        while (LvlList.get(combNumb) != 0) {
            combNumb = LvlList.get(combNumb);
            tempList.add(HistoryMoves.get(combNumb));
        }
        //Collections.reverse(tempList);
        return tempList;
    }

    // наполнение массива истории передвижения с двух объектов
    public ArrayList resultList (int secondCombNumb, int firstCombNumb, Search s2) {

        ArrayList<Integer> tempList = resultList(secondCombNumb);
        Collections.reverse(tempList);
        tempList.addAll(s2.resultList(firstCombNumb));

        return tempList;
    }

    public boolean neighbors(int zero, int a) {
        boolean[][] neigh = new boolean[8][8]; // массив смежности

        neigh[0][1] = true;
        neigh[0][2] = true;

        neigh[1][0] = true;
        neigh[1][2] = true;
        neigh[1][3] = true;

        neigh[2][5] = true;
        neigh[2][1] = true;
        neigh[2][0] = true;

        neigh[3][4] = true;
        neigh[3][6] = true;
        neigh[3][1] = true;

        neigh[4][3] = true;
        neigh[4][5] = true;

        neigh[5][4] = true;
        neigh[5][2] = true;
        neigh[5][7] = true;

        neigh[6][7] = true;
        neigh[6][3] = true;

        neigh[7][5] = true;
        neigh[7][6] = true;


        if (neigh[zero][a]) return true;
        else return false;
    }

    // поиск нулевой ячейки
    public final int zeroCell(int[] comb) {
        for (int i = 0; i < comb.length; i++) {
            if (comb[i] == 0) return i;
        }
        return -1;
    }
}

