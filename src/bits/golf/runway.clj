(ns bits.golf.runway)

(defn runway [offset])

#_ ; WUT
(let [plane "--O--"
      starting-offset 5
      starting-distance 4
      sps #(apply str (repeat % " "))]
  (loop [offset starting-offset
         distance starting-distance]
    (doseq [r (range 5 0 -1)]
      (let [row (if (= r distance) (str (sps offset) plane) "")]
        (println row)))))
