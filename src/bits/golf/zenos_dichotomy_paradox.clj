(ns bits.golf.zenos-dichotomy-paradox)

(defn zenos-dichotomy-paradox []
  ; Create an anonymous function...
 ((fn [n]
    ; ... that prints the fraction (or just 1 if (= n 1))...
    (print
      (if (= 1M n) 1 (str 1 \/ n))
      \+)
    ; ; ... , doubles the denominator, then recurses infinitely.
    ; Will not SO since I'm using recur.
    (recur (* n 2)))
   ; Start the recursive function off will a 1. The M makes it a BigInteger so
   ;  I don't get an IntegerOverflow.
  1M))

#((fn[n](print(if(= 1M n)1(str 1\/ n))\+)(recur(* n 2)))1M)

