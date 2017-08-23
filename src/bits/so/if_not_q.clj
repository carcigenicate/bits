(ns bits.so.if-not-q)

(defmacro my-if-not [test then else]
  `(if (not ~test) ~then ~else))

(defmacro my-if-not-2 [test then else]
  `(if ~test ~else ~then))

(defn test-macros [tests]
  (doseq [test tests]
    (let [r1 (my-if-not test :then :else)
          r2 (my-if-not-2 test :then :else)]
      (println (if-not (= r1 r2) \!) test \: r1 r2))))