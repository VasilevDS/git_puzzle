# Головоломка
  Ограничения: время – 0.5s, память – 256MB
Для проверки интеллекта роботов используется головоломка, показанная на рисунке, в которой цифры от 1 до 7 располагаются в ячейках, а одна из ячеек свободна. Необходимо переставить цифры, используя свободную ячейку, чтобы цифры расположились так, как показано на рисунке. Перемещение буквы в свободную ячейку возможно, только если ячейки соединены линией.
            
![screenshot of sample](https://ipc.susu.ru/39590.png)
---
  Напишите методу на java, которая определяет минимальное количество ходов для решения головоломки.
На вход методу передается массив чисел, который содержит перестановку чисел 0, 1, 2, 3, 4, 5, 6, 7 – начальную
позицию. Числа перечисляются в порядке чтения слева направо, сверху вниз. Число 0 обозначает пустую ячейку. Метод должен
вывести последовательность ходов – последовательность цифр от 1 до 7, которые нужно перемещать на очередном ходе в
свободную ячейку. Если существует несколько минимальных вариантов для последовательности ходов, то можно вывести любой
из них. Гарантируется, что все начальные позиции для головоломки имеют решение.

## Пример 1
Ввод

1 2 3 4 0 5 6 7

Вывод

<пустой массив>
## Пример 2
Ввод

2 1 3 4 0 5 6 7

Вывод

5 3 2 1 2 3 5
## Пример 3
Ввод

0 1 2 3 4 5 6 7

Вывод

2 1 3 4 5 1 3 2 3 1 5 4 2 1 3 1 2 4

