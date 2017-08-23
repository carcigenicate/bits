(ns bits.golf.build-wall)

(defn build-wall []
  (println
    (apply str
      (map #(str (apply str %) "\n")
        (partition 70
          (subs (apply str
                  (repeat 175 "_|__"))
                0 700))))))

(fn[](println(apply str(map #(str(apply str %)"\n")(partition 70(subs(apply str(repeat 175 "_|__"))0 700))))))

#_
(fn[](let [j clojure.string/join](println(j"\n"(map #(apply str %)(partition 70(subs(j(repeat 234"_|__"))0 700)))))))
