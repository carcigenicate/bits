(ns bits.golf.word-triplets)

(def allowed
       (set
         (apply str (map char (concat
                                (range 48 58)
                                (range 65 91)
                                (range 97 122)
                                [\ ])))))




(defn remove-punc [s]
  (apply str
         (filter allowed s)))

(defn triplets [s]
  (let [stripped (remove-punc s)
        lower (clojure.string/lower-case stripped)
        words (clojure.string/split lower #" ")]
    (vec
      (map vec
        (partition 3 1 words)))))

(def long-str
  "A chatbot (also known as a talkbot, chatterbot, Bot, chatterbox, Artificial Conversational Entity) is a computer program which conducts a conversation via auditory or textual methods. Such programs are often designed to convincingly simulate how a human would behave as a conversational partner, thereby passing the Turing test. Chatterbots are typically used in dialog systems for various practical purposes including customer service or information acquisition. Some chatterbots use sophisticated natural language processing systems, but many simpler systems scan for keywords within the input, then pull a reply with the most matching keywords, or the most similar wording pattern, from a database.\nThe term \"ChatterBot\" was originally coined by Michael Mauldin (creator of the first Verbot, Julia) in 1994 to describe these conversational programs.[1]\nThere are two main types of chatbots, one functions based on a set of rules, and the other more advanced version uses artificial intelligence. The chatbots based on rules, tend to be limited in functionality, and are as smart as they are programmed to be. On the other end, a chatbot that uses artificial intelligence, understands language, not just commands, and continuously gets smarter as it learns from conversations it has with people.")

(def shorter-str
  "There are two main types of chatbots, one functions based on a set of rules, and the other more advanced version uses artificial intelligence. The chatbots based on rules, tend to be limited in functionality, and are as smart as they are programmed to be. On the other end, a chatbot that uses artificial intelligence, understands language, not just commands, and continuously gets smarter as it learns from conversations it has with people.")
