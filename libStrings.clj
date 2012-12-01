;LibStrings
;Perform actions on sequences, splitting or getting information.
(ns lib.libStrings)
(import lib)

;Test to see whether or not a point in a string is in between d1 and d2.
(defn in-between? [string pos d1 d2]
  "Is the position in between d1 and d2?"
  (let [x (.substring string 0 pos),
        y (.substring string (inc pos) (count string))]
    (and (.contains x d1) (.contains y d2))))

;Get the information between the delim and end.

(defn after [string d1 & d2]
  "Get what is after d1 and possibly before d2"
  (let [x (.substring string (+ (.indexOf string d1) (count d1)))]
    (.substring x 0 (if (not (bad? d2)) (.indexOf x (first d2)) (count x)))))