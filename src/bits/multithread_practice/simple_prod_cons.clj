(ns bits.multithread-practice.simple-prod-cons
  (:require [helpers.general-helpers :as g]))

(defn random-wait [min-wait max-wait]
  (Thread/sleep (+ min-wait (rand-int (- max-wait min-wait)))))

(defn new-producer [item-set n-to-produce min-wait max-wait]
  (let [a (atom [])
        items (seq item-set)
        checked-to-produce (if (neg? n-to-produce) Long/MAX_VALUE n-to-produce)]
    (g/thread
      (dotimes [_ checked-to-produce]
        (let [item (rand-nth items)]
          (random-wait min-wait max-wait)
          (swap! a #(conj % item))
          (println "Produced" item "\n"))))
    a))

(defn new-consumer [producer-queue min-wait max-wait]
  (loop []
    (random-wait min-wait max-wait)

    (swap! producer-queue
           #(if (empty? %)
              %
              (do
                (println "\tConsumed" (first %) "\n")
                (vec (rest %)))))

    (recur)))

