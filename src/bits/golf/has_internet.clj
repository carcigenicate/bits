(ns bits.golf.has-internet)

(defn internet? []
  (try (slurp "http://www.g.gl") 1 (catch Exception _ nil)))

#(try(slurp"http://to.")1(catch Exception _ nil))