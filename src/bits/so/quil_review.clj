(ns bits.so.quil-review
  (:require [quil.core :as q]))

(def grid-size 10)

(def grid-offset-x-mod 10)
(def grid-offset-y-mod 10)

(def grid-offset-x 10)
(def grid-offset-y 10)

(defn draw-number [& args])

#_
(defmacro jsRound [expr]
  `(.round js/Math expr))

(defmacro roundM [expr]
  `(Math/round ^double ~expr))

(doseq [x (range (roundM (/ (q/width) grid-size)))]
  (q/line (+ grid-offset-x-mod (* x grid-size)) 0
          (+ grid-offset-x-mod (* x grid-size)) (q/height))
  (draw-number :horiz x grid-offset-x grid-offset-y))

(doseq [y (range (roundM (/ (q/height) grid-size)))]
  (q/line 0 (+ grid-offset-y-mod (* y grid-size))
          (q/width) (+ grid-offset-y-mod (* y grid-size)))
  (draw-number :vert y grid-offset-x grid-offset-y))

(defn round [n]
  (.round js/Math n))

(defn draw-lines [window-dimension-length dimension]
  (doseq [d (range (round (/ window-dimension-length grid-size)))]
    (q/line 0 (+ grid-offset-y-mod (* d grid-size)) (q/width) (+ grid-offset-y-mod (* y grid-size)))
    (draw-number :vert d grid-offset-x grid-offset-y)))


(dorun (for [y (range (.round js/Math (/ (q/height) grid-size)))]
         (do (q/line 0 (+ grid-offset-y-mod (* y grid-size)) (q/width) (+ grid-offset-y-mod (* y grid-size)))
             (draw-number :vert y grid-offset-x grid-offset-y))))