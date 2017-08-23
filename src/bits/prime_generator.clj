(ns bits.prime-generator
  (:require [helpers.general-helpers :as g]))

(def min-sqrt-shortcut 15)

(defn multiple-of? [numer denom]
  (zero? (rem numer denom)))

(defn prime? [n]
  (let [initial (if (<= n min-sqrt-shortcut)
                  (dec n)
                  (int (+ 2 (Math/sqrt n))))]
    (loop [mult initial]
      (if (< mult 2)
        true
        (if (multiple-of? n mult)
          false
          (recur (dec mult)))))))

(defn gen-prime [start-n end-n]
  (lazy-seq
    (loop [n start-n]
      (conj))))
