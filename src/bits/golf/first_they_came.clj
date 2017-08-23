(ns bits.golf.first-they-came)

"First they came for the Socialists, and I did not speak out-
Because I was not a Socialist.

Then they came for the Trade Unionists, and I did not speak out-
Because I was not a Trade Unionist.

Then they came for the Jews, and I did not speak out-
Because I was not a Jew.

Then they came for me-and there was no one left to speak for me."

(defn ftc []
  ; Loop over the lines numbers for reference
  (doseq [i (range 4)
          :let [; Added a dummy last "class" since "me-" wasn't necessary,
                ;  and only having 3 elements was causing OOB errors.
                classes ["Socialist" "Trade Unionist" "Jew" ""]

                ; A helper function that executes either its first or second argument
                ;  depending on if the current line is one of the first 3 lines.
                if-first-three-lines #(if (< i 3) % %2)]]

    ; An implicit space is inserted between each of the args to println
    (println
      ; Decide on the opener
      (if (= i 0) "First" "Then")

      "they came for"

      ; Start string concatenation because the implicit space
      ;  begins complicating things.
      (str
        ; Decide which "class" the line belongs to
        (if-first-three-lines
          (str "the " (classes i) "s, ")
          "me-")

        ; Decide on the line ending
        (if-first-three-lines
          "and I did not speak out-\n"
          "and there was no one left to speak for me.")

        ; Then pick the trailer
        (if-first-three-lines
          (str "Because I was not a " (classes i) ".\n")
          "")))))

(defn ftc []
  ; Loop over the lines numbers for reference
  (doseq [i (range 4)
          :let [; Added a dummy last "class" since "me-" wasn't necessary,
                ;  and only having 3 elements was causing OOB errors.
                classes ["Socialist" "Trade Unionist" "Jew" ""]

                ; A helper function that executes either its first or second argument
                ;  depending on if the current line is one of the first 3 lines.
                if-first-three-lines #(if (< i 3) % %2)]]

    ; An implicit space is inserted between each of the args to println
    (println
      ; Decide on the opener
      (if (= i 0) "First" "Then")

      "they came for"

      ; Start string concatenation because the implicit space
      ;  begins complicating things.
      (str
        ; Decide which "class" the line belongs to
        (if-first-three-lines
          (str "the " (classes i) "s, ")
          "me-")

        ; Decide on the line ending
        (if-first-three-lines
          "and I did not speak out-\n"
          "and there was no one left to speak for me.")

        ; Then pick the trailer
        (if-first-three-lines
          (str "Because I was not a " (classes i) ".\n")
          "")))))

(fn[](doseq[i(range 4):let[c["Socialist""Trade Unionist""Jew"""]f #(if(< i 3)% %2)]](println(if(= i 0)"First""Then")"they came for"(str(f(str"the "(c i)"s, ")"me-")(f"and I did not speak out-\n""and there was no one left to speak for me.")(f(str"Because I was not a "(c i)".\n")"")))))