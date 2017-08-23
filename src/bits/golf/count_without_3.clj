(ns bits.golf.count-without-3)

(defn c [n]
  (loop [m (inc n)]
    (if (or (= (rem m 3) 0) (some #(= \3 %) (str m)))
      (recur (inc m))
      m)))

(fn c[n](loop[m(inc n)](if(or(=(rem m 3)0)(some #(=\3 %)(str m)))(recur (inc m))m)))


(defn c2 [n]
  (let [m (inc n)]
    (if (or (= (rem m 3) 0)
            (some #(= \3 %) (str m)))
      (c m)
      m)))

(fn c[n](let[m(inc n)](if(or(=(rem m 3)0)(some #(=\3 %)(str m)))(c m)m)))