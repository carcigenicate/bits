(ns bits.golf.loader)

(defn -main []
                ; (map vector...) is how you zip in Clojure
  (doseq [[c i] (map vector (cycle "\\|/-") (range 101))]
    ; \r to erase the old line
    (print "\rloading" c i \%) (flush)
    (Thread/sleep (rand-int 751))))
#_
(doseq[[c i](map vector(cycle"\\|/-")(range 101))](print"\rloading"c i\%)(flush)(Thread/sleep(rand-int 751)))