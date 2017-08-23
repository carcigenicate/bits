(ns bits.golf.abcs)

"
A, B, C, D, E, F, G,
H, I, J, K, L, M, N, O, P,
Q, R, S,
T, U, V,
W, X, Y, Z.
"

(defn abcs []
  (print
    (loop [alpha (map char (range 65 91))
           [c & cuts] [7 9 3 3 4]
           acc ""]
      (if c
        (let [[a-line a-rest] (split-at c alpha)
              e (if cuts \, \.)]
          (recur a-rest cuts (str acc (clojure.string/join ", " a-line) e "\n")))
        acc))))

#(print(loop[a(map char(range 65 91))[c & d][7 9 3 3 4]s""](if c(let[[l r](split-at c a)e(if d\,\.)](recur r d(str s(clojure.string/join", "l)e"\n")))s)))

#(print "A, B, C, D, E, F, G,\nH, I, J, K, L, M, N, O, P,\nQ, R, S,\nT, U, V,\nW, X, Y, Z.")