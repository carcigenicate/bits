(ns bits.golf.moonlight)

; http://codegolf.stackexchange.com/questions/111477/la-la-land-no-wait-moo-moo-moonlight

(defn repeat-prefix-cons [phrase]
  (let [[first-letter] phrase ; Get first letter

        ; Function that checks if a lowercased character is a part of the vowel set
        vowel? #(#{\a \e \i \o \u \y} (Character/toLowerCase %))

        ; cons(onant)? Negation of above
        cons? #(not (vowel? %))

        ; Decide how to split it depending on if the first character is a vowel
        first-sp-pred (if (vowel? first-letter) vowel? cons?)

        ; Split off the first chunk of cons/vowels
        [pre1 r] (split-with first-sp-pred phrase)

        ; Split off the rest of the vowels
        [pre2 r2] (split-with vowel? r)

        ; Shortcut function that turns a list into a string (Basically (join "" some-list-of-strings) )
        as #(apply str %)]

    (str ; ... then concat the prefix in front of the original phrase, and return
      (as ; ...then turn it back into a string since "repeat" returns a list... ^
        (repeat 2 ; ... then repeat it twice (shame Clojure doesn't have string multiplication)... ^
                (str (as pre1) (as pre2) \ ))) ; Concat the 2 prefix parts together with an space at the end... ^
      phrase)))

(fn[p](let[[f]p v #(#{\a\e\i\o\u\y}(Character/toLowerCase %))[q r](split-with(if(v f)v #(not(v %)))p)[w _](split-with v r)as #(apply str %)](str(as(repeat 2(str(as q)(as w)\ )))p)))

#_
    (defn repeat-prefix-cons [phrase]
      (let [[first-letter] phrase ; Get first letter

            ; Function that checks if a lowercased character is a part of the vowel set
            vowel? #(#{\a \e \i \o \u \y} (Character/toLowerCase %))

            ; cons(onant)? Negation of above
            cons? #(not (vowel? %))

            ; Decide how to split it depending on if the first character is a vowel
            first-sp-pred (if (vowel? first-letter) vowel? cons?)

            ; Split off the first chunk of cons/vowels
            [pre1 r] (split-with first-sp-pred phrase)

            ; Split off the rest of the vowels
            [pre2 r2] (split-with vowel? r)


            pre3 (take-while vowel? r2)
            as #(apply str %)]

        (str
          (as
            (repeat 2
                    (str (as (mapv as [pre1 pre2 pre3])) \ )))
          phrase)))
