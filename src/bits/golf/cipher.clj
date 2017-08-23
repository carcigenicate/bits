(ns bits.golf.cipher)

; http://codegolf.stackexchange.com/questions/100729/generate-a-cipher

; Input:
;The treasure is here
;2

;Output:
;T!0h32eF4t0irlkehma7ys#0u*&r*he!2iH^sB,h!@e0r$he

#_
(defn cipher [message n]
  (apply str
    (remove #(= \space %)
      (reduce #(apply str %1 %2
                 (for [_ (range n)]
                   (char (rand-nth (range 32 127)))))
              ""
              message))))

(defn cipher [message n]
  (apply str
    (remove #(= \space %)
        (mapcat #(apply str %
                     (for [_ (range n)]
                       (char (rand-nth (range 32 127)))))
                message))))

(defn c[m n](apply str(remove #(=\space %)(mapcat #(apply str %(for [_(range n)](char(rand-nth(range 32 127)))))m))))