(ns bits.double-deal)

(def cards-per-deck 52)

(defn gen-cards [n-cards color]
  (repeat n-cards color))

(defn gen-deck [colors]
  (let [n-colors (count colors)
        n (/ cards-per-deck n-colors)]
    (if (= (rem cards-per-deck n-colors) 0)
      (mapcat #(gen-cards n %) colors)
      nil)))

{\g [[\g \g] [\b \g \b \b]]
 \b [\b \b \b [\g \b \g \b]]}

(defn double-deal [deck]
  (loop [[f-card s-card & rest-cards] deck
         acc {}]
    (if f-card
      (let [updated-decks (update acc f-card
                                  #(let [[color-deck unknown-deck] %]
                                     [(conj color-deck f-card) (conj unknown-deck s-card)]))]
        (recur rest-cards updated-decks))
      acc)))