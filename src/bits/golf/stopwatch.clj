(ns bits.golf.stopwatch)

(defn run-stopwatch []
  (let [running? (atom false)
        listener (.start (Thread. #(do (read-line) (swap! running? not))))
        pad #(if (> (count %) 1) % (str 0 %))]
    (loop [m 0
           s 0]
      (Thread/sleep 1000)
      (if @running?
        (let [s' (inc s)
              s' (if (> s' 59) 0 s')
              m' (if (= s' 0) (inc m) m)
              m' (if (> m' 59) 0 m')]
          (print (str "\r" (pad (str m')) \: (pad (str s')) (flush)))
          (recur m' s'))
        (recur m s)))))

(defn -main []
  (run-stopwatch))

