(ns bits.golf.hashtag)

(defn hashtag? [s]
  (let [[h & r] s
        codes (map int r)]
    (and (= h \#)
         (not (<= 48 (or (first codes) 0) 57))
         (every?
           #(or (Character/isLetterOrDigit ^long %)
                (= 95 %))
           codes))))

(fn[s](let[[h & r]s n(map int r)](and(= h\#)(not(<= 48(or(first n)0)57))(every? #(or(<= 97 % 122)(<= 65 % 90)(= % 95)(<= 48 % 57))n))))

(fn[s](let[[h & r]s n(map int r)](and(= h\#)(not(<= 48(or(first n)0)57))(every? #(or(Character/isLetterOrDigit^long %)(= 95 %))n))))