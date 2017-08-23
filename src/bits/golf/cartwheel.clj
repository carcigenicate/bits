(ns bits.golf.cartwheel)

(defn cartwheel? [word]
  (let [; Valid Character Sets
        cs ["nu" "wm" "bp"]

        ; Is the list composed of only a single character?
        count-1? #(= 1 (count (set %)))

        ; Grabs every other element of the list
        every-other #(take-nth 2 %)

        ; Do the characters repeat? Works by checking if every other element is the same, then drops the first letter
        ; to check all the odd indexed characters
        repeating? #(and (count-1? (every-other %))
                         (count-1? (every-other (drop 1 %))))]

    ; Do all the predicates hold?
    (and (= (first word) (last word))
         (repeating? word)
         ; Is the set of letters in the word part of some set of the valid characters?
         (some #(= (set word) (set %)) cs))))

(fn[w](let[s["nu""wm""bp"]c #(= 1(count(set %)))e #(take-nth 2 %)r #(and(c(e %))(c(e(drop 1 %))))](and(=(first w)(last w))(r w)(some #(=(set w)(set %))s))))