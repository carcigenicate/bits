(ns bits.golf.eh)

(defn eh [a b]
  ; Create a range object representing A-B
  ; Need to inc(rease) b since the upper bound is exclusive
  (let [range-set (set
                    (if (<= a b)
                      (range a (inc b)) ; Ascending Range
                      (range b (inc a))))] ; Descending range
    ; Sets are functions that check for membership, returning falsey if the number isn't in it.
    (if (range-set (- a b))
      a
      b)))

#(if((set(if(<= % %2)(range %(inc %2))(range %2(inc %))))(- % %2))% %2)

; -----

(defn eh [a b]
  ; <= accepts any number of argments, and ensure all fall within the range
    (if (<= (min a b) (- a b) (max a b))
      a
      b))

#(if(<=(min % %2)(- % %2)(max % %2))% %2)