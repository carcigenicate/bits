(ns bits.golf.derangement)

; http://codegolf.stackexchange.com/questions/103536/generate-a-random-derangement
#_
(defn dearang [ls]
  (let [s (shuffle ls)
        done? (reduce (fn [acc [x y]]
                        (if (= x y)
                          false
                          acc))
                      true
                      (map vector ls s))]
    (if done? s (recur ls))))
#_
(defn dearang [ls]
  (let [s (shuffle ls)
        done? (reduce (fn [acc [x y]]
                        (and (not= x y) acc))
                      true
                      (map vector ls s))]
    (if done? s (recur ls))))

(defn dearang [ls]
  (let [s (shuffle ls)
        bad? (some (fn [[x y]] (= x y))
                (map vector ls s))]
    (if (not bad?) s (recur ls))))


#(let[s(shuffle %)d(reduce(fn[a[x y]](if(= x y)false a))true(map vector % s))](if d s(recur %)))

#(let[s(shuffle %)](if(reduce(fn[a[x y]](and(not= x y)a))true(map vector % s))s(recur %)))

#(let[s(shuffle %)](if(not(some(fn[[x y]](= x y))(map vector % s)))s(recur %)))
