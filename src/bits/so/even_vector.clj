(ns bits.even-vector)

; http://stackoverflow.com/questions/40873489/clojure-find-even-numbers-in-a-vector/40874182#40874182

(defn even-vector-2 [input]
  (def output [])
  (loop [x input]
    (if (not= (count x) 0)
      (do
        (if (= (mod (first x) 2) 0)
          (do
            (def output (conj output (first x)))))
        (recur (rest x)))))
  output)

(defn even-vector-3 [input]
  (loop [[n & rest-input] input ; Deconstruct the head from the tail
         output []] ; Output is just looped with the input
    (if n ; n will be nil if the list is empty
        (recur rest-input
               (if (= (mod n 2) 0)
                 (conj output n)
                 output)) ; Adding nothing since the number is odd
        output)))

(defn even-vector-4 [input]
  (reduce
    (fn [acc n]
      (if (= (rem n 2) 0)
        (conj acc n)
        acc))
    [] ; This is the initial accumulator.
    input))

