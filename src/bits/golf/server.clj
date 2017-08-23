(ns bits.golf.server
  (:import (java.io BufferedReader InputStreamReader)))
#_
(defn server []
  (let [ss (java.net.ServerSocket. 12345)]
    (while 1
      (let [c (.accept ss)
            pw (java.io.PrintWriter. (.getOutputStream c))]
        (.write pw "Hello, World\n")
        (.flush pw)
        (.close c)))))
#_
(let[s(java.net.ServerSocket. 12345)](while 1(let[c(.accept s)p(java.io.PrintWriter.(.getOutputStream c))](.write p"Hello, World")(.flush p)(.close c))))

; -----

(defn server []
  (let [ss (java.net.ServerSocket. 12345)]
    (while 1
      ; Auto closes client
      (with-open [c (.accept ss)]
         ; Passes the PrintWriter as the first argument to each function
         (doto (java.io.PrintWriter. (.getOutputStream c))
           (.write "Hello, World\n")
           ; Parentheses are optional if the function only takes 1 argument
           ; Ugly, but shorter
           .flush)))))

#_
(let[s(java.net.ServerSocket. 12345)](while 1(with-open[c(.accept s)](doto(java.io.PrintWriter.(.getOutputStream c))(.write"Hello, World").flush))))

; -----

(defn connect-test-client []
  (.readLine
    (BufferedReader.
      (InputStreamReader.
        (.getInputStream
          (java.net.Socket. "127.0.0.1" 12345))))))