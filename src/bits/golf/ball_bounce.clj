(ns bits.golf.ball-bounce
  (:require [quil.core :as q]))

(def width 2000)
(def height 1000)

(def font-size 150)

; Mutable state holding the properties of the ball. n and m are the directions on the x and y axis.
(def ball (atom {:x 200 :y 300 :n -1 :m 1}))

(q/defsketch b
  :size [width height] ; Window size

  :setup #(do
            (q/text-font (q/create-font "Arial" font-size)) ; Set the font
            (q/fill 255 255 255)) ; And the text color

  :draw
  #(let [speed 20
         ; Deconstruct the state
         {:keys [x y n m]} @ball
         next-x (+ x (* n speed))
         next-y (+ y (* m speed))

         ; I'm adding/subtracting the font-size so it stays in the window properly
         x-inbounds? (< 0 next-x (- width font-size))
         y-inbounds (< (+ 0 font-size) next-y height)]

     ; Wipe the screen so the ball doesn't smear
     (q/background 0 0 0)

     ; Reset the state
     (reset! ball
             {:x next-x
              :y next-y
              :n (if x-inbounds? n (* -1 n))
              :m (if y-inbounds m (* -1 m))})

     (q/text "O" (:x @ball) (:y @ball))))



