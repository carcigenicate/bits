(ns bits.image.color-encode.conversion-helpers
  (:require [helpers.general-helpers :as g]
            [bits.image.color-encode.general-helpers :as gh])
  (:import [java.awt Color]
           (java.awt.image BufferedImage)))

(def min-char-code 0)
(def max-char-code "inclusive" 127)

(def color-base 256)

(def total-colors (int (Math/pow color-base 3)))
(def color-code-mult (int (inc (/ total-colors (- max-char-code min-char-code)))))

(def starting-color [0 0 0])

(defn base-10-to-base [base n]
  (let [places (reverse (take-while #(< % n)
                           (map #(Math/pow base %) (range))))]
    (loop [m n
           [place & rest-places] places
           acc []]
      (if place
        (let [place-value (int (/ m place))
              remainder (rem m place)]
          (recur remainder
                 rest-places
                 (conj acc place-value)))
        acc))))

(defn base-10-to-base2 [base n]
  (let [places (reverse (take-while #(< % n)
                          (map #(Math/pow base %) (range))))]
    (loop [[place & rest-places] places
           acc []]
      (if place
        (let [place-value (rem (int (/ n place)) base)]
          (recur rest-places
                 (conj acc place-value)))
        acc))))

(defn channels-to-rgb [[r g b :as color]]
  (.getRGB (Color. ^long r ^long g ^long b)))

(defn nth-color [n]
  (base-10-to-base color-base n))

(defn pad-color [[a b :as unpadded-color]]
  (case (count unpadded-color)
    0 [0 0 0]
    1 [0 0 a]
    2 [0 a b]
    unpadded-color))

(defn color-of-char [chr]
  (let [code (- (int chr) min-char-code)]
    (pad-color
      (nth-color
        (* code color-code-mult)))))

(defn draw-pixel [image x y [r g b :as color]]
  (.setRGB image x y
           (channels-to-rgb [r g b])))

(defn draw-message [^BufferedImage image ^String message]
  (println (str (.getWidth image) "x" (.getHeight image)))
  (loop [[x y :as position] [0 0]
         [cur-char & rest-message] message]
    (if cur-char
      (let [color  (color-of-char cur-char)]
        (do
          #_(println (str "Writing " color " to " x "," y))
          (draw-pixel image x y color)
          (recur (gh/next-position position (.getWidth image))
                 rest-message))))))


; ---------- Decoding ----------

(defn rgb-to-channels [^long raw]
  (let [col (Color. raw)]
    [(.getRed col)
     (.getGreen col)
     (.getBlue col)]))

(defn base-x-to-base-10 [base n-coll]
  (let [place (map #(Math/pow base %) (range (dec (count n-coll)) -1 -1))]
    (reduce (fn [acc [place-value unit-value]]
              (+ acc
                 (* place-value unit-value)))
            0
            (map vector place n-coll))))

(defn char-of-color [color]
  (let [base-10 (base-x-to-base-10 color-base color)
        scaled (/ base-10 color-code-mult)
        offset (+ scaled min-char-code)]
    (char offset)))

(defn read-pixel [^BufferedImage image x y]
  (let [raw-color (.getRGB image x y)
        [r g b :as color] (rgb-to-channels raw-color)]
    color))

(defn read-image [^BufferedImage image]
  (let [[w h] [(.getWidth image) (.getHeight image)]]
    (loop [[x y :as position] [0 0]
           message ""]
      (if (< y h)
        (let [pixel-channels (read-pixel image x y)
              chr (char-of-color pixel-channels)]
          (recur (gh/next-position position w) (str message chr)))
        message))))



; ---------- Tests ----------

(defn test-chars-encode-decode []
  (let [passed (atom true)]
    (doseq [c (map char (range min-char-code (inc max-char-code)))]
      (let [chr c
            col (color-of-char chr)
            chr' (char-of-color col)]
        (println chr col chr')
        (if (not= chr chr')
          (reset! passed false))))
    (println
      (if @passed "Passed"
                  "!!!!!Failed!!!!! See above!!!!!"))))
