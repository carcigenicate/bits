(ns bits.golf.to-decimal)

; http://codegolf.stackexchange.com/questions/102219/binary-to-decimal-converter/102239#102239

#_
(defn to-dec [binary-str]
  (reduce (fn [acc [place digit]]
            (let [parsed-digit (Integer/parseInt (str digit))
                  place-value (long (Math/pow 2 place))]
              (+ acc (* parsed-digit place-value))))
          0
          (map vector (range (dec (count binary-str)) -1 -1) binary-str)))
#_
(defn to-dec [binary-str]
  (reduce (fn [acc [place digit]]
            (let [parsed-digit (Integer/parseInt (str digit))
                  place-value (long (Math/pow 2 place))]
              (+ acc (* parsed-digit place-value))))
          0
          (map vector (range) (reverse binary-str))))

(defn to-dec [binary-str]
  (reduce (fn [acc digit]
            (+ (* acc 2) digit))
          (map #(Integer/parseInt (str %)) binary-str)))

(defn to-dec [binary-str]
  (reduce (fn [acc digit]
            (+ (* acc 2) (- (int digit) 48)))
          binary-str))

(fn[s](reduce #(+(* %1 2)%2)(map #(Integer/parseInt(str %))s)))

#(reduce(fn[a d](+(* a 2)(-(int d)48)))%)

; -----

(defn extended-digit-to-dec [^Character extended-digit-char]
  (let [ch (Character/toUpperCase extended-digit-char)
        code (int ch)
        extended (into {} (map vector (map char (range 65 91)) (range 10 Integer/MAX_VALUE)))]
    (if (<= 48 code 57)
      (- code 48)
      (extended ch))))



(defn hex-to-dec [hex-str]
  (let [p #(.pow (BigInteger. "16") %)]
    (reduce (fn [acc [i digit]]
              (let [^BigInteger place-value (p i)
                    dig-val (BigInteger/valueOf (extended-digit-to-dec digit))]
                #_(println (str "(" dig-val " * " place-value ") +"))
                (.add acc
                 (.multiply place-value dig-val))))
            (BigInteger. "0")
            (map vector (range) (reverse hex-str)))))
