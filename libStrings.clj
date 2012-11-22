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
(defn after [string delim end]
  "Get what is after delim in the string, finishing at string end"
  (let [x (.substring string (inc (.indexOf string delim)))]
    (.substring string (+ (count delim) (.indexOf string delim)) (inc (.indexOf x end)))))

