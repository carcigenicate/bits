(ns bits.golf.blink-caps)
#_
(import '[java.awt Toolkit event.KeyEvent]
        '[java.awt.event KeyEvent])

(repeatedly
  (fn[]
     (Thread/sleep 500)

     (.setLockingKeyState
       (java.awt.Toolkit/getDefaultToolkit)
       java.awt.event.KeyEvent/VK_CAPS_LOCK
       true)))
#_
(repeatedly(fn[](Thread/sleep 500)(.setLockingKeyState(java.awt.Toolkit/getDefaultToolkit)java.awt.event.KeyEvent/VK_CAPS_LOCK true)))
