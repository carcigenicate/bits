(ns bits.golf.sum-of-digits)

(defn sum-of-digits? [n]
  (let [ds (map #(- (int %) 48) (str n)) ; Stringify n, and parse each digit char of the string
        d-sum (* 2 (apply + ds))] ; Get the doubled sum
    (= 0 (rem n d-sum)))) ; Check if the remainder of (/ n sum) is 0

(fn[n](= 0(rem n(* 2(apply +(map #(-(int %)48)(str n)))))))