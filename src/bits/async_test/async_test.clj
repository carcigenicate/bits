(ns bits.async-test.async-test
  (:require [helpers.general-helpers :as g]
            [clojure.core.async :as a :refer [>!! >! <!! <! chan thread go timeout]])
  (:gen-class)
  (:import (java.net SocketException)))

(def stock-chan (chan 20))

(defn new-producer-chan [item-set n-items]
  (let [c (chan n-items)
        r (g/new-rand-gen 993061001)]
    (go
      (while true
        (>! c (g/random-from-collection item-set r))
        (<! (timeout 50))))
    c))

(defn new-input-chan [buffer-size]
  (let [c (chan buffer-size)]
    (go
      (while true
        (let [in (read-line)]
          (>! c in))))
    c))

(defn main-loop []
  (let [in (new-input-chan 0)]
      (while true
        (loop [acc 0]
          (let [n? (<!! in)]
            (println acc)
            (recur
              (if (g/numeric? n?)
                (+ acc (g/parse-int n?)) acc)))))))

(def ex-chan (chan))

(thread (while true
          (println (<!! ex-chan))))

(defmacro try-with-chan
  "Custom try block that catches SocketExceptions, and forwards them to the global ex-chan.
  Closes the channel in a finally block."
  [channel & body]
  `(try
     ~@body
     (catch SocketException se#
       (go (>! ex-chan se#)))
     (finally (a/close! ~channel))))

(defn async-task [task-id delay]
  (try-with-chan (chan)
      (thread
        (println (str task-id " - Thread started: " (.getId (Thread/currentThread))))
        (Thread/sleep delay)
        (println (str "\t\t" task-id " - Thread finished: " (.getId (Thread/currentThread)))))))

(defn -main []
  (doseq [n (range 2000)
          m (range n)]
      (async-task n 5000)
    (Thread/sleep 50))
  (read-line))

