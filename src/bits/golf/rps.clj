(ns bits.golf.rps)

(defn rps-scorer [plays]
  (let [[[p1] [_ p2]] (split-with #(not= \space %) plays) ; Parse out first letters using destructuring
        d (- (int p1) (int p2))] ; And find the difference of their numeric values
    (cond (#{-1 -2 3} d) 1 ; If it's a winning difference, return 1, else
          (#{1 2 -3} d) -1 ; If it's a losing difference, return 0, else
          :e 0))) ; return 0, because they're the same.

(fn[s](let[[[p1][_ p2]](split-with #(not=\space %)s)d(-(int p1)(int p2))](cond(#{-1 -2 3}d)1(#{1 2 -3}d)-1 :e 0)))

; -----

(defn rps-scorer [[p1] [p2]] ; Deconstruct out the first letters of each word
  (let [d (- (int p1) (int p2))] ; And find the difference of their numeric values
    (cond (#{-1 -2 3} d) 1 ; If it's a winning difference, return 1, else
          (#{1 2 -3} d) -1 ; If it's a losing difference, return -1, else
          :else 0))) ; return 0, because they're the same.

#(let[d(-(int %)(int %2))](cond(#{-1 -2 3}d)1)(#{1 2 -3}d)-1 0 0)

(fn[[p][q]](let[d(-(int p)(int q))](cond(#{-1 -2 3}d)1(#{1 2 -3}d)-1 0 0)))
; -----

(defn test-rps []
  (let [opts ["Rock" "Paper" "Scissors"]
        gen'd
        (for [p1 opts
              p2 opts]
          [p1 p2 (rps-scorer p1 p2)])]
    (clojure.pprint/pprint gen'd)))