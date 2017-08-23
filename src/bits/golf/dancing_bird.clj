(ns bits.golf.dancing-bird
  (:gen-class))

; http://codegolf.stackexchange.com/questions/105237/bird-dancer

(defn dancing-bird [n]
  (let [birds (clojure.string/split "(>\")> <(\")> <(\"<) ^(\")v v(\")^ ^(\")^ v(\")v" #" ")
        r (rand-int 7)]
    (print "\r" (birds r))
    (flush)
    (Thread/sleep (* (if (> r 2) 100 200) n))
    (recur n)))

#(let[b(clojure.string/split"(>\")> <(\")> <(\"<) ^(\")v v(\")^ ^(\")^ v(\")v"#" ")r(rand-int 7)](print"\r"(b r))(flush)(Thread/sleep(*(if(> r 2)100 200)%))(recur %))

; -----

(defn dancing-bird-2 [n]
  (loop [m nil]
    (let [birds (clojure.string/split "(>\")> <(\")> <(\"<) ^(\")v v(\")^ ^(\")^ v(\")v" #" ")
          r (or m 1)]
      (print "\r" (birds r))
      (flush)
      (Thread/sleep (* (if (> r 2) 100 200) n))
      (recur (rand-int 7)))))

#(loop[m nil r(or m 1)](print"\r"((clojure.string/split"(>\")> <(\")> <(\"<) ^(\")v v(\")^ ^(\")^ v(\")v"#" ")r))(flush)(Thread/sleep(*(if(> r 2)100 200)%))(recur 1(rand-int 7)))


; -----

(defn -main [& args]
  (dancing-bird-2
    (Double/parseDouble
      (if-let [[n] args]
        n
        "1.0"))))