(ns bits.golf.adsr
  (:require [quil.core :as q]))

; http://codegolf.stackexchange.com/questions/110037/attack-decay-sustain-release

(defn -main [& args]
  (let [[a d s r] (map #(Integer/parseInt %) args)
        [sx sy :as start-pos] [100 600]
        attack-level (- sy 127)
        sustain-level (- sy 64)
        ax (+ sx a)
        dx (+ ax d)
        sx (+ dx s)
        rx (+ sx r)
        ap [ax attack-level]
        dp [dx sustain-level]
        sp [sx sustain-level]
        rp [rx sy]]
    (q/defsketch r
      :size [999 999]
      :draw
      (fn []
        (q/stroke-weight 5)
        (q/scale 1.5)
        (q/line start-pos ap)
        (q/line ap dp)
        (q/line dp sp)
        (q/line sp rp)))))

