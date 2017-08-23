(ns bits.golf.for)

(defn my-for [init-num com-str com-num inc-num]
  (let [op (if (= com-str "<") < >)] ; Figure out which operator to use
    (loop [n init-num]
      (when (op n com-num) ; When the condition is true, print and recur
        (println n)
        (recur (+ n inc-num))))))
      ; Else, terminate (implicit)

#(loop[n %](when((if(= %2"<")< >)n %3)(println n)(recur(+ n %4))))

; -----

(defn my-for2 [init-num com-str com-num inc-num]
  (let [op (if (= com-str "<") < >)] ; Figure out which operator to use
    (when (op init-num com-num) ; When the condition is true, print and recur
      (println init-num)
      (recur (+ init-num inc-num) com-str com-num inc-num))))
    ; Else, terminate (implicit)

#(when((if(= %2"<")< >)% %3)(println %)(recur(+ % %4)%2 %3 %4))

; -----

