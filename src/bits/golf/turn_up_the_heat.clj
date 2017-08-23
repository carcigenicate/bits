(ns bits.golf.turn-up-the-heat)

;

; Sleeps at the end
(defn turn-up-the-heat []
  (doseq [t (range 32 73 2)]
    (println t)
    (Thread/sleep 500)))

#(doseq[t(range 32 73 2)](println t)(Thread/sleep 500))

; -----

(defn turn-up-the-heat []
  (doseq [t (range 32 73 2)]
    (println t)
    (if (< t 72)
      (Thread/sleep 500))))

#(doseq[t(range 32 73 2)](println t)(if(< t 72)(Thread/sleep 500)))