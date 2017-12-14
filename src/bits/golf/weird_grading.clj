(ns bits.golf.weird-grading)

(defn weird-grading [n-days]
  (cond
    (<= 0 n-days 13) \F
    (<= 14 n-days 170) \E
    (<= 171 n-days 180) \D
    (<= 181 n-days 294) \C
    (<= 295 n-days 300) \B
    :else \A))

#(cond(<= 0% 13)\F(<= 14% 170)\E(<= 171% 180)\D(<= 181% 294)\C(<= 295% 300)\B 1\A)