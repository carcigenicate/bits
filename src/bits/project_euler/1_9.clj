(ns bits.project-euler.1-9)

(defn- mult? [x y]
  (zero? (rem x y)))

(def ^:private sum
  (partial reduce + 0))

(defn q1 []
  (sum
    (filter #(or (mult? % 3)
                 (mult? % 5))
            (range 1000))))

(defn q2 []
  (sum
    (filter even?
      (loop [[last1 last2 :as acc] '(2 1)]
        (let [sum (+ last1 last2)]
          (if (<= sum 4e6)
            (recur (conj acc sum))
            acc))))))

(defn- factors-of
  "Excludes 1 and n"
  [n]
  (filter #(mult? n %) (range 2 n)))

(defn- prime? [n]
  (< (count (factors-of n))
     1))

(defn q3 []
  (apply max
    (filter prime? (factors-of 600851475143))))

(defn q3-2 []
  (let [n 600851475143]
    (loop [m n]
      (if (or (< m 2)
              (and (mult? m n)
                (prime? m)))
        m
        (recur (dec m))))))

(defn- factors-of-r
  "Excludes 1 and n. Largest First"
  [n]
  (filter #(mult? n %) (range (dec n) 1 -1)))

(defn q3-3 []
  (apply max
    (filter prime? (factors-of-r 600851475143))))
