(ns bits.golf.animate-hello-world)

; http://codegolf.stackexchange.com/questions/109897/animate-the-text-in-your-terminal/109983

(defn -main []
  (doseq [i (range 11)] ; For the index of each character...
    (Thread/sleep 1000)
    (print "\r" ; Clear old line
           (apply str ; Turn the vector back into a string
             (update ; Update the char at index i"
               (vec "hello world") ; Turn the string into a vector so it's updatable
               i ; Index to update
               #(char (- (int %) 32))))) ; Subtract 32 from the character to make uppercase
    (flush)))
#_
(doseq[i(range 11)](Thread/sleep 1000)(print"\r"(apply str(update(vec"hello world")i #(char(-(int %) 32)))))(flush))