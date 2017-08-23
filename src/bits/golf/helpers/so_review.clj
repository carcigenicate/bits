(ns bits.golf.helpers.so-review)

(def safe-neighbors
  (set "(){}[]\" \t\n"))

(defn whitesp? [ch]
  (Character/isWhitespace ^Character ch))

(defn del-white [s]
  (reduce #(if (and (whitesp? %2)
                    (whitesp? (last %1)))
             %1
             (str %1 %2)) "" s))

(defn squeezable? [[a b c]]
  (and (whitesp? b) (or (safe-neighbors a) (safe-neighbors c))))

(defn elide [triples]
  (let [squeezabilities (drop-last (cons true (map (complement squeezable?) triples)))]
    (->> (map vector squeezabilities triples)
         (filter first)
         (map (comp first second)))))

(defn squeeze-safe [s]
  (apply str (apply str (->> s (partition 3 1) elide)) (take-last 2 s)))

(defn minimize-code-2* [code-str]
  (->> code-str
       del-white
       squeeze-safe))

(defmacro minimize-code [& body]
  (minimize-code-2* (apply str body)))

(defn x-1 []
  (minimize-code (fn [a]    (+ a   1))))