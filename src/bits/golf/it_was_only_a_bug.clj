(ns bits.golf.it-was-only-a-bug)

(defn bugged []
  (doseq [n-spaces (range -8 9)]
    (println
        (clojure.string/join
          ; "String multiplication"
          (apply str
                 (repeat (Math/abs n-spaces) \space))
          "1234567890"))))

(defn bugged []
  (doseq [n-spaces (range -8 9)]
    (println
      (clojure.string/join
        ; "String multiplication"
        (apply str
               (repeat (Math/abs n-spaces) \space))
        "1234567890"))))

(fn b[](doseq [n(range -8 9):let[n(Math/abs n)a #(apply str %)]](println(a(clojure.string/join(a(repeat n\ ))"1234567890")))))

#(doseq[n(range -8 9)](println(clojure.string/join(apply str(repeat (Math/abs n)\ ))"1234567890")))