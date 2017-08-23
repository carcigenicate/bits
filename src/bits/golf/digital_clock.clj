(ns bits.golf.digital-clock
  (:require [helpers.general-helpers :as g])
  (:gen-class))

; http://codegolf.stackexchange.com/questions/102251/display-a-digital-clock
#_
(defn -main []
  (while true
    (let [d (java.util.Date.)]
      (flush)
      (print
        (str
          (apply str (repeat 9 "\b"))
          (.getHours d)":"(.getMinutes d)":"(.getSeconds d)))
      (Thread/sleep 99))))

(defn -main []
  (let [r (g/new-rand-gen 99)]
    (loop []
      (flush)
      (print
        (str (apply str (repeat 99 "\b"))
             (g/random-int 0 24 r) \: (g/random-int 0 60 r) \: (g/random-int 0 60 r)
             "   "))
      (Thread/sleep 750)
      (recur))))

(defn -main []
  (while true
    (flush)
    (print
      (str
        (apply str (repeat 99 "\b"))
        (.format (java.text.SimpleDateFormat. "H:m:s") (java.util.Date.))
        "   "))
    (Thread/sleep 99)))

#(while true(let[d(java.util.Date.)](flush)(print(str(apply str(repeat 9 "\b"))(.getHours d)":"(.getMinutes d)":"(.getSeconds d)))(Thread/sleep 99)))

#(while true(flush)(print(str(apply str(repeat 9"\b"))(.format(java.text.SimpleDateFormat."H:m:s")(java.util.Date.))"   "))(Thread/sleep 99))