(ns bits.so.hotel-nights)

(defn free-night? [nights-stayed]
  (<= 5 (rem nights-stayed 7) 6))

(defn -main []
  (loop [night-n 1
         free-nights 0]
    (println (str "Night#: " night-n ", Free nights: " free-nights))
    (if (<= night-n 18)
      (recur (inc night-n)
             (if (free-night? night-n) (inc free-nights) free-nights)))))
