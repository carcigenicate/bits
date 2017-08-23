(ns bits.golf.pi-to-n)

(defn nth-pi-digit [n]
  (reduce
    (fn [sum k]
      (+ sum
         (*
           (/ 1 (Math/pow 16 k))
           (-
             (/ 4 (+ 1 (* 8 k)))
             (/ 2 (+ 4 (* 8 k)))
             (/ 1 (+ 5 (* 8 k)))
             (/ 1 (+ 6 (* 8 k)))))))
    0
    (range n)))

; Fail. Calculates PI to n digits

; -----

(defn nth-pi-digit [n]
  (let [b bigdec
        d #(.divide (b %) %2 (+ n 4) BigDecimal/ROUND_HALF_UP)
        m #(.multiply (b %) %2)
        a #(.add (b %) %2)
        s #(.subtract % %2)]
    (-
      (int
         (nth
           (str
             (reduce
               (fn [sum k]
                 (a sum
                       (m
                         (d 1 (.pow (b 16) k))
                         (s
                           (s
                             (s
                               (d 4 (a 1 (m 8 k)))
                               (d 2 (a 4 (m 8 k))))
                             (d 1 (a 5 (m 8 k))))
                           (d 1 (a 6 (m 8 k)))))))
               (bigdec 0)
               (map bigdec (range (inc n)))))
           (+ n 2)))
      48)))

(fn[n](let[b bigdec d #(.divide(b %)%2(+ n 4)BigDecimal/ROUND_HALF_UP)m #(.multiply(b %)%2)a #(.add(b %)%2)s #(.subtract % %2)](-(int(nth(str(reduce(fn[z k](a z(m(d 1(.pow(b 16)k))(s(s(s(d 4(a 1(m 8 k)))(d 2(a 4(m 8 k))))(d 1(a 5(m 8 k))))(d 1(a 6(m 8 k)))))))(bigdec 0)(map bigdec(range(inc n)))))(+ n 2)))48)))

; -----