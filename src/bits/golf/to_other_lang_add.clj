(ns bits.golf.to-other-lang-add)

; a => a + n

(defn emit-inc-f [n]
  (str "a=>a+" n))