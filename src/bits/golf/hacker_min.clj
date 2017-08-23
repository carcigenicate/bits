(def d 1)(quil.core/defsketch a :key-pressed(fn[](print(str(rand-nth(filter #(not(empty? %))(clojure.string/split(slurp(java.io.File.(:file(meta #'d))))#" ")))\ ))(flush)))
