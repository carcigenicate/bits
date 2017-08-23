(ns bits.golf.collatz)

; http://codegolf.stackexchange.com/questions/12177/collatz-conjecture?page=2&tab=votes#tab-top

(defn collatz-conj [n]
  (loop [step 0
         m n]
    (if (= m 1)
      step
      (recur (inc step)
        (if (even? m)
          (/ m 2)
          (+ (* m 3) 1))))))

#(loop[s 0 m %](if(= m 1)s(recur(inc s)(if(even? m)(/ m 2)(+(* m 3)1)))))

; -----

(defn collatz-conj [n]
  (if (= n 1)
    0 ; Base case
    (inc ; Add one to step
      (collatz-conj ; Recurse
        (if (even? n) ; The rest should be be self-explanatory
          (/ n 2)
          (+ (* n 3) 1))))))

(fn c[n](if(= n 1)0(inc(c(if(even? n)(/ n 2)(+(* n 3)1))))))

; -----

(defn collatz-conj [n]
  (if (= n 1)
    0 ; Base case
    (inc ; Add one to step
      (collatz-conj ; Recurse
        (if (even? n) ; The rest should be be self-explanatory
          (/ n 2)
          (+ (* n 3) 1))))))

(defn test-f [n-min n-max]
  (let [c (fn c[n](if(= n 1)0(inc(c(if(even? n)(/ n 2)(+(* n 3)1))))))]
    (loop [high-step 0
           high-n nil
           n n-min]

      (if (zero? (rem n 1e6))
        (println "n " n ", high n " high-n ", high step " high-step))

      (if (>= n n-max)
        [high-n high-step]
        (let [steps (c n)
              higher? (> steps high-step)]
          (recur (if higher? steps high-step)
                 (if higher? n high-n)
                 (inc n)))))))
