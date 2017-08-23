(ns bits.lucky-google)

(defn lucky-address [search-string]
  (let [encoded-search search-string]
    (str "https://www.google.ca/search?q=" encoded-search)))

(defn grab-page [address]
  (slurp address))

(defn feeling-lucky [search-string]
  (let [address (lucky-address search-string)]
    (grab-page address)))

; Google seems to block requests with a Java user agent