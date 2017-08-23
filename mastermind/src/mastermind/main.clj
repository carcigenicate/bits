(ns mastermind.main
  (:require [helpers.general-helpers :as g]
            [clojure.string :as s :refer [split trim]])

  (:gen-class))

(def default-max-num 5)
(def default-seq-length 5)

(defn print-fl [s]
  (print s)
  (flush))

(defn ask-for-input [prompt-message validator-f error-message]
  (loop []
    (print-fl prompt-message)
    (let [inp (read-line)]
      (if (validator-f inp)
        inp
        (do
          (print-fl error-message)
          (recur))))))

(defn ask-for-num-input
  "Asks for input and verifies it. If it's invalid, it asks again.
  Returns the parsed number.

  Only accepts integer input."
  [prompt-message]
  (Integer/parseInt
     (ask-for-input
       prompt-message
       g/numeric?
       "Enter a number.\n")))

(defn valid-seq-input? [input-str]
  (every?
    (fn [^Character c] (or (Character/isWhitespace c)
                           (g/numeric? (str c))))
    input-str))

(defn ask-for-sequence [prompt error-message]
    (let [raw (ask-for-input
                prompt
                valid-seq-input?
                error-message)
          split-nums (split (trim raw) #"\s+")]
      (mapv #(Integer/parseInt %) split-nums)))

(defn seq-error-message [length]
  (str "Enter " length " numbers seperated by spaces."))

(defn count-errors
  "Counts the number of differences between the sequences.
  Assumes they're the same length."
  [guess-seq actual-seq]
  (reduce (fn [c [guess actual]]
            (if (not= guess actual)
              (inc c)
              c))
          0
          (map vector guess-seq actual-seq)))

(defn same-length? [xs ys]
  (= (count xs) (count ys)))

(defn generate-seq [length choice-set rand-gen]
  (mapv
    (fn [_] (g/random-from-collection choice-set rand-gen))
    (range length)))

(defn game-loop [secret-seq max-num]
  (let [prompt (str "Enter " (count secret-seq) " numbers between 0 and " max-num " (inclusive): ")
        seq-len (count secret-seq)
        ask-f #(ask-for-sequence prompt (seq-error-message seq-len))]
    (loop [tries 0]
      (let [guess (ask-f)
            n-errors (count-errors secret-seq guess)
            tries' (inc tries)]
        (if (> n-errors 0)
          (do
            (println (str "Wrong. Errors: " n-errors))
            (recur tries'))
          (do
            (println (str "Right! Tries: " tries'))
            tries'))))))

(defn -main [& args]
  (let [s-args (mapv str args)
        p #(Integer/parseInt %)
        rand-gen (g/new-rand-gen)
        [seq-length max-num] (if s-args
                               [(p (s-args 0)) (p (s-args 1))]
                               [default-seq-length default-max-num])]
    (loop [record nil]
      (let [secret-seq (generate-seq seq-length
                                     (into [] (range (inc max-num)))
                                     rand-gen)
            tries (game-loop secret-seq max-num)
            record' (if (or (nil? record) (< tries record)) tries record)]
        (println (str "Record: " record))
        (recur record')))))

