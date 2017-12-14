(ns bits.golf.web-sock
  (:require [gniazdo.core :as g]))

; No longer valid with the new specification
#_
(defn new-tag-posts [^String tag]
  (let [sock (g/connect "ws://qa.sockets.stackexchange.com"
                        :on-receive println)]

    (g/send-msg sock (str "1-questions-newest-tag-" tag))))

(defn new-tag-posts [^String tag]
  (let [; A mutable state to hold the sock.
        ; Necessary since the sock's callback requires a reference to the
        ;  sock itself
        sock-a (atom nil)

        ; Shortcut to save bytes
        send #(g/send-msg @sock-a %)

        handler #(if (= ((vec %) 11) \h) ; If the 11th character is 'h'
                   (send "hb") ; respond to the heartbeat
                   (println %)) ; else print the JSON

        sock (g/connect "ws://qa.sockets.stackexchange.com"
                        :on-receive handler)]

    ; Set the mutable state to the created socket
    (reset! sock-a sock)

    ; Request to be notified
    (send (str "1-questions-newest-tag-" tag))))

(use '[gniazdo.core])(fn[t](let[a(atom 0)s #(send-msg @a %)o(connect"ws://qa.sockets.stackexchange.com":on-receive #(if(=((vec %)11)\h)(s"hb")(println %)))](reset! a o)(s(str"1-questions-newest-tag-"t))))

(use '[gniazdo.core])#(send-msg(connect"ws://qa.sockets.stackexchange.com":on-receive println)(str"1-questions-newest-tag-"%))