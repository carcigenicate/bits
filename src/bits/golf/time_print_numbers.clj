(ns bits.golf.time-print-numbers)

(defn time-print []
  (let [t #(System/currentTimeMillis) ; Alias the time-getter to "t"
        start (t)] ; Record starting time
    (doseq [n (range -1000 1001)] ; Loop over the range...
      (println n)) ; ... printing the numbers

    (println (- (t) start)))) ; Then print the current time minus the starting time.

(let[t #(System/currentTimeMillis)s(t)](doseq[n(range -1e3 1001)](println n))(println(-(t)s)))