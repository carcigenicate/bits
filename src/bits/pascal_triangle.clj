(ns bits.pascal-triangle)

(defn value-from-previous-row [previous-row current-index]
  (if (or (zero? current-index) (= current-index (count previous-row)))
    1
    (let [prev-i (dec current-index)]
      (+ (previous-row prev-i)
         (previous-row current-index)))))

(defn new-row-from-previous [previous-row]
  (vec
    (for [i (range (inc (count previous-row)))]
      (value-from-previous-row previous-row i))))

(defn p-tri-rows [n-rows]
  (if (< n-rows 1)
    []
    (loop [[row-n & rest-ns] (vec (range 2 (inc n-rows)))
           acc [[1]]]
      (if row-n
        (recur rest-ns
               (conj acc (new-row-from-previous (last acc))))
        acc))))

(defn p-tri [height])

