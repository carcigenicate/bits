(ns bits.golf.nest-fns)

; http://codegolf.stackexchange.com/questions/96641/nest-a-string
#_
(defn nest [s]
  (str ; Concatenate...
    (loop [[f & r] s ; Get fn name, and remaining letters
           acc ""]
      (if f
        (recur r (str acc f (if r \()))
        acc))
    (apply str (repeat (dec (count s)) \))))) ; Add closing brackets

#(str(loop[[f & r]% a""](if f(recur r(str a f(if r\()))a))(apply str(repeat(dec(count %))\))))

; -----

(defn nest [s]
  (let [[f & r] s] ; Pop the "function name"
    (if f ; When it exists, construct string and recur, else, base-case
      (str f (if r (str \( (nest r) \)))))))

(defn n[s](let[[f & r]s](if f(str f(if r(str\((n r)\)))))))

; 4574 9155 With returning ""
; 5235 Without returning ""

; -----

; Thanks to NikoNyrh

(defn nest [[f & r]]
    (if f ; When it exists, construct string and recur, else, base-case
      (str f (if r (str \( (nest r) \))))))

(defn n[[f & r]](if(str f(if r(str\((n r)\))))))