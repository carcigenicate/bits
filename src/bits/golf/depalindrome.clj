(ns bits.golf.depalindrome)

; http://codegolf.stackexchange.com/questions/98335/depalindromize-this-string

#(subs % 0(inc(/(count %)2)))