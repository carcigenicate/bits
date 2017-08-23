(ns bits.golf.shutdown)

(defn shutdown []
  (.exec (Runtime/getRuntime) "shutdown-s"))

(.exec(Runtime/getRuntime)"shutdown/s")