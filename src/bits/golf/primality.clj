(ns bits.golf.primality)

; http://codegolf.stackexchange.com/questions/57617/is-this-number-a-prime/105807#105807

(let [n (Integer/parseInt (read-line))]
  (println
    (and (not= 1 n) ; 1 is an unfortunate special case
         (not ; Negate to indicate primality
           (some #(= (rem n %) 0) ; Check if n has any divsors...
                 (range 2 n)))))) ; in the range of 2 to n

(let[n(Integer/parseInt(read-line))](println(and(not= 1 n)(not(some #(=(rem n %)0)(range 2 n))))))

; -----

(fn [n]
  (and (not= 1 n)
    (not (some #(= (rem n %) 0) (range 2 n)))))

(fn[n](if(= 1 n)false(not(some #(=(rem n %)0)(range 2 n)))))

(fn[n](and(not= 1 n)(not(some #(=(rem n %)0)(range 2 n)))))




