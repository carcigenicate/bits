(ns bits.golf.current-time)

(defn current-time []
  (loop [last-ns 0]
    (let [current-time (System/nanoTime)]
      (recur
        (if (>= (- current-time last-ns) 1e3)
          (do
            (println (.format (java.text.SimpleDateFormat. "h:m:s a") (java.util.Date.)))
            current-time)
          last-ns)))))
#_
(loop[l 0](let[c(System/nanoTime)](recur(if(>=(- c l)1e3)(do(println(.format(java.text.SimpleDateFormat."h:m:s a")(java.util.Date.)))c)l))))

; -----

(defn current-time []
  (while []
    (Thread/sleep 1e3)
    (println (.format (java.text.SimpleDateFormat. "h:m:s a") (java.util.Date.)))))
#_
(while[](Thread/sleep 1e3)(println(.format(java.text.SimpleDateFormat."h:m:s a")(java.util.Date.))))