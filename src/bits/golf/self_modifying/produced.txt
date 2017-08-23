4660046610375530309 7540113804746346429 
(let [u #(apply str %)
      a "./src/bits/golf/self_modifying/wip.clj"
      b "./src/bits/golf/self_modifying/produced.txt"
      p #(Long/parseLong (u %))
      l (fn [v] (split-with #(Character/isDigit %) v))
      src (slurp a)
      [n [_ & r]] (l src)
      [m [_ & r]] (l r)n' (+ (p n) (p m))
      k (str (p m) " " n' " " (u r))]
  (println n')
  (spit b k)
  (spit a k))

