(ns bits.golf.typing-simulator)

(defn typer [input]
                      ; (map vector... is generally how you zip lists in Clojure
  (doseq [[chr delay] (map vector input (cycle [100 100 500]))]
    (Thread/sleep delay)
    (print chr) (flush)))

#(doseq[[c d](map vector %(cycle[100 100 500]))](Thread/sleep d)(print c)(flush))
