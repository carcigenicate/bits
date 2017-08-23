(ns bits.coprime-approx-pi
  (:require [helpers.general-helpers :as g]))

; Stolen: http://stackoverflow.com/a/32386438/3000206
(defn gcd [a b]
  (if (zero? b)
    a
    (recur b (mod a b))))

(defn pi [coprimes cofactors]
  (Math/sqrt
    (/ 6 (/ coprimes (+ coprimes cofactors)))))

(defn estimate-pi [max-number n-tries rand-gen]
  (loop [cps 0
         cfs 0
         try-n 0]
    (if (>= try-n n-tries)
      (pi cps cfs)
      (let [x (g/random-int 1 max-number rand-gen)
            y (g/random-int 1 max-number rand-gen)
            cofactor? (> (gcd x y) 1)]
        (recur
          (if cofactor? cps (inc cps))
          (if cofactor? (inc cfs) cfs)
          (inc try-n))))))


