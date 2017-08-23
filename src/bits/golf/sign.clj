(ns bits.golf.sign)

; http://codegolf.stackexchange.com/questions/103822/output-the-sign

(defn sign [n]
  (cond
    (> n 0) 1
    (< n 0) -1
    :else 0))

#(cond(> % 0)1(< % 0)-1 0 0)