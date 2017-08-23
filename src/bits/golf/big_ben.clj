(ns bits.golf.big-ben)

#_
(defn big-ben []
  (let [d (java.util.Date.)
        hour (.getHours d)
        hour' (if (> hour 12) (- hour 12) hour)
        hour'' (if (= 0 hour') 12 hour')]
    (if (= (.getMinutes d) 0)
      (println (apply str (repeat hour'' "BONG "))))
    (Thread/sleep 2000)
    (recur)))

(defn big-ben []
  (let [d (java.util.Date.)
        hour (.getHours d)
        hour' (if (> hour 12) (- hour 12) hour)]
    (if (or (= (.getMinutes d) 0) (< (.getSeconds d) 4))
      (println (apply str (repeat (if (= 0 hour') 12 hour') "BONG "))))
    (recur)))

#(let[d(java.util.Date.)h(.getHours d)h(if(> h 12)(- h 12)h)h(if(= 0 h)12 h)](if(=(.getMinutes d)0)(println(apply str(repeat h"BONG "))))(Thread/sleep 2000)(recur))

#(let[d(java.util.Date.)h(.getHours d)h(if(> h 12)(- h 12)h)](if(=(.getMinutes d)0)(println(apply str(repeat(if(= 0 h)12 h)"BONG "))))(recur))