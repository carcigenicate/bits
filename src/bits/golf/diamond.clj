(ns bits.golf.diamond)
#_
(defn diamond []
  (let [spaces #(apply str (repeat % " ")) ; Shortcut function that produces % many spaces
        n (atom 0)] ; Mutable container holding the highest number that should appear in the row. EWW
    (doseq [row (range 18)]
      (let [top? (< row 9) ; Are we in the top?
            f (if top? inc dec) ; Decide if we need to increment or decrement n
            n-spaces (if top? (- 8 row) (- row 8))] ; Calculate how many spaces the row should be prefixed with
        (swap! n f) ; Increment/decrement n
        (print (spaces n-spaces)) ; Print the prefix spaces
        (doseq [m (concat (range 1 @n) (range @n 0 -1))] ; Loop over the range of number in the row
          (print m)) ; Print the number
        (println)))))

#(let[n(atom 0)](doseq[r(range 18)](let[s(if(< r 9)(- 8 r)(- r 8))](swap! n(if(< r 9)inc dec))(print(apply str(repeat s" ")))(doseq[m(concat(range 1 @n)(range @n 0 -1))](print m))(println))))

; -----

(defn diamond []
  (let [spaces #(apply str (repeat % " "))] ; Shortcut function that produces % many spaces
    (loop [[row-n & r-rows] (range 18) ; Deconstruct the row number from the range
           high-n 1] ; Keep track of the highest number that should appear in the row
      (let [top? (< row-n 8) ; Are we on the top of the diamond?
            f (if top? inc dec) ; Decided if we should increment or decrement
            n-spaces (if top? (- 8 row-n) (- row-n 8))] ; Calculate how many prefix-spaces to print
        (print (spaces n-spaces)) ; Print prefix-spaces
        (doseq [m (concat (range 1 high-n) (range high-n 0 -1))] ; Loop over the row of numbers
          (print m)) ; Print the number
        (println)

        (if r-rows
          (recur r-rows (f high-n)))))))

#(loop[[r & s](range 18)h 1](print(apply str(repeat(if(< r 8)(- 8 r)(- r 8))\ )))(doseq[m(concat(range 1 h)(range h 0 -1))](print m))(println)(if s(recur s((if(< r 8)inc dec)h))))


#_(
    1
    1       2       1
    1      2      3      2      1
    1     2     3     4     3     2     1
    1    2    3    4    5    4    3    2    1
    1   2   3   4   5   6   5   4   3   2   1
    1  2  3  4  5  6  7  6  5  4  3  2  1
    1 2 3 4 5 6 7 8 7 6 5 4 3 2 1
    12345678987654321
    1 2 3 4 5 6 7 8 9 10 9 8 7 6 5 4 3 2 1
    1  2  3  4  5  6  7  8  9  8  7  6  5  4  3  2  1
    1   2   3   4   5   6   7   8   7   6   5   4   3   2   1
    1    2    3    4    5    6    7    6    5    4    3    2    1
    1     2     3     4     5     6     5     4     3     2     1
    1      2      3      4      5      4      3      2      1
    1       2       3       4       3       2       1
    1        2        3        2        1
    1         2         1)
