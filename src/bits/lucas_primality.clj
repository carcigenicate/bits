(ns bits.lucas-primality
  (:require [helpers.general-helpers :as g]))

; https://www.youtube.com/watch?v=lEvXcTYqtKU&t=231s

; Fail. Too slow compared to trial-and-error division. Requires too much memory for larger numbers.

(defn lazy-nth [n l-seq]
  (first
    (take 1 (drop n l-seq))))

(defn lucas-seq []
  (map first
    (iterate
      (fn [[x y]] [y (+ x y)])
      [1N 3N])))

(defn nth-fib [a b n]
  (let [big-n (bigint n)]
    (loop [x (bigint a)
           y (bigint b)
           m 0N]
      (if (= big-n m)
        x
        (recur y (+ x y) (inc m))))))

(def lucas-nums (lucas-seq))

(defn factor? [n m]
  (zero? (rem n m)))

(defn maybe-prime? [n]
  (let [; dec'ing to change to 1-based indexing
        nth-luc (nth-fib 1 3 (dec n))
        dec-luc (dec nth-luc)]
    (factor? dec-luc n)))

(defn definately-prime? [n]
  (reduce
    (fn [acc fact]
      (if (factor? n fact)
        (reduced false)
        acc))
    true
    (range 2 (inc (Math/sqrt n)))))
