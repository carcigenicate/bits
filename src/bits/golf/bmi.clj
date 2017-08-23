(ns bits.golf.bmi)

(defn bmi-category [weight height]
  (let [bmi (/ weight (Math/pow height 2))]
    (println "BMI: " bmi)
    (cond
      (< bmi 18.5) "Underweight"
      (and (<= 18.5 bmi) (< bmi 25)) "Normal"
      (<= 25 bmi) "Overweight")))

(defn height-from-bmi [weight bmi]
  (Math/sqrt (/ weight bmi)))

(defn weight-from-bmi [height bmi]
  (* bmi (Math/pow height 2)))