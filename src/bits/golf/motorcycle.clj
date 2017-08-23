(ns bits.golf.motorcycle)

; Expects ^ for shift-up, and V (or anything else) for shift down
; Returns a character representing the current gear
(defn shift [shift-str]
  ((vec "1N23456") ; 4. Then index the gear list with the calculated index, and return
   (reduce (fn [current-gear s] ; 1. Fold over the shift symbols
             (max 0 (min 6 ; 3. Clamp it to the range 0-6 so we don't overflow
                      ((if (= s \^) inc dec) ; 2. If the shift is up, increase, else decrease
                       current-gear))))
           1
           shift-str)))

#((vec "1N23456")(reduce(fn[c s](max 0(min 6((if(= s \^)inc dec)c))))1 %))


