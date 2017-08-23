(ns bits.multithread-pratice.prime-multi-thread
  (:require [helpers.general-helpers :as g]))

(defn factor-of? [factor base]
  (zero? (rem base factor)))

(defn brute-prime? [n]
  (if (<= 0 n 3)
    true

    (reduce (fn [a fact]
              (if (factor-of? fact n)
                (reduced false)
                a))
            true
            (range 2 (inc (Math/sqrt n))))))

(defn divided-ranges [start-n end-n n-ranges]
  (let [start (min start-n end-n)
        end (max start-n end-n)
        n-per-range (/ (- end start) n-ranges)]
    (for [s (range 0 end-n n-per-range)]
      (range s (+ s n-per-range)))))

(defn multi-prime-finder [start-n end-n n-threads]
  (let [ranges (divided-ranges start-n end-n n-threads)
        new-finder #(future (filter brute-prime? %))

        futs (map new-finder ranges)
        primes (map deref futs)]
    (apply concat primes)))