(ns bits.image.tests
  (:require [helpers.general-helpers :as g])
  (:import [java.io File]
           [java.awt Graphics2D Color]
           [java.awt.image BufferedImage]
           [javax.imageio ImageIO]))

(defn get-color-channels-at [^BufferedImage picture x y]
  (let [raw (.getRGB picture x y)
        col (Color. raw)]
    [(.getRed col)
     (.getGreen col)
     (.getBlue col)]))

(defn channels-to-rgb [[r g b :as color]]
  (.getRGB (Color. ^long r ^long g ^long b)))

(defn to-grey [[r g b :as color]]
  (let [avg (int (/ (reduce + 0 color) 3))]
    [avg avg avg]))

(defn negative-color [[r g b :as color]]
  (mapv #(- 255 %) color))

(defn make-monochrome [^String file-path]
  (let [^BufferedImage pic (ImageIO/read (File. file-path))]
    (doseq [y (range (.getHeight pic))
            x (range (.getWidth pic))]
      (let [color (get-color-channels-at pic x y)
            [^long g] (to-grey color)]
        (.setRGB pic x y
          (channels-to-rgb [g g g]))))

    (ImageIO/write pic "png" (File. "a-2.png"))))

(defn make-negative [^String file-path]
  (let [^BufferedImage pic (ImageIO/read (File. file-path))]
    (doseq [y (range (.getHeight pic))
            x (range (.getWidth pic))]
      (let [color (get-color-channels-at pic x y)
            neg (negative-color color)]
        (.setRGB pic x y
          (channels-to-rgb neg))))

    (ImageIO/write pic "png" (File. "a-2.png"))))

