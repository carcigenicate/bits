(ns bits.golf.google)

;http://codegolf.stackexchange.com/questions/58891/dont-google-google

#_
(defn google [s]
  (if (= s "google")
    (/ 1 0)
    (count s)))

(defn google [s]
  (/ (count s)
     (if(= s "google")0 1)))




#(if(= % "google")(/ 1 0)(count %))

#(/(count %)(if(= %"google")0 1))