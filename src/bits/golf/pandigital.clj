(ns bits.golf.pandigital)

; count? will be nil when first called
#_
(defn pan [num & count?]
  (let [
        n (bigint num)

        ; Default the count if not initialized yet
        c (or count? 0)

        ; Remove all the characters from the stringified input
        ;  that are numeric. If the result is an empty list, all
        ;  the numbers were present.
        all-nums? (empty?
                    (remove (set (str n)) "0123456789"))]
    (if all-nums?
      c
      (recur (* 2 n) (inc c)))))

(defn pan [num]
  (loop [; Cast to BigInteger so we don't get an overflow
         n (bigint num)
         cnt 0]

    ; Remove all the characters from the stringified input
    ;  that are numeric. If the result is an empty list, all
    ;  the numbers were present.
    (if (empty? (remove (set (str n)) "1234567890"))
      cnt
      (recur (* 2 n) (inc cnt)))))

(fn[n & x](let[m(bigint n)c(or x 0)](if(empty?(remove(set(str m))(map char(range 48 58))))c(recur(* 2 m)(inc c)))))

#(loop[n(bigint %)c 0](if(empty?(remove(set(str n))"1234567890"))c(recur(* 2 n)(inc c))))
