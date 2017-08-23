(ns bits.golf.war)

(defn outcome [turns] ; Take input as ["rr" "ff"]
  (loop [p1-ammo? false ; Keep track of if each player has ammo
         p2-ammo? false
         [[p1-move p2-move] & rest-turns] turns] ; Deconstruct the turns out

    (let [killed? (fn [m m2 a] (and a (= m \f) (not= m2 \g))) ; Function that checks if one player killed another
          has-ammo? (fn [m a] (and (not= m \f) (or a (= m \r)))) ; Function that decides if a player ha ammo in the
                                                                 ;  next turn
          p1-killed? (killed? p2-move p1-move p2-ammo?) ; Check if each player was killed.
          p2-killed? (killed? p1-move p2-move p1-ammo?)]

      (cond ; Check who (if any) died. If no one died, recur to next turn.
        (and p1-killed? p2-killed?) 0
        p1-killed? 2
        p2-killed? 1
        :else (recur (has-ammo? p1-move p1-ammo?)
                     (has-ammo? p2-move p2-ammo?)
                     rest-turns)))))

(fn[t](loop[z nil x nil[[c v]& r]t](let[k #(and %3(= %\f)(not= %2\g))h #(and(not= %\f)(or %2(= %\r)))q(k v c x)w(k c v z)](cond(and q w)0 q 2 w 1 1(recur(h c z)(h v x)r)))))


