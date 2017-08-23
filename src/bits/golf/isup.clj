(ns bits.golf.isup)

(defn is-up? [site]
  (clojure.string/includes?
    (slurp (str "http://isup.me/" site))
    "'s j"))

#(clojure.string/includes?(slurp(str"http://isup.me/"%))"'s j")