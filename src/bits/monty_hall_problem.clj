(ns bits.monty-hall-problem
  (:require [helpers.general-helpers :as g]))

(def prizes #{:goat :car})

(defn random-door-number [n-doors rand-gen]
  (g/random-int 0 n-doors rand-gen))

(defn filter-from-doors [n-doors excludes]
  (remove #(get (into #{} excludes) %) (range n-doors)))

(defn random-door-from-remaining [remaining-doors rand-gen]
  (nth remaining-doors (g/random-int 0 (count remaining-doors) rand-gen)))

(defn simulate-round
  "Simulates a round, switching doors if indicated.
  Returns whether or not the player won the round, or false if n-doors is less than 3."
  [n-doors switch-doors? rand-gen]
  (if (> n-doors 2)
    (let [winning-door (random-door-number n-doors rand-gen)
          chosen-door (random-door-number n-doors rand-gen)
          remaining-doors (filter-from-doors n-doors [winning-door chosen-door])
          opened-door (random-door-from-remaining remaining-doors rand-gen)
          chosen-door' (if switch-doors?
                         (random-door-from-remaining (filter-from-doors n-doors [opened-door]) rand-gen)
                         chosen-door)]

      (if (= chosen-door' winning-door)
        :car
        :goat))
    false))

(defn simulate-rounds [n-doors switch-doors? rounds rand-gen]
  (map (fn [[r n]] [r (double (/ n rounds))])
       (frequencies
         (for [_ (range rounds)]
           (simulate-round n-doors switch-doors? rand-gen)))))

(defn test-procedure [n-doors switch-doors? rounds rand-gen]
  (let [start-t (g/current-ms-timestamp)
        result (simulate-rounds n-doors switch-doors? rounds rand-gen)
        end-t (g/current-ms-timestamp)]

    (clojure.pprint/pprint result)

    (- end-t start-t)))

; Change to a fold that exits early once the winning door is set?
#_
    (defn random-stage [n-doors rand-gen]
      (let [winning-door (random-door-number n-doors rand-gen)]
        (mapv #(if (= % winning-door) :car :goat)
              (range n-doors))))

#_
    (defn random-losing-door [stage rand-gen]
      (let [n-doors (count stage)
            last-i (dec n-doors)
            rand-door (random-door-number n-doors rand-gen)
            rand-door' (if (= (nth stage rand-door) :car)
                         (inc rand-door)
                         rand-door)]

        (g/wrap rand-door' 0 last-i)))
