(ns bits.golf.friday-13th)

(import '[java.time LocalDate DayOfWeek])

(defn count-friday-13ths [year]
  (loop [d (LocalDate/parse (str year "-01-01")) ; Starting date
         c 0] ; The count
    (if (= (.getYear d) year) ; If we haven't moved onto the next year...
      (recur (.plusDays d 1) ; Add a day...
             (if (and (= (.getDayOfMonth d) 13) ; And increment the count if its Friday the 13th
                      (= (.getDayOfWeek d) DayOfWeek/FRIDAY))
               (inc c) c))
      c))) ; Return the count once we've started the next year.

(import '[java.time LocalDate DayOfWeek])#(loop[d(LocalDate/parse(str %"-01-01"))c 0](if(=(.getYear d)%)(recur(.plusDays d 1)(if(and(=(.getDayOfMonth d)13)(=(.getDayOfWeek d)DayOfWeek/FRIDAY))(inc c)c))c))

; -----

#(loop[d(java.time.LocalDate/parse(str % "-01-01"))c 0](if(=(.getYear d)%)(recur (.plusDays d 1)(if(and(= (.getDayOfMonth d)13)(=(.getDayOfWeek d)java.time.DayOfWeek/FRIDAY))(inc c)c))c))