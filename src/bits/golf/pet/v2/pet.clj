(ns bits.golf.pet.v2.pet)

; 100 0 0 10 0 100
(def he (atom 100))
(def hu (atom 0))
(def sl (atom 0))
(def fo (atom 10))
(def po (atom 0))
(def mo (atom 100))

(defn a! [sa n]
  (swap! sa #(+ % n)))

(defn s! [sa n]
  (swap! sa #(- % n)))

(defn apply-rules []
    (a! hu 1)
    (a! sl 1)
    (if (>= @hu 80)
      (s! he 1))
    (if (>= @sl 80)
      (do
        (reset! sl 0)
        (a! hu 9)))
    (if (>= @he 80)
      (a! mo 1)))

(defn handle-keypress [k]
    (case k
      \f (if (> @hu 8)
           (do
             (s! fo 1)
             (s! hu 9)))
      \s (reset! sl 0)
      \p (if (> @po 0)
           (do
             (s! po 1)
             (a! he 9)))
      \b (if (> @mo 8)
           (do
             (s! mo 9)
             (a! po 1)))
      \t (if (> @mo 8)
           (do
             (s! mo  9)
             (a! fo 1)))
      nil))


(defn start-listener []
  (.start
    (Thread. ^Runnable
      (fn []
        (while (> @he 0)
            (handle-keypress (first (read-line))))))))

(defn -main []
  (start-listener)
  (while (> @he 0)
    (Thread/sleep 1000)

    (apply-rules)

    (println (str
               "Health: " @he "\n"
               "Hunger: " @hu "\n"
               "Sleep: " @sl "\n"
               "Food: " @fo "\n"
               "Potions: " @po "\n"
               "Money:" @mo "\n")))

  (println "You died!\n"))

