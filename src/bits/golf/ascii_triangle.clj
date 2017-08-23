(ns bits.golf.ascii-triangle)

; http://codegolf.stackexchange.com/questions/109491/ascii-triangles
#_
(defn triangle [hypot-n]
  (let [r #(apply str (repeat % %2))] ; Create a shortcut string repeat function, since I need it twice.
    (doseq [line-n (range hypot-n)] ; Loop over the range 0 to hypot...
      (println (str \| (r line-n \ ) \\))) ; Printing a bar, then line-n many spaces, then a slash
    (print (r (inc hypot-n) \-)))) ; Then print the trailing dashes

(fn[h](let[r #(apply str(repeat % %2))](doseq[n(range h)](println(str\|(r n\ )\\)))(print(r(inc h)\-))))

; -----

(defn triangle [hypot-n]
  (let [r #(apply str (repeat % %2))]
    (println
      (apply str
        (for [line-n (range hypot-n)]
          (str \| (r line-n \ ) \\ "\n"))))
    (print (r (inc hypot-n) \-))))

(fn[h](let[r #(apply str(repeat % %2))](println(apply str(for[n(range h)](str\|(r n\ )\\"\n"))))(print(r(inc h)\-))))
