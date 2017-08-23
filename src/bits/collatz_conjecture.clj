(ns bits.collatz-conjecture
  (:require [helpers.general-helpers :as g]))

(defn collatz-conjecture [n]
  (loop [m n
         s [n]]
    (let [m' (if (= (rem m 2) 0)
               (/ m 2)
               (inc (* 3 m)))
          s' (conj s m')]
      (if (= m 1)
        s
        (recur m' s')))))

(defn test-routine [start-n end-n]
  (let [print-every (int (/ (- end-n start-n) 50))
        r (for [n (range start-n end-n)]
            (do
              (if (= (rem n print-every) 0)
                (println (str "Testing " n " (" (* 100.0 (/ n end-n)) " %)")))

              [n (count (collatz-conjecture n))]))]
    (vec r)))

(defn- string-line [line-char length]
  (apply str (repeat length line-char)))

(defn- find-max-counts [results]
  (reduce (fn [maxs [n c :as pair]]
            (let [max-count (if (empty? maxs) 0 (second (first maxs)))]
              (cond
                (> c max-count) [pair]
                (= c max-count) (conj maxs pair)
                :else maxs)))
          []
          results))

(defn graph-results [results max-line-length line-character]
  (let [max-counts (find-max-counts results)
        max-count (if (empty? max-counts) 0 (second (first max-counts)))
        ratio (/ max-count max-line-length)]
    (doseq [[n c] results
            :let [post (if (= c max-count) " <=")]]
      (println (str n ": " (string-line line-character (int (/ c ratio))) post)))
    max-counts))