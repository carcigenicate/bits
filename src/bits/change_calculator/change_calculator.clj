(ns bits.change-calculator.change-calculator)

(defn calc-change [amount denoms]
  (loop [remaining-change amount
         [denom & r-denoms] denoms
         acc {}]
    (let [can-make (int (Math/floor (/ remaining-change denom)))
          remaining-change' (- remaining-change
                               (* can-make denom))
          acc' (assoc acc denom can-make)]
      (if r-denoms
        (recur remaining-change' r-denoms acc')
        (if (zero? remaining-change')
          acc'
          (assoc acc :left remaining-change'))))))

