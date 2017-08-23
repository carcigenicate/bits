0 1 ; Starting numbers
(let [; The first 4 entires are shortcuts to functions and data that are used more than once
      u #(apply str %) ; Turns a list into a string
      a "./src/golf/self_modifying/v1.clj" ; Current location
      p #(Integer/parseInt (u %)) ; Integer parsing shortcut
      ; Used to split a string on digits to parse them out
      l (fn [v] (split-with #(Character/isDigit %) v))
      src (slurp a) ; Get the source
      [n [_ & r]] (l src) ; Use deconstructuring to grab the first number
      [m r] (l r) ; Same as above, grabbing the second number
      n' (+ (p n) (p m)) ; Parse the 2 numbers, and add them
      ; Put everything back together, only this time with the new numbers
      k (str (p m) " " n' (u r))]
  (println n') ; Print the new number
  (spit a k)) ; Overwrite the old source

0 1(let[u #(apply str %)a"./src/s.clj"p #(Long/parseLong(u %))l(fn[v](split-with #(Character/isDigit %)v))c(slurp a)[n[_ & r]](l c)[m r](l r)b(+(p n)(p m))](println b)(spit a(str(p m)" "b" "(u r))))

