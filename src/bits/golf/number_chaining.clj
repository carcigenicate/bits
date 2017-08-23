(ns bits.golf.number-chaining)

; Mine
; http://codegolf.stackexchange.com/questions/112937/number-chaining-predicate

(defn number-chain? [nums]
  (let [; Turn each number into a set of characters
        set-nums (map #(set (str %)) nums)

        ; Partition the sets into lists of neighbors
        ; [1 2 3 4] -> [[1 2] [2 3] [3 4]]
        partitioned (partition 2 1 set-nums)]

    ; Does every second neighbor contain some element of the first?
    (every?
      (fn [[l1 l2]]
        (some l1 l2))
      partitioned)))

(fn[n](every?(fn[[q w]](some q w))(partition 2 1(map #(set(str %))n))))
