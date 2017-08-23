(ns bits.thread-pool)

(defrecord Thread-Pool [active-threads idle-threads])

(defn new-thread [thread-n]
  (Thread.))

(defn new-thread-pool
  ([n-threads]
   (let [threads (map #(Thread. nil nil (str "pool-thread-" %)))
         thread-map (into {}
                      (map (fn [^Thread t] [(.getId t) t]) (range n-threads)))]
     (Thread-Pool
       (ref {})
       (ref thread-map)))))
#_
(defn run [pool task]
  (let [{act-t :active-threads idl-t :idle-threads} pool]
    (dosync
      (let [[t-id t] (first @idle-t)]
        (i (nil? t-id))))))

