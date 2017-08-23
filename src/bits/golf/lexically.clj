(ns bits.golf.lexically)

; Ended up being a longer version of Niko's answer
#_
(defn lex? [s]
 (let [n (map int s)
       [g h] n
       o (if (and h (<= g h)) <= >=)]
   (apply o n)))

#(if(= 1(count %))1(let[n(map int %)[g h]n](apply(if(<= g h)<= >=)n)))

; -----
#_
(defn lex? [s]
  (let [sorted (apply str (sort s))
        reversed (clojure.string/reverse sorted)]
    (or (= s sorted) (= s reversed))))

#(let[o(apply str(sort %))r(clojure.string/reverse o)](or(= % o)(= % r)))

(defn lex? [s]
  ; (apply str ...) basically just turns a list into a string
  (let [sorted (apply str (sort s))
        reversed (apply str (reverse sorted))]
    (or (= s sorted) (= s reversed))))

#(let[s(apply str(sort %))](or(= % s)(= %(apply str(reverse s)))))
