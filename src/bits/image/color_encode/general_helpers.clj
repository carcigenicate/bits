(ns bits.image.color-encode.general-helpers
  (:import (java.awt.image BufferedImage)))

(defn next-position [[x y :as current-position] image-width]
  (let [raw-next-x (inc x)
        over-width? (>= raw-next-x image-width)
        next-x (if over-width? 0 raw-next-x)
        next-y (if over-width? (inc y) y)]
    [next-x next-y]))

(defn create-new-image ^BufferedImage [message-length image-width]
  (let [image-height (int (inc (/ message-length image-width)))]
    (BufferedImage. image-width image-height BufferedImage/TYPE_INT_ARGB)))


(defn filter-bad-chars ^String [^String msg]
  msg
  #_(apply str
      (filter #(<= 32 (int %) 127) msg)))