(ns bits.memoize)

(defn my-memoize [f]
  (let [arg-cache (atom {})]
    #_
    (add-watch arg-cache :debug
      (fn [k r o n] (println (str \+ (count n) \+))))
    (fn [& args]
      (let [mem-result (@arg-cache args)]
        (if (some? mem-result)
          mem-result
          (let [result (apply f args)]
            (do
              (swap! arg-cache #(assoc % args result))
              result)))))))