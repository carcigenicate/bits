(ns bits.image.color-encode.main
  (:require [helpers.general-helpers :as g]
            [bits.image.color-encode.conversion-helpers :as ch]
            [bits.image.color-encode.general-helpers :as gh])

  (:import [java.io File]
           [java.awt Graphics2D Color]
           [java.awt.image BufferedImage]
           [javax.imageio ImageIO]))

(def ^String image-output-extention "png")

(defn encode ^BufferedImage [^String message]
  (let [filt-msg (gh/filter-bad-chars message)
        message-length (count filt-msg)
        image (gh/create-new-image message-length (int (Math/sqrt message-length)))]
    (ch/draw-message image filt-msg)
    image))

(defn decode ^String [^BufferedImage image]
  (let [message (ch/read-image image)]
    message))

(defn save-image [^BufferedImage image ^String path]
  (ImageIO/write image image-output-extention (File. path)))

(defn load-image ^BufferedImage [^String path]
  (ImageIO/read (File. path)))

(defn save-message [^String message ^String path]
  (spit path message))

(defn load-message ^String [^String path]
  (slurp path))

(defn -main [& [^String command ^String input-path ^String output-path]]
  (case (first command)
    \e
    (let [message (load-message input-path)
          encoded-image (encode message)]
      (save-image encoded-image output-path))

    \d
    (let [image (load-image input-path)
          decoded-message (decode image)]
      (println decoded-message)
      (save-message decoded-message output-path))

    (throw (RuntimeException.
             (str \" command \" " is an invalid command."
                  "\n\tTo encode a message, type \"encode [INPUT PATH] [OUTPUT PATH]\""
                  "\n\tTo decode a message, type \"decode [INPUT PATH] [OUTPUT PATH]\"")))))


