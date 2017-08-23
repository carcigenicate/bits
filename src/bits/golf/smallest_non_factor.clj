(ns bits.golf.smallest-non-factor
  (:require [clojure.string :as s]))
 ; http://codegolf.stackexchange.com/questions/105412/find-the-smallest-number-that-doesnt-divide-n


(defn smallest [n]
  (let [mults (into #{} (filter (fn [m] (= (rem n m) 0)) (range 1 (+ n 1))))] ; Find multiples, and place in set for membership lookup
    (apply min ; Find minimum non-multiple
      (remove mults ; Remove multiples from the range 2 to (n+2)
              (range 2 (+ n 2))))))

#(apply min(remove(into #{}(filter(fn[m](=(rem % m)0))(range 1(+ % 1))))(range 2(+ % 2))))

(def first-100-answers-raw
  "2, 3, 2, 3, 2, 4, 2, 3, 2, 3, 2, 5, 2, 3, 2, 3, 2, 4, 2, 3, 2, 3, 2, 5, 2, \n3, 2, 3, 2, 4, 2, 3, 2, 3, 2, 5, 2, 3, 2, 3, 2, 4, 2, 3, 2, 3, 2, 5, 2, 3, \n2, 3, 2, 4, 2, 3, 2, 3, 2, 7, 2, 3, 2, 3, 2, 4, 2, 3, 2, 3, 2, 5, 2, 3, 2, \n3, 2, 4, 2, 3, 2, 3, 2, 5, 2, 3, 2, 3, 2, 4, 2, 3, 2, 3, 2, 5, 2, 3, 2, 3")

(def first-100-answers
  (mapv #(Integer/parseInt %)
     (s/split (apply str (remove #(= \newline %) first-100-answers-raw))
              #", ")))