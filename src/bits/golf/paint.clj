(ns bits.golf.paint
  (:require [quil.core :as q]))

(q/defsketch P ; Start a new drawing

             :mouse-dragged ; Attach mouse drag listener
             ; that draws a point at the current mouse location
             #(q/point (q/mouse-x) (q/mouse-y)))


(require '[quil.core :as q])(q/defsketch P :mouse-dragged #(q/point(q/mouse-x)(q/mouse-y)))
; -----
