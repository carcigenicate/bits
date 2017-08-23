(ns bits.golf.destruct-loop)

(defn -main []
  (loop [n 1]
    (/ 1 n)
    (recur (dec n)))

  (iterate #(do(% 0)[])[0])())