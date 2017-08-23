(ns bits.golf.hofstadter-q-sequence)

; a( n - a(n-1) ) + a( n - a(n-2) )

(defn a [n]
  (cond
    (< 0 n 3) 1 ; Return 1 if n is 1 or 2
    :else (+ (a (- n (a (dec n)))) ; Else, recurse 4 times and do some math
             (a (- n (a (- n 2)))))))

(defn a[n](cond(< 0 n 3)1 1(+(a(- n(a(dec n))))(a(- n(a(- n 2)))))))
