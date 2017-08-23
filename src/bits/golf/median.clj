(ns bits.golf.median)

; Not submitted. Better Clojure answer already existed.

(defn median [ns]
  (let [sns (vec (sort ns))
        cns (count ns)
        hcns (/ cns 2)]
    (if (odd? cns)
      (sns (dec (int (Math/ceil hcns))))
      (/ (+ (sns (dec hcns)) (sns hcns)) 2))))


#(let[s(vec(sort %))c(count ns)h(/ c 2)](if(odd? c)(s(dec(int(Math/ceil h))))(/(+(s(dec h))(s h))2)))