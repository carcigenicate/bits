(ns bits.golf.no-numbers-2014)

; Not submitted
; http://codegolf.stackexchange.com/questions/17005/produce-the-number-2014-without-any-numbers-in-your-source-code/40029#40029

(defn print-2014-no-numbers
  (print(reduce +(map int"~~~~~~~~~~~~~~~|"))))