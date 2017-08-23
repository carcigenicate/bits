(ns bits.golf.comp-chain)

; http://codegolf.stackexchange.com/questions/92414/greater-than-less-than-greater-than-something-fishy/92572#92572

(defn comp-chain [chain-str]
  (loop [l 0
         h (count chain-str)
         [c & cr] chain-str
         a ""]
    (if c
      (case c
        \<(recur (inc l) h cr (str a l c))
        (recur l (dec h) cr (str a h c)))
      (str a l))))

(defn comp-chain2 [chain-str]
  (let [l (atom -1)
        h (atom (inc (count chain-str)))]
    (loop [[c & cr] chain-str
           a ""]
      (if c
        (if (= c \<)
          (recur cr (str a (swap! l inc) c))
          (recur cr (str a (swap! h dec) c)))
        (str a @l)))))

(defn comp-chain3 [chain-str]
  (loop [l 0
         h (count chain-str)
         [c & cr] chain-str
         a ""]
    (if c
      (if (= c \<)
        (recur (inc l) h cr (str a l c))
        (recur l (dec h) cr (str a h c)))
      (str a l))))

(defn f[s](loop[l 0 h(count s)[c & r]s a""](if c(case c\<(recur(inc l)h r(str a l c))(recur l(dec h)r(str a h c)))(str a l))))

(defn f2 [chain-str]
  (let [l (atom -1)
        h (atom(inc(count chain-str)))]
    (loop [[c & cr] chain-str a ""]
      (if c(if (= c \<)(recur cr (str a (swap! l inc) c))(recur cr (str a (swap! h dec) c)))(str a @l)))))


