(ns bits.golf.middle-name)

(def test-name "Brendon Taylor Willis Willy Williams")

(defn middle-name [full-name]
  ; Partition the string into a list of names split on spaces, then take every second element
  ;  since partition keeps the spaces
  (let [sub-names (take-nth 2 (partition-by #(= % \ ) full-name))
        sub-names (map #(drop 1 %) )]

    ; ... then join the middle names together on a space.
    (clojure.string/join " "
      ; drop returns list of chars, so turn them back into strings...
      (map #(apply str %)
           ; Drop the first and last name
           (drop 1
             (drop-last sub-names))))))

(fn[f](clojure.string/join" "(map #(apply str %)(drop 1(drop-last(take-nth 2(partition-by #(= % \ )f)))))))
