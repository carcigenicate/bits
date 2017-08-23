(ns bits.golf.following-dialog
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(def width 500)
(def height 200)

(def top-bar-height 30)

(def b-width 200)
(def b-height 100)

(defn -main []
  (q/defsketch d
    :size [999 999]
    :setup (fn []
             ; Set the border color
             (q/stroke 0 0 0)

             ; Set the font size
             (q/text-font (q/create-font "" (* top-bar-height 1.3)))

             ; The initial state
             {:x 0 :y 0})

    :draw (fn [{x :x y :y}]
            (let [r q/rect ; Shortcut functions for brevity
                  f q/fill

                  ; The top-left coordinates of the window
                  window-x  (- x (/ width 2))
                  window-y (- y (/ height 2))]

              ; Wipe the previous screen
              (q/background 99 99 99)

              ; Blue top bar
              (f 0 0 255)
              (r window-x
                 window-y
                 width height)

              ; Grey window background
              (f 200 200 200)
              (r window-x
                 (+ window-y top-bar-height)
                 width (- height top-bar-height))

              ; Red top right "button"
              (f 255 0 0)
              (r (- (+ window-x width)
                    top-bar-height)
                 window-y
                 top-bar-height top-bar-height)

              ; The X
              (f 255 255 255)
              (q/text "X" (- (+ window-x width) top-bar-height)
                          (+ window-y top-bar-height))

              ; The main "button"
              (r (- x (/ b-width 2))
                 (- y (/ b-height 2))
                 b-width
                 b-height)))

    ; When the mouse is moved, set the current state to the event object, which
    ;  conveniently has :x and :y properties
    :mouse-moved (fn [_ e] e)

    ; Needed for ease of state management. May try to factor out.
    :middleware [m/fun-mode]))
