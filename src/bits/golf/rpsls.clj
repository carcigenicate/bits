(ns bits.golf.rpsls)

(defn decide [& moves] ; Using varargs so I don't need to duplicate the steps.
  (let [[p1 p2] (map (comp #(apply + %) ;   and sum them.
                           #(map int %) ;  turn them to ints...
                           #(take 2 %)) ; Take the first 2 chars...
                     moves)
        d (- p1 p2)]

    (cond
      (= d 0) ; A tie
      0

      (#{5 -16 12 -14 13
         1 4 -18 2 11} d) ; P1 Wins
      1

      :else ; P2 Wins
      2)))

(defn decide2 [& moves] ; Using varargs so I don't need to duplicate the steps.
  ; Pop the first 2 chars of each string, convert them to their ASCII code, and sum them.
  (let [[p1 p2] (map #(apply + (map int (take 2 %))) moves)
        d (- p1 p2)]

    (cond
      (= d 0) ; A tie
      0

      (#{5 -16 12 -14 13
         1 4 -18 2 11} d) ; P1 Wins
      1

      :else ; P2 Wins
      2)))

(fn[& m](let[[p q](map #(apply +(map int(take 2 %)))m)d(- p q)](cond(= d 0)0(#{5 -16 12 -14 13 1 4 -18 2 11}d)1 1 2)))