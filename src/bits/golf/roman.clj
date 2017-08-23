(ns bits.golf.roman)

; ----- Not golf

(def sym-values {\I       1
                 \V       5
                 \X       10
                 \L       50
                 \C       100
                 \D       500
                 \M       1000
                 \i       1000 ; Lower case letters are non-standard notation.
                 \v       5000
                 \x       10000
                 \l       50000
                 \c       10000
                 \d       500000
                 \m       1000000})

(def macro-delimiter \$)

(defn higher-valued? [first-sym second-sym]
  #_(println \$ first-sym second-sym)
  (let [fs (sym-values first-sym)
        ss (sym-values second-sym)]
    (> (if fs fs 0)
       (if ss ss 0))))

(defn split-on-chunk [rest-syms]
  (loop [[sym & r-syms :as syms] rest-syms
         acc []]
    (let [l (last acc)]
      (if (higher-valued? l sym)
        [acc syms]
        (recur r-syms (conj acc sym))))))

(defn evaluate-chunk [chunk]
  (loop [[sym & r-syms :as syms] chunk
         acc-value 0
         last-sym nil]
    (let [sym-val (sym-values sym)]
      (if r-syms
        (recur r-syms (+ acc-value sym-val) sym)
        (if (higher-valued? sym last-sym)
          (- sym-val acc-value)
          (+ sym-val acc-value))))))

(defn from-roman [str-num]
  (loop [[_ & r-syms :as syms] str-num
         acc 0]
    (let [[chunk rest-syms] (split-on-chunk syms)
          value (evaluate-chunk chunk)
          total (+ value acc)]
      (if rest-syms
        (recur rest-syms total)
        total))))

(defmacro romanize [& body])

