(ns bits.golf.avg-google-size)

(defn avg-request-len [n]
  (/
    (reduce (fn [acc _]
              (+ acc (count (slurp "http://google.com"))))
            0
            (range n))
    n))

#(/(reduce(fn[a _](+ a(count(slurp"http://google.com"))))0(range %))%)
