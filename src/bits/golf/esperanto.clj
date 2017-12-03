(ns bits.golf.esperanto)

(def syms "ĉĝĥĵŝŭĈĜĤĴŜŬ")

#_
(defn parse [^String esperanto]
  (loop [[c & rest-str] esperanto
         last-c nil
         acc ""]
    (if c
      (let [r]))))

(defn translate [^String esperanto]
  (reduce (fn [acc [f r]] (clojure.string/replace
                            acc ; Replace the translation so far by
                            (str f \x) ; adding a x after each character, search for it in the string,
                            (str r))) ; and replace it with a stringified accented char

          esperanto ; Before the reduction happens, the accumulator is the original string

          ; A map of replacements to be reduced over
          (partition 2"cĉgĝhĥjĵsŝuŭCĈGĜHĤJĴSŜUŬ")))


#(reduce(fn[a[f r]](clojure.string/replace a(str f\x)(str r)))%(partition 2"cĉgĝhĥjĵsŝuŭCĈGĜHĤJĴSŜUŬ"))

{\c\ĉ\g\ĝ\h\ĥ\j\ĵ\s\ŝ\u\ŭ\C\Ĉ\G\Ĝ\H\Ĥ\J\Ĵ\S\Ŝ\U\Ŭ}
(partition 2"cĉgĝhĥjĵsŝuŭCĈGĜHĤJĴSŜUŬ")

