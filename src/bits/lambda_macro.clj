(ns bits.lambda-macro)

(defn arg-list
  "Returns a lazy-seq of (%, %2, %3, ...) strings."
  ([] (arg-list 1))
  ([n] (lazy-seq
         (cons (str "%" (if (> n 1) n))
               (arg-list (inc n))))))

(defmacro lambda
  "Use as you would #(). (map (lambda (* % 2)) [1, 2, 3])"
  [& body]
  (let [arg-syms (->> (arg-list) (take 5) (mapv symbol))]
    `(fn [& ~arg-syms]
       ~@body)))

(defmacro vec-f [& elems]
  `(lambda (vec ~(vec elems))))

(defn example []
  (mapv (lambda (* % %2)) [1 2 3] [1 5 9])
  (mapv (vec-f (inc %) (* % %2)) [1 2 3] [1 5 9]))