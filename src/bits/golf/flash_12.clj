(ns bits.golf.flash-12)

(defn -main []
  (loop [i 0] ; Track which sign we showed last
    (Thread/sleep 500)
    (println (["12:00" "--:--"] i)) ; Index the vector to get the string to print
    (recur (if (= 0 i)1 0))) ; Swap the index, and loop again

  (loop[i 0](Thread/sleep 500)(println(["12:00""--:--"]i))(recur(if(= 0 i)1 0))))

; -----

(doseq [m (cycle ["12:00" "--:--"])]
  (Thread/sleep 500)
  (println m))

(doseq[m(cycle["12:00""--:--"])](Thread/sleep 500)(println m))

