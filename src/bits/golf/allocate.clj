(ns bits.golf.allocate)

(defrecord A []
  Object
  (finalize [this] (mapv (fn [_] (A.)) (range 5))))

(mapv (fn [_] (A.)) (range))