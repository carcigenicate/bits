(ns bits.golf.ceee)

(defn ceee [[first-char & rest-phrase] letter] ; Deconstruct the first letter off right from the parameter list.
  (loop [[next-char & rest-remaining :as r] rest-phrase ; Deconstruct the remaining string apart
         acc first-char] ; Keep track of the letters to show at the start
    (println (str acc (apply str r))) ; Print the prefix accumulator, then the remaining phrase
    (if rest-remaining ; Recur if there's part of the phrase left
      (recur (if (= next-char letter)
               (rest rest-remaining)
               rest-remaining)
             (if (= next-char letter)
               (str acc next-char)
               acc)))))

(fn[[f & p]l](loop[[n & m :as r]p a f](println(str a(apply str r)))(if m(recur(if(= n l)(rest m)m)(if(= n l)(str a n)a)))))
