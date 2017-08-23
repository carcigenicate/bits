; Failed. Must maintain indentation

(def dummy 1) ; A dummy def so I can easily get the current file.
(let [src (slurp (java.io.File. (:file (meta #'dummy)))) ; Get the current file path, then "slurp" the source
      chunks (clojure.string/split src #"\s+")]

  (clojure.pprint/pprint chunks)

  (quil.core/defsketch a
               :key-pressed #(do
                               (print (str (rand-nth chunks) \ ))
                               (flush))))
