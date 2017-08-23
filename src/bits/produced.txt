13 21 
(let [u #(apply str %)
      a "./src/bits/self_modify.clj"
      b "./src/bits/produced.txt"
      p #(Integer/parseInt (u %))
      l (fn [v] (split-with #(Character/isDigit %) v))
      src (slurp a)
      [n [_ & r]] (l src)
      [m [_ & r]] (l r)
      n' (+ (p n) (p m))
      k (str (p m) " " n' " " (u r))]
  (println n m)
  (spit a k)
  (spit b k))