(ns bits.cr-birthday-problem
  (:require [helpers.general-helpers :as g]
            [clojure.pprint :refer [pprint]]))

(defn random-int [min max]
  (+ min (rand-int (- max min))))

(defn new-random-birthday []
  (random-int 0 365))

(defn generate-random-birthdays [n]
  (repeatedly n new-random-birthday))

(defn percentage-matching-birthdays [n-tests n-people]
  (let [n-matches (->> #(generate-random-birthdays n-people)
                       (repeatedly n-tests)
                       (remove (partial apply distinct?))
                       count)]
    (double (/ n-matches n-tests))))

(defn test-n-people-range-percentage
  "Applies percentage-matching-birthdays to a range of different numbers of people.
  Returns a 2D list in the form [[n-of-people perc-matching-birthdays]]"
  [n-tests-per min-people max-people]
  (persistent!
    (reduce
      (fn [acc n-people]
        (conj! acc [n-people (percentage-matching-birthdays n-tests-per n-people)]))
      (transient [])
      (range min-people (inc max-people)))))

(defn timed-perc-test-procedure [n-tests-per]
  (let [start-t (g/current-ms-timestamp)
        results (test-n-people-range-percentage n-tests-per 2 365)
        end-t (g/current-ms-timestamp)]

    (pprint results)

    (- end-t start-t)))
