(ns bits.golf.negative-order)

(defn negative-order [n]
  (loop [r 1
         a ""]
    (let [s (apply str (range r 0 -1))]
      (if (> r n)
        a
        (recur (inc r) (str a s))))))

#(loop[r 1 a""](let[s(apply str(range r 0 -1))](if(> r %)a(recur(inc r)(str a s)))))

; -----

(defn negative-order [n]
  (loop [r 1
         a ""]
    (if (> r n)
      a
      (recur (inc r) (str a (apply str (range r 0 -1)))))))

#(loop[r 1 a""](if(> r %)a(recur(inc r)(str a(apply str(range r 0 -1))))))

; -----