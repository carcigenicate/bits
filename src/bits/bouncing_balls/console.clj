(ns bits.bouncing-balls.console
  (:require [helpers.general-helpers :as g])
  (:gen-class))

; Fail. move-cursor doesn't work. Need a way

(defrecord Ball [position velocity])

(def width 40)
(def height 20)

(def default-speed 1)

(def ball-char "O")

; http://visibletrap.blogspot.ca/2015/09/how-to-clear-terminal-screen-in-clojure.html

(defn move-cursor-to_broken [x y]
  (print (str (char 27) "["y";"x"H")))

(defn move-cursor-to [x y]
  (let [r #(apply str (repeat % %2))]
    (print (str (r y \newline)
                (r x \space)))))

(defn clear-screen []
  (print (str (char 27) "[2J"))
  (flush))

(defn print-ball [ball display-str]
  (let [[x y] (:position ball)]
    (clear-screen)
    (move-cursor-to x y)
    (print display-str)
    (flush)))

(defn xy-inbounds?
  "Returns a pair of bools representing [x-inbounds? y-inbounds?]"
  [x y]
  [(< 0 x width)
   (< 0 y height)])

(defn next-1D-position [current velocity speed]
  (+ current (* speed velocity)))

(defn next-position [x y x-vel y-vel speed]
  [(next-1D-position x x-vel speed)
   (next-1D-position y y-vel speed)])

(defn next-velocities [x y x-vel y-vel]
  (let [[x-in? y-in?] (xy-inbounds? x y)]
    [(if x-in? x-vel (* -1 x-vel))
     (if y-in? y-vel (* -1 y-vel))]))

(defn advance-ball [ball speed]
  (let [{[x y] :position [x-vel y-vel] :velocity} ball
        [next-x next-y]  (next-position x y x-vel y-vel speed)
        [next-vel-x next-vel-y] (next-velocities next-x next-y x-vel y-vel)]
    (->Ball [next-x next-y] [next-vel-x next-vel-y])))

(defn -main []
  (loop [ball (->Ball [10 10] [1 1])]
    (print-ball ball ball-char)

    (Thread/sleep 500)

    (recur
      (advance-ball ball default-speed))))