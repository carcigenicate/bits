(ns bits.golf.interference.interference
  (:require [quil.core :as q]))

; Canvas size
(def width 1800)
(def height 900)

(defn -main [n]
  (let [col-width (/ width (- (* n 2) 1))
        ; The left-most x of each column
        col-starts (range 0 width col-width)

        ; The columns that need to be drawn. Need "vec" so I can index it later.
        active-cols (vec (take-nth 2 col-starts))

        ; Function taking a decimal percentage, and returning whether or not it's satisfied.
        ; (chance? 0.5) would be used to simulate a coin toss.
        chance? (fn [perc] (< (rand) perc))

        ; Function that returns a random int between a and b
        r-int (fn [a b] (+ a (rand-int (- b a))))

        ; Generates index pairs for each complimentary column.
        indices (for [i (range (int (/ n 2)) -1 -1)]
                  [i (- n 1 i)])

        ; Zips each index pair from above with the chance that it will be" chosen"
        zipped-perc (for [[j i] (map vector (range 1 (inc (count indices))) indices)]
                      [(/ 1 (Math/pow 2 j)) i])]

    ; Animation boilerplate
    (q/defsketch Interference
      :size [width height]
      ; For the GIF. Not included in the count of golfed code.
      :setup (fn [] (q/background 0 0 0))
      :draw
      ; The animation loop. It contains a loop over each complimentary column. It tries each column pair starting
      ;  from the middle, and works outward. Once it picks a pair of columns, it randomly chooses one of them.
      #(loop [[[p i] & r] zipped-perc]
         (when p
           ; Pick this column?
           (if (chance? p)
             ; Pick one of the column pairs
             (let [col (active-cols (rand-nth i))]
               ; Set the coloring and dot size
               (q/stroke 255 255 255)
               (q/stroke-weight 5)
               ; And finally draw the dot
               (q/point (r-int col (+ col col-width))
                        (r-int 0 height)))

             ; If the column wasn't chosen, loop again to try the next one
             (recur r)))))))