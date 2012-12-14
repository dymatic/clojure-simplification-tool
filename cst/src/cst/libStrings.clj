;LibStrings
;Perform actions on sequences, splitting or getting information.
(ns lib.libStrings)

;Test to see whether or not a point in a string is in between d1 and d2.
(defn in-between? [string pos d1 d2]
  "Is the position in between d1 and d2?"
  (let [x (.substring string 0 pos),
        y (.substring string (inc pos) (count string))]
    (and (.contains x d1) (.contains y d2))))

;Get the information between the delim and end.

(defn safe [string stringop check]
  "Only perform stringop on the string if check is inside of the string"
  (if (.contains string check) (stringop string) string))

(defn after [string d1 & d2]
  "Get what is after d1 and possibly before d2"
  (let [x (.substring string (+ (.indexOf string d1) (count d1)))]
    (.substring x 0 (if (not (= d2 nil)) (.indexOf x (first d2)) (count x)))))

(defn replaceList [string lis & with]
  "Goes through the list, replacing everything in the lis form the string with with"
  (loop [l1 lis, s string]
    (if (= l1 ()) s
      (recur (rest l1)
             (.replace s (first l1) (if (not (= with nil)) (first with) ""))))))