(ns bits.golf.pet)

(defrecord State [he hu sl fo po mo])

(def new-state (->State 100 0 0 10 0 100))

(def state (atom new-state))

(defn update! [sa k f]
  (swap! sa #(update % k f)))

(defn apply-rules [s]
  (let [s' (atom s)
        u! #(update! s' %1 %2)
        g #(get @s' %)]
    (u! :hu inc)
    (u! :sl inc)
    (if (>= (g :hu) 80)
      (u! :he dec))
    (if (>= (g :sl) 80)
      (do
        (u! :sl (fn [_] 0))
        (u! :hu #(+ % 9))))
    (if (>= (g :he) 80)
      (u! :mo inc))
    @s'))

(defn get-input []
  (let [raw (read-line)]
    (first raw)))

(defn handle-keypress [s k]
  (let [s' (atom s)
        u! #(update! s' %1 %2)
        g #(get @s' %)]
    (case k
      \f (if (> (g :hu) 8)
           (do
             (u! :fo dec)
             (u! :hu #(- % 9))))
      \s (u! :sl (fn [_] 0))
      \p (if (> (g :po) 0)
           (do
             (u! :po dec)
             (u! :he #(+ % 9))))
      \b (if (> (g :mo))
           (do
             (u! :mo #(- % 9))
             (u! :po inc)))
      \t (if (> (g :mo) 8)
           (do
             (u! :mo #(- % 9))
             (u! :fo inc)))
      nil
      @s')))

(defn start-listener []
  (.start
    (Thread. ^Runnable
      (fn []
        (while true
          (let [k (get-input)]
            (swap! state #(handle-keypress % k))))))))

(defn -main []
  (start-listener)
  (let [g #(get @%1 %2)]
    (while true
      (Thread/sleep 1000)

      (swap! state #(apply-rules %))

      (println (str
                 "Health: " (g state :he) "\n"
                 "Hunger: " (g state :hu) "\n"
                 "Sleep: " (g state :sl) "\n"
                 "Food: " (g state :fo) "\n"
                 "Potions: " (g state :po) "\n"
                 "Money:" (g state :mo) "\n"))

      (if (<= (g state :he) 0)
        (do
          (println "You died!\n\n\n\n\n")
          (reset! state new-state))))))
