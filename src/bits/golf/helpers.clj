(ns bits.golf.helpers)

(defn minify [code-str]
  (let [ib #(#{\(\)\[\]\{\}} %)]
    (loop [[f ^char s t :as c] code-str
           a (first c)]
      (if s
        (if (and (or (ib f)
                     (ib t))
                 (Character/isWhitespace s))
          (recur (rest c) (str a)))
        #_(recur (rest c)
               (if (and (or (ib f)
                            (ib t))
                        (Character/isWhitespace s))
                 (str a f)
                 (str a s)))
        a))))

; ast
; (defn comp-chain [chain-str] ())