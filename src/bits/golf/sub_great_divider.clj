(ns bits.golf.sub-great-divider)

; http://codegolf.stackexchange.com/questions/79644/how-many-steps-does-it-take-from-n-to-1-by-subtracting-the-greatest-divisor
#_
(defn great-divider [n]
  (loop [m (dec n)]
    (if (or (= (rem n m) 0) (< m 2)) ; If m is a divisor, or n is prime, return m
        m
        (recur (dec m)))))  ; Else, try the next number down
#_
(defn sub-great-divide [n]
  (loop [m n
         step 1]
    (let [g-d (great-divider m) ; Find greatest divisor of m
          s (- m g-d)] ; Find the difference
      (println m " is " g-d " --> " m " - " g-d " = " s)
      (if (< s 2)
        step
        (recur s (inc step))))))

#(loop[m % t 1](let[s(- m(loop[b(dec m)](if(or(=(rem m b)0)(< b 2))b(recur(dec b)))))](if(< s 2)t(recur s(inc t)))))

; -----

(defn great-divider [n]
  ; Filter a range to find multiples, then take the last one to get the largest
  (last
     (filter #(= (rem n %) 0)
             (range 1 n))))

(defn sub-great-divide [n]
  (loop [m n
         step 1]
    (let [g-d (great-divider m) ; Find greatest divisor of m
          diff (- m g-d)] ; Find the difference
      (println m " is " g-d " --> " m " - " g-d " = " diff)
      (if (< diff 2)
        step
        (recur diff (inc step))))))

(fn[n](loop[m n t 1](let[s(- m(last(filter #(=(rem m %)0)(range 1 m))))](if(< s 2)t(recur s (inc t))))))