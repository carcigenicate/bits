(ns bits.golf.military-time)

; input format: 12 34 am
; output format 0034

; 12 34 p -> 1234
#_
(defn to-24h [time-str]
  ; Split the string on spaces, then deconstruct out the hour,
  ; minute, and whether it's am or pm
  (let [[hours minutes [ampm]] (clojure.string/split time-str #" ")
        pm? (= ampm \p)
        h (Integer/parseInt hours)
        twelve? (= h 12)]
    ; Dispatching to figure out how much needs to be added/subtracted.
    (str (cond (and (not twelve?) pm?) (+ h 12)
               (and twelve? (not pm?)) (- h 12)
               :e h)
         minutes)))
#_
#(let[[h m[i]](clojure.string/split % #" ")p(= i\p)h(Integer/parseInt h)t(= h 12)](str(cond(and(not t)p)(+ h 12)(and t (not p))(- h 12):e h)m))

; -----
#_
(defn to-24h [time-str]
  ; Split the string on spaces, then deconstruct out the hour,
  ; minute, and whether it's am or pm
  (let [[hours minutes [ampm]] (clojure.string/split time-str #" ")
        pm? (= ampm \p)
        h (Integer/parseInt hours)
        h (if (= h 12) 0 h)] ; 0-out the hours if they're equal to 12 to minimize cases
    (str (if pm?
           (+ h 12)
           h)
         minutes)))

#(let[[h m[i]](clojure.string/split % #" ")h(Integer/parseInt h)h(if(= h 12)0 h)](str(if(= i\p)(+ h 12)h)m))

; -----

; Thanks to BRFennPocock

(defn to-24h [time-str]
  ; Split the string on spaces, then deconstruct out the hour,
  ; minute, and whether it's am or pm
  (let [[hours minutes [ampm]] (clojure.string/split time-str #" ")
        pm? (= ampm \p)
        h (mod (Integer/parseInt hours) 12)]
    (str (+ (if pm? 12 0) h)
         minutes)))

#(let[[h m[i]](clojure.string/split % #" ")](str(+(if(= i\p)12 0)(mod(Integer/parseInt h)12))m))

; -----



(defn zero-pad [n]
  (let [n-str (str n)]
    (if (= 1 (count n-str))
        (str \0 n-str)
        n-str)))


(defn test-to-24 [f]
  (clojure.pprint/pprint
    (for [p ["am" "pm"]
          h (range 1 13)
          m (range 60)
          :let [t-str (str (zero-pad h) " " (zero-pad m) " " p)]]
      [t-str (f t-str)])))

; ----- Built-in

(defn to-24h-built-in [time-str]
  (let [p #(java.text.SimpleDateFormat. %)]
    (.format (p "km")
      (.parse (p "h m a") time-str))))

(fn[s](let[p #(java.text.SimpleDateFormat. %)](.format(p"km")(.parse(p"h m a")s))))


