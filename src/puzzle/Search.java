package puzzle;

import java.util.*;

public class Search {
   private int [] start;
   private int [] finish;
   private int combNumb;
   private int level;
   private Queue<int[]> queueList; // очередь комбинаций
   private ArrayList<Integer> HistoryMoves; // история ходов
   private ArrayList<Integer> LvlList;     // история уровней, нужна для восстановления истории ходов
   private HashSet<String>VisitComb;  // массив для отслеживания уже проверенных комбинаций

    public Search(int[] start, int[] finish) {
        this.start = start;
        this.finish = finish;
        this.combNumb = 1;
        this.level = 0;
        this.queueList = new LinkedList<>();
        this.queueList.add(start);
        this.HistoryMoves = new ArrayList<>();
        this.HistoryMoves.add(0,0);
        this.LvlList = new ArrayList<>();
        this.LvlList.add(0,0);
        this.VisitComb = new HashSet<>();
        this.VisitComb.add(null);
    }

    public int run() {

        while (queueList.size() > 0) { // поиск пока очередь не пуста
            int[] comb = queueList.poll();
            int zero = this.zeroCell(comb);
            for (int i = 0; i < comb.length; i++) {

                if (this.neighbors(zero, i) &&
                        comb[i] != HistoryMoves.get(level)) {
                    int[] temp = comb.clone();
                    temp[i] = 0;
                    temp[zero] = comb[i];

                    if (VisitComb.add(Arrays.toString(temp))) { // true при добавлении, иначе false
                        queueList.add(temp);
                        HistoryMoves.add(combNumb, comb[i]);
                        LvlList.add(combNumb, level);
                        if (Arrays.equals(temp, finish)) return combNumb; // возвращает номер комбинации
                        combNumb++;
                    }
                }
            } level++;
        }
        return 0;
    }

    public String outResult (int combNumb) {

        if(combNumb == 0) return null;

        ArrayList<Integer> tempList = new ArrayList<>();
        tempList.add(HistoryMoves.get(combNumb));

        while (LvlList.get(combNumb) != 0) {
            combNumb = LvlList.get(combNumb);
            tempList.add(HistoryMoves.get(combNumb));
        }
        int[] res = new int[tempList.size()];
        for (int j = 0; j < res.length; j++) {
            res[j] = tempList.get(res.length - j - 1);
        }
        return Arrays.toString(res);
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
