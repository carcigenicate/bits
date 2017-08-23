(ns bits.golf.checkbox
  (:require [quil.core :as q]))
#_
    (q/defsketch a
                 :size [1000 1000] ; Set sketch size
                 :setup #(do nil) ; Init the state with nil (smallest falsy value)
                 :draw #(apply q/background (if % [0 0 0] [255 0 0])) ; Draw the background color to be either black or red dependig on the toggle state; %.
                 :mouse-clicked #(do %2(not %)) ; Negate the state on mouse click
                 :middleware [m/fun-mode]) ; Needed to avoid global state. Trying to factor out.

#_
    (ns c(:require[quil.core :as q][quil.middleware :as m]))#_(q/defsketch a :size[9 9]:setup #(do nil):draw #(apply q/background(if %[0 0 0][255 0 0])):mouse-clicked #(do %2(not %)):middleware [m/fun-mode])

; -----

; Mutable global variable to hold the toggle state
(def a (atom nil))

(q/defsketch b
             ; Draw the background color to be either black or red depending on the toggle state.
             :draw #(apply q/background (if @a [0] [99]))

             ; Negate the state on mouse click
             :mouse-clicked #(swap! a not))

#_
(ns c(:require[quil.core :as q]))#_(def a(atom nil))(q/defsketch b :draw #(q/background(if@a 0 99)):mouse-clicked #(swap! a not))
