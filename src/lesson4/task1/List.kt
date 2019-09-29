@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var abs = 0.0
    if (v.isEmpty()) return 0.0
    for (i in 0 until v.size)
        abs += sqr(v[i])
    return sqrt(abs)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.isEmpty()) return 0.0
    var sum = 0.0
    for (i in 0 until list.size)
        sum += list[i]
    var mid = 0.0
    mid = sum / list.size
    return mid
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isEmpty()) return list
    var sum = 0.0
    for (i in 0 until list.size)
        sum += list[i]
    var mid = 0.0
    mid = sum / list.size
    for (i in 0 until list.size)
        list[i] = list[i] - mid
    return list

}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var product = 0
    if (a.isEmpty() || b.isEmpty()) return 0
    if (a.size >= b.size) {
        for (i in 0 until b.size)
            product += a[i] * b[i]
    } else {
        for (i in 0 until a.size)
            product += a[i] * b[i]
    }
    return product
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    if (p.isEmpty()) return 0
    var sum = 0
    var iks = 1
    for (i in 1 until p.size) {
        for (k in 1..i)
            iks *= x
        sum += p[i] * iks
        iks = 1
    }
    sum += p[0]
    return (sum)
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    if (list.isEmpty()) return mutableListOf()
    var sum = 0
    for (i in list.size - 1 downTo 1) {
        for (k in 0..i)
            sum += list[k]
        list[i] = sum
        sum = 0
    }
    return list


}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var list = mutableListOf<Int>()
    var n1 = n
    var m = 2
    while (n1 > 1) {
        while (n1 % m != 0)
            m += 1
        list.add(m)
        n1 /= m
        m = 2
    }
    return list
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    var list = mutableListOf<Int>()
    var n1 = n
    var m = 2
    while (n1 > 1) {
        while (n1 % m != 0)
            m += 1
        list.add(m)
        n1 /= m
        m = 2
    }
    return list.joinToString(separator = "*")


}

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var n1 = n
    var list = mutableListOf<Int>()
    while (n1 >= base) {
        list.add(n1 % base)
        n1 /= base
    }
    list.add(n1)
    return list.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String = TODO()

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    var k = 1
    for (i in digits.size - 1 downTo 0) {
        result += digits[i] * k
        k *= base
    }
    return result

}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int = TODO()

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var res: String = ""
    var n1 = n
    while (n1 / 1000 > 0) {
        res += "M"
        n1 -= 1000
    }
    while (n1 / 900 > 0) {
        res += "CM"
        n1 -= 900
    }
    while (n1 / 500 > 0) {
        res += "D"
        n1 -= 500
    }
    while (n1 / 400 > 0) {
        res += "CD"
        n1 -= 400
    }
    while (n1 / 100 > 0) {
        res += "C"
        n1 -= 100
    }
    while (n1 / 90 > 0) {
        res += "XC"
        n1 -= 90
    }
    while (n1 / 50 > 0) {
        res += "L"
        n1 -= 50
    }
    while (n1 / 40 > 0) {
        res += "XL"
        n1 -= 40
    }
    while (n1 / 10 > 0) {
        res += "X"
        n1 -= 10
    }
    while (n1 / 9 > 0) {
        res += "IX"
        n1 -= 9
    }
    while (n1 / 5 > 0) {
        res += "V"
        n1 -= 5
    }
    while (n1 / 4 > 0) {
        res += "IV"
        n1 -= 4
    }
    while (n1 / 1 > 0) {
        res += "I"
        n1 -= 1
    }
    return res
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var list = mutableListOf<String>()
    when (n / 100000) {
        1 -> list.add("сто")
        2 -> list.add("двести")
        3 -> list.add("триста")
        4 -> list.add("четыреста")
        5 -> list.add("пятьсот")
        6 -> list.add("шестьсот")
        7 -> list.add("семьсот")
        8 -> list.add("восемьсот")
        9 -> list.add("девятьсот")
    }
    if (n % 100000 / 1000 in 10..19) {
        when (n % 100000 / 1000) {
            10 -> list.add("десять тысяч")
            11 -> list.add("одиннадцать тысяч")
            12 -> list.add("двенадцать тысяч")
            13 -> list.add("тринадцать тысяч")
            14 -> list.add("четырнадцать тысяч")
            15 -> list.add("пятнадцать тысяч")
            16 -> list.add("шестнадцать тысяч")
            17 -> list.add("семнадцать тысяч")
            18 -> list.add("восемнадцать тысяч")
            19 -> list.add("девятнадцать тысяч")
        }
    } else {
        when (n % 100000 / 10000) {
            2 -> list.add("двадцать")
            3 -> list.add("тридцать")
            4 -> list.add("сорок")
            5 -> list.add("пятдесят")
            6 -> list.add("шестьдесят")
            7 -> list.add("семьдесят")
            8 -> list.add("восемдесят")
            9 -> list.add("девяноста")
        }
        if (n / 1000 != 0) {
            when (n % 10000 / 1000) {
                0 -> list.add("тысяч")
                1 -> list.add("одна тысяча")
                2 -> list.add("две тысячи")
                3 -> list.add("три тысячи")
                4 -> list.add("четыре тысячи")
                5 -> list.add("пять тысяч")
                6 -> list.add("шесть тысяч")
                7 -> list.add("семь тысяч")
                8 -> list.add("восемь тысяч")
                9 -> list.add("девять тысяч")
            }
        }
    }
    when (n % 1000 / 100) {
        1 -> list.add("сто")
        2 -> list.add("двести")
        3 -> list.add("триста")
        4 -> list.add("четыреста")
        5 -> list.add("пятьсот")
        6 -> list.add("шестьсот")
        7 -> list.add("семьсот")
        8 -> list.add("восемьсот")
        9 -> list.add("девятьсот")
    }
    if (n % 100 in 10..19) {
        when (n % 100) {
            10 -> list.add("десять")
            11 -> list.add("одиннадцать")
            12 -> list.add("двенадцать")
            13 -> list.add("тринадцать")
            14 -> list.add("четырнадцать")
            15 -> list.add("пятнадцать")
            16 -> list.add("шестнадцать")
            17 -> list.add("семнадцать")
            18 -> list.add("восемнадцать")
            19 -> list.add("девятнадцать")
        }
        return list.joinToString()
    }
    when (n % 100 / 10) {
        2 -> list.add("двадцать")
        3 -> list.add("тридцать")
        4 -> list.add("сорок")
        5 -> list.add("пятдесят")
        6 -> list.add("шестьдесят")
        7 -> list.add("семьдесят")
        8 -> list.add("восемьдесят")
        9 -> list.add("девяноста")
    }
    when (n % 10) {
        1 -> list.add("один")
        2 -> list.add("два")
        3 -> list.add("три")
        4 -> list.add("четыре")
        5 -> list.add("пять")
        6 -> list.add("шесть")
        7 -> list.add("семь")
        8 -> list.add("восемь")
        9 -> list.add("девять")
    }
    return list.joinToString(separator = " ")
}

