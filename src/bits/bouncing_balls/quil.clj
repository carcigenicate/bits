(ns bits.bouncing-balls.quil
  (:require [helpers.general-helpers :as g]
            [quil.core :as q]
            [quil.middleware :as m]))

(def width 1500)
(def height 1000)

(def default-speed 30)

(def ball-color [0 0 0])
(def background-color [255 255 255])

(def ball-size 70)

; Needed so balls don't become "embedded" in the right/lower boundries
(def ball-safe-width (- width ball-size))
(def ball-safe-height (- height ball-size))

(def rand-gen (g/new-rand-gen 993061001))

(defrecord Ball [position velocity])

(defrecord State [balls])

(defn xy-inbounds?
  "Returns a pair of bools representing [x-inbounds? y-inbounds?]"
  [x y]
  [(< 0 x ball-safe-width)
   (< 0 y ball-safe-height)])

(defn next-1D-position [current velocity speed]
  (+ current (* speed velocity)))

(defn next-position [x y x-vel y-vel speed]
  [(next-1D-position x x-vel speed)
   (next-1D-position y y-vel speed)])

(defn next-velocities [x y x-vel y-vel]
  (let [[x-in? y-in?] (xy-inbounds? x y)]
    [(if x-in? x-vel (- x-vel))
     (if y-in? y-vel (- y-vel))]))

(defn advance-ball [ball speed]
  (let [{[x y] :position [x-vel y-vel] :velocity} ball
        [next-x next-y]  (next-position x y x-vel y-vel speed)
        [next-vel-x next-vel-y] (next-velocities next-x next-y x-vel y-vel)]
    (->Ball [next-x next-y] [next-vel-x next-vel-y])))

(defn random-ball [rand-gen]
  (let [rand-pos #(g/random-int 0 % rand-gen)
        rand-vel #(g/random-double -1 1 rand-gen)
        x-vel (rand-vel)
        y-vel (* (- 1 (Math/abs ^double x-vel)))
        y-vel' (if (g/random-boolean rand-gen) y-vel (- y-vel))]
    (->Ball [(rand-pos ball-safe-width) (rand-pos ball-safe-height)]
            [x-vel y-vel'])))

(defn random-balls [n-balls rand-gen]
  (mapv
    (fn [_] (random-ball rand-gen))
    (range n-balls)))

(defn setup-state []
  (->State (random-balls 3 rand-gen)))

(defn update-state [state]
  (update state :balls
          (partial
            map #(advance-ball % default-speed))))

(defn draw-ball [^Ball {[x y] :position}]
  (q/with-fill ball-color
               (q/ellipse x y ball-size ball-size)))


(defn draw-state [state]
  (apply q/background background-color)

  (let [balls (:balls state)]
    (doseq [ball balls]
      (draw-ball ball))))




(q/defsketch Bouncing-Balls
             :size [width height]

             :setup setup-state
             :update update-state
             :draw draw-state

             :middleware [m/fun-mode])


