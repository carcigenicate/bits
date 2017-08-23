(ns bits.golf.dont-give-5)

(defn count-minus-5 [mi ma]
  (count ; Get the length of the resulting list
    ; Comprehension over the range mi(n) to ma(x).
    (for [n (range mi (inc ma))
          ; Only allow the number when every digit isn't a 5.
          :when (every? #(not= \5 %) (str n))]
      n)))

(fn[i a](count(for[n(range i(inc a)):when(every? #(not=\5 %)(str n))]n)))

; -----

(defn count-minus-5 [mi ma]
  (count ; Get the length of the resulting list
    ; Select all numbers where every digit is not a 5
    (filter #(every? (fn [digit] (not= \5 digit)) (str %))
            ; In the range from mi(n) to ma(x)
            (range mi (inc ma)))))

(fn[i a](filter #(every?(fn[c](not= \5 c))(str %))(range i(inc a))))

(fn[i a](count(filter #(every?(fn[d](not=\5 d))(str %))(range i(inc a)))))



