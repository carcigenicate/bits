(ns bits.golf.clear-clipboard)

(defn clear-clipboard []
  (.setContents
      (.getSystemClipboard
        (java.awt.Toolkit/getDefaultToolkit))
      (java.awt.datatransfer.StringSelection. "")
      nil))

#(.setContents(.getSystemClipboard(java.awt.Toolkit/getDefaultToolkit))(java.awt.datatransfer.StringSelection."")nil)


