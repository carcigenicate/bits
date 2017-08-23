(ns bits.golf.how-much-to-write)

(defn how-much-to-write? [n]
  ; Count the characters in the resulting string
  (count
    ; Basically "joins" the list with ""
    (apply str
      ; Creates an ascending/descending range, depending on the value of n
      (range (min 0 n) (max 1 (inc n))))))

(count(apply str(range(min 0 %)(max 1(inc %)))))