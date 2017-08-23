; http://codegolf.stackexchange.com/questions/106945/xor-two-bw-images/106968

(ns bits.golf.bit-xor-picts
  (:import [java.io File]
           [java.awt.image BufferedImage]
           [javax.imageio ImageIO]))

#_
(defn -main []
  (let [^BufferedImage a (ImageIO/read (File. "a.png"))
        ^BufferedImage b (ImageIO/read (File. "b.png"))]
    (doseq [i (range (.getHeight a))
            j (range (.getWidth a))]
      (.setRGB a j i
                (bit-xor (.getRGB a j i)
                         (.getRGB b j i))))
    (ImageIO/write a "png" (File. "c.png"))))

(defn -main []
  (let [^BufferedImage a (ImageIO/read (File. "a.png"))
        ^BufferedImage b (ImageIO/read (File. "b.png"))]
    (doseq [i (range (.getHeight a))
            j (range (.getWidth a))]
      (.setRGB a j i
               (bit-xor (.getRGB a j i)
                        (.getRGB b j i))))
    (ImageIO/write a "png" (File. "c.png"))))

#_
(ns s(:import[java.io File][java.awt.image BufferedImage][javax.imageio ImageIO]))#_(defn -main[](let[a(ImageIO/read(File."a.png"))](doseq[i(range(.getHeight a))j(range(.getWidth a))](.setRGB a j i(bit-xor(.getRGB a j i)(.getRGB(ImageIO/read(File."b.png")) j i))))(ImageIO/write a"png"(File."c.png"))))

