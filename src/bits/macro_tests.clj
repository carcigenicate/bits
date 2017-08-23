(ns bits.macro-tests
  (:require [helpers.general-helpers :as g]
            [clojure.pprint :refer [pprint]]))

(defn factor? [n m]
  (= (rem n m) 0))

(defn mult-forms? [n forms]
  (factor? (count forms) n))

(defmacro add [expr]
  (let [e expr]
    (cons + e)))

(defmacro from-infix [expr]
  (let [[a1 f a2] expr]
    (list f a1 a2)))

(defmacro from-many-infix [expr]
  (let [args (reverse (take-nth 2 expr))
        operators (reverse (-> expr (drop 1) (take-nth 2)))
        [last-arg & rest-args] args]
    (loop [[arg & r-args] rest-args
           [op & r-ops] operators
           acc '()]
      (if arg
        (recur r-args r-ops
               (if (empty? acc)
                 (list op arg last-arg)
                 (conj (list op arg acc))))
        acc))))

(defmacro my-cond [& [clause then & rest-exprs :as exprs]]
  (if (and (not (empty? exprs))
           (mult-forms? 2 exprs))
    `(if ~clause ~then
       (my-cond ~@rest-exprs))))

(defmacro my-condp [val pred & clauses]
  (if (and (not (empty? clauses)) (not= (count clauses) 1))
    (let [[other-val then & rest-clauses] clauses]
      `(if (~pred ~val ~other-val) ~then
           (my-condp ~val ~pred ~@rest-clauses)))
    (first clauses)))

(defn wrap-if-bare [form?]
  (if (symbol? form?) `(~form?) form?))

(defmacro my-> [val & forms]
  (if (not (empty? forms))
    (let [[first-form & rest-forms] forms
          [f & r]  (wrap-if-bare first-form)
           new-val (conj r val f)]
      `(my-> ~new-val ~@rest-forms))
    val))

(defmacro my->> [val & forms]
  (if (not (empty? forms))
    (let [[first-form & rest-forms] forms
          first-form' (wrap-if-bare first-form)
          new-val `(~@first-form' ~val)]
      `(my->> ~new-val ~@rest-forms))
    val))

(defn insert-at
  "Inserts an element at the given index. Returns a list.
  Indices out of range of the collection are treated as either 0 or (dec (count xs))
  Likely very expensive."
  [xs x index]
  (let [[f-half s-half] (split-at index xs)]
    (concat f-half `(~x) s-half)))

(defmacro ->n
  "Expects a value to be threaded, and pairs of indices and forms.
  The indices indicate which index the value should be inserted at in the following form.
  Example:
      (n-> 1
          1 (+ 2)
          0 (+ 3))
  Expands to: (+ (+ 2 1) 3)"
  [val & forms]
  (if (and (not (empty? forms))
           (mult-forms? 2 forms))
    (let [[thread-n form & rest-forms] forms
          threaded  (insert-at form val (inc thread-n))]
      `(->n ~threaded ~@rest-forms))
    val))

(defmacro ->at-n [val index & forms]
  (let [inter-forms (cons index (interpose index forms))]
    `(->n ~val ~@inter-forms)))

(defmacro condbp [val1 val2 & preds]
  (if (> (count preds) 1)
    (let [[pred result & rest-preds] preds
          pred-m `(~@(wrap-if-bare pred) ~val1 ~val2)]
      `(if ~pred-m ~result (condbp ~val1 ~val2 ~@rest-preds)))
    (first preds)))

(defmacro condmp
  "Applies the vals to each predicate and executes the form following the first predicate that returns true.

  (condmp [1 2 3]
      > \"bad\"
      < \"Good\"
      = \"Bad\")

      \"Good\""
  [vals & preds]
  (if (> (count preds) 1)
    (let [[pred result & rest-preds] preds
          pred-m `(apply ~@(wrap-if-bare pred) ~vals)]
      `(if ~pred-m ~result (condmp ~vals ~@rest-preds)))
    (first preds)))

; What the actual fuck? Stackoverflows all the way down.
(defmacro ^:private assert-args' [& forms]
  (let [[pred error & rest-forms] forms
        recur? (not (empty? rest-forms))]
    `(if ~pred
      ~(if recur?
          `(assert-args' ~@rest-forms))
       (throw (IllegalArgumentException.
               (str (first ~&form) " requires " ~error))))))

#_
(defmacro ^:private assert-args2' [& forms]
  (loop [[pred error & rest-forms] forms
         acc '()]
    (if pred
      (recur rest-forms
             (list acc)))))

(defmacro ^:private assert-args2' [& forms]
  (let [[pred error & rest-forms] forms
        r? (not (empty? (g/dbg rest-forms)))
        m `(when-not ~pred
             (throw (IllegalArgumentException.
                      (str (first ~&form) " requires " ~error))))]
    (when r?
      (cons m `(assert-args2' ~@rest-forms)))))



(defmacro assert-arg
  "Takes pred/error message pairs of forms.
  If the predicate is false, and exception is thrown with the message:
  (str \"fn/macro-name requires \" your-error-message)"
  [& forms])

(defmacro if-let-mult [bindings then else]
  (assert-args (mult-forms? 2 bindings) "an even number of bindings.")
  (let [pred-val (second bindings)]
    `(if ~pred-val
      (let [~@bindings]
        ~then)
      ~else)))