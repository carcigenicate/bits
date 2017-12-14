(ns bits.sql-syntax)

(def test-db [{:id 1, :name "A"},
              {:id 0, :name "B"},
              {:id 3, :name "C"},
              {:id 2, :name "D"},
              {:id 4, :name "E"}])

(defn- where
  "Turns a vector of [key comparator compare-value] into a predicate that filter can use."
  [arg-vec]
  (let [[k f comp-arg] arg-vec]
    (fn [elem] (f (k elem) comp-arg))))

(defn select
  "Selects the given keys from the \"database\", filtered by the :where clause, and ordered by the :order-by key.

  Example:

  (select [:id] :from [{:id 1} {:id 2}] :where [:id > 0] :order-by :id)"
  [selection _ from & args]
  (let [arg-map (into {} (map vec (partition 2 args)))
        {where-vec :where, order-key :order-by} arg-map

         filt-ordered
         (cond->> from
           where-vec (filter (where where-vec))
           order-key (sort-by order-key))]

      (mapcat #(map % filt-ordered) selection)))

(select [:name] :from test-db :where [:id < 2] :order-by :id)
 ; Prints ("B" "A")