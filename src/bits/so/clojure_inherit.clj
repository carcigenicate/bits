(ns bits.so.clojure-inherit
  (:import [javafx.scene.control ListCell]
           [javafx.scene.text Font]))
#_
(defrecord FontFaceListCell [])
#_
(proxy [javafx.scene.control.ListCell] []
  (updateItem [item empty?]
    (println item empty?)))
#_
(proxy-super updateItem item empty?)
#_
(if empty?
  (setText this nil)
  (do
    (setFont this (Font.font item 16.0))
    (setText this item)))
