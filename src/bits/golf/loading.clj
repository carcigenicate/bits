(ns bits.golf.loading
  (:gen-class))
#_
(defn -main []
  (print "Loading...")
  (doseq [c (cycle "\\|/-")]
    (print (str "\b" c))
    (flush)
    (Thread/sleep 250)))

(defn -main []
  (do(print"Loading... -")(doseq[c(cycle"\\|/-")](print(str"\b"c))(flush)(Thread/sleep 250))))

#(do(print"Loading... -")(doseq[c(cycle"\\|/-")](print(str"\b"c))(flush)(Thread/sleep 250)))



