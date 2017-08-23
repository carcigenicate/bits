(ns bits.birthday-problem
  (:require [helpers.general-helpers :as g]
            [clojure.pprint :refer [pprint]]))

(set! *warn-on-reflection* true)

(defn new-random-birthday [rand-gen]
  (g/random-int 0 365 rand-gen))

(defn generate-random-birthdays
  "Generates a list of random birthdays."
  ^longs [n-birthdays rand-gen]
  (persistent!
      (reduce (fn [acc _]
                (conj! acc (new-random-birthday rand-gen)))
              (transient [])
              (range n-birthdays))))

(defn count-matching-birthdays
  "Returns the number of matching birthdays in a list."
  ^long [^longs birthdays]
  (- (count birthdays) (count (into #{} birthdays))))

(defn percentage-matching-birthdays
  "Returns the percentage of tests that contained matching birthdays for the given number of people."
  [n-tests n-people rand-gen]
  (let [n-matches (g/iterate-many 0 n-tests
                    (fn [n-dupes]
                      (let [rand-birthdays (generate-random-birthdays n-people rand-gen)]
                        (if (> (count-matching-birthdays rand-birthdays) 0)
                          (inc n-dupes)
                          n-dupes))))]
    (double (/ n-matches n-tests))))

(defn test-n-people-range-percentage
  "Applies percentage-matching-birthdays to a range of different numbers of people.
  Returns a 2D list in the form [[n-of-people perc-matching-birthdays]]"
  [n-tests-per min-people max-people rand-gen]
  (persistent!
    (reduce
      (fn [acc n-people]
        (conj! acc [n-people (percentage-matching-birthdays n-tests-per n-people rand-gen)]))
      (transient [])
      (range min-people (inc max-people)))))

(defn average-matching-birthdays [n-tests n-people rand-gen]
  (let [n-matches (g/iterate-many 0 n-tests
                                  (fn [n-dupes]
                                    (let [rand-birthdays (generate-random-birthdays n-people rand-gen)]
                                      (+ n-dupes (count-matching-birthdays rand-birthdays)))))]
    (double (/ n-matches n-tests))))

(defn timed-perc-test-procedure [n-tests-per rand-gen]
  (let [start-t (g/current-ms-timestamp)
        results (test-n-people-range-percentage n-tests-per 2 365 rand-gen)
        end-t (g/current-ms-timestamp)]

    (pprint results)

    (- end-t start-t)))

(defn test-n-people-range-average
  "Applies percentage-matching-birthdays to a range of different numbers of people.
  Returns a 2D list in the form [[n-of-people perc-matching-birthdays]]"
  [n-tests-per min-people max-people rand-gen]
  (persistent!
    (reduce
      (fn [acc n-people]
        (conj! acc [n-people (average-matching-birthdays n-tests-per n-people rand-gen)]))
      (transient [])
      (range min-people (inc max-people)))))

(defn timed-average-test-procedure [n-tests-per rand-gen]
  (let [start-t (g/current-ms-timestamp)
        results (test-n-people-range-average n-tests-per 2 365 rand-gen)
        end-t (g/current-ms-timestamp)]

    (pprint results)

    (- end-t start-t)))



