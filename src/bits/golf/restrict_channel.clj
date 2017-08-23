(ns bits.restrict-channel
  (:import (java.awt.image BufferedImage)
           (clojure.lang Keyword)
           (javax.imageio ImageIO)
           (java.io File)
           (java.awt Color)))

(import '(java.awt.image BufferedImage)'(clojure.lang Keyword)'(javax.imageio ImageIO)'(java.io File)'(java.awt Color))

(defn restrict-channel
  "Accepts a BufferedImage and a index between 0 and 2 (inclusive).
  Removes color from all channels EXCEPT the channel indicated by color-i.
  color-i of 0 = keep red
  color-i of 1 = keep green
  color-i of 2 = keep blue"
  [^BufferedImage image ^long color-i]
  (let [; Create a vector containing the indices the the channels to throw away
        chan-i-to-zero (vec (disj (set (range 3)) color-i))

        ; Turn a number representing RGB into a triplet representing [R G B]
        channels #(let [c (Color. %)]
                    [(.getRed c) (.getGreen c) (.getBlue c)])

        ; Throws away the channels not indicated by color-i
        ; Works by overwriting the original color triplet by grabbing the indices in chan-i-to-zero
        ; Could probably shorten using some "apply" magic
        zero-channels #(assoc (channels %) (chan-i-to-zero 0) 0
                                           (chan-i-to-zero 1) 0)]

    ; Loop over each pixel
    (doseq [y (range (.getHeight image))
            x (range (.getWidth image))]
      ; Grab the current color...
      (let [cur-color (.getRGB image x y)]
        ; ... setting it to stripped color triplet returned by zero-channels
        (.setRGB image x y
                   ; This "apply" part is just applying the triplet to the Color constructor
          (.getRGB (apply #(Color. % %2 %3) (zero-channels cur-color))))))
    ; Save the result to file
    (ImageIO/write image "jpg" (File. "./o.jpg"))))
#_
(import '(java.awt.image BufferedImage)'(clojure.lang Keyword)'(javax.imageio ImageIO)'(java.io File)'(java.awt Color))#_(fn[i y](let[q(vec(disj(set(range 3))y))a #(let[c(Color. %)][(.getRed c)(.getGreen c)(.getBlue c)])z #(assoc (a %)(q 0)0(q 1) 0)](doseq[y(range(.getHeight i))x (range(.getWidth i))](let[c(.getRGB i x y)](.setRGB i x y(.getRGB(apply #(Color. % %2 %3)(z c))))))(ImageIO/write i "jpg" (File. "./o.jpg"))))

; -----

(defn restrict-channel2
  "Accepts a BufferedImage and a index between 0 and 2 (inclusive).
  Removes color from all channels EXCEPT the channel indicated by color-i.
  color-i of 0 = keep red
  color-i of 1 = keep green
  color-i of 2 = keep blue"
  [^BufferedImage image ^long color-i]
  (let [; Turn a number representing RGB into a triplet representing [R G B]
        channels #(let [c (Color. %)]
                    [(.getRed c) (.getGreen c) (.getBlue c)])

        ; Create a new triplet that only contains color in the color-i channel
        zero-channels #(assoc [0 0 0] color-i ((channels %) color-i))]

    ; Loop over each pixel
    (doseq [y (range (.getHeight image))
            x (range (.getWidth image))]

      ; Grab the current color...
      (let [cur-color (.getRGB image x y)]

        ; ... setting it to stripped color triplet returned by zero-channels
        (.setRGB image x y

                          ; This "apply" part is just applying the stripped triplet to the Color constructor
                 (.getRGB (apply #(Color. % %2 %3) (zero-channels cur-color))))))

    ; Save the result to file
    (ImageIO/write image "jpg" (File. "./o.jpg"))))



(import '(java.awt.image BufferedImage)'(javax.imageio ImageIO)'(java.io File)'(java.awt Color))(fn[p i](doseq[y(range(.getHeight p))x(range(.getWidth p))](.setRGB p x y(.getRGB(apply #(Color. % %2 %3)(#(let[c(Color. %)](assoc [0 0 0]i(([(.getRed c)(.getGreen c)(.getBlue c)]%)i)))(.getRGB p x y))))))(ImageIO/write p"jpg"(File."./o.jpg")))
