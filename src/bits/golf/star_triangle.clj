(ns bits.golf.star-triangle)

(defn tri [n]
  (let [f (fn [m]
            (when (<= m n)
              (println (apply str (repeat m \*)))
              (recur (inc m))))]
    (f 1)))

(defn tri2 [n]
  (#(when (<= % n)
     (println (apply str (repeat % \*)))
     (recur (inc %))) 0))

(defn tri3 [n]
  (doseq [m (range 1 (inc n))]
    (println (apply str (repeat m \*)))))

; mapv necessary or else it will preprint a ( becasue map is lazy
(defn tri4 [n]
  (mapv #(println (apply str (repeat % \*))) (range 1 (inc n))))

(defn tri2[n](#(when(<= % n)(println(apply str(repeat %\*)))(recur(inc %)))0))

(defn t[n](doseq[m(range 1(inc n))](println(apply str(repeat m\*)))))

(defn t[n](mapv #(println(apply str(repeat %\*)))(range 1(inc n))))

#(doseq[m(range 1(inc %))](println(apply str(repeat m\*))))