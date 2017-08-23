(ns bits.golf.war-is-peace)

(defn war [word]
  ; Create a list of strings by splitting on commas, and "get" from the resulting list
  ((clojure.string/split "freedom,,war,,strength,,slavery,peace,ignorance" #","
     ; Calculate the index to get (see the original answer for details)
    (rem (int (nth word 1)) 9))))

#((clojure.string/split"freedom,,war,,strength,,slavery,peace,ignorance"#",")(rem(int(nth % 1))9))