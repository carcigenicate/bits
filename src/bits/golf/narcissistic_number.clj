(ns bits.golf.narcissistic-number)

; A Narcissistic Number is a number which is the sum of its own digits,
;  each raised to the power of the number of digits.

(defn is-narcissistic []
  (let [n-str (read-line)
        parse #(Integer/parseInt (str %))
        pow #(int (Math/pow % (count n-str)))
        digits (map #(pow (parse %)) n-str)]
    (= (parse n-str) (reduce + 0 digits))))

(fn[](let[s(read-line)p #(Integer/parseInt(str %))](=(p s)(reduce + 0(map #(int(Math/pow(p %)(count s)))s)))))


(fn[n](= n(reduce + 0(map #(int(Math/pow(Integer/parseInt(str %))(count(str n))))(str n)))))