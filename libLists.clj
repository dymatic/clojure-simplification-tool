;libLists
;Perform actions on lists, extracting or creating and re-organizing information on a large scale.
(ns lib.libLists)
(import lib)

;Reverse the order of a list by conjing values to the head.
(defn rlist [lis]
  "Reverses a list"
  (loop [li lis, ll '()]
    (if (bad? li) ll
      (recur (rest li) (conj ll (first li))))))

;Recurse over the list, seeing if the supplied condition is found in the list.
(defn orlist [condition lis]
  "Is condition found in lis?"
    (cond
      (= (first lis) condition) true
      (= lis ()) false
      :else (recur condition (rest lis))))

;Cut a list into parts, either in half or by extracting a section with endPos.
(defn sniplist [lis pos & endpos]
  "Snip a list starting at the pos and ending at the endpos."
  (loop [ll lis, ctr 0, col '()]
    (if (>= ctr (if (= nil (first endpos)) (dec (count lis)) (first endpos))) (rlist col)
      (recur
        (rest ll)
        (inc ctr)
        (if (>= ctr pos) (conj col (first ll)) col)))))

;Get information between two list entries, starting at pos.
(defn between [lis pos d1 d2]
  (loop [rf (sniplist lis pos), col '(), state false]
    (if (or (bad? rf) (and state (.contains (first rf) d2)))
      (rlist col)
      (recur
        (rest rf)
        (if state (conj col (first rf)) col)
        (or state (.contains (first rf) d1))))))

(defn loc [lis toLoc]
  "Location of toLoc in the list"
  (loop [ll lis, counter 0]
    (if (or (= (first ll) toLoc) (= ll '()))
      counter 
      (recur (rest ll) (inc counter)))))

(defn found-in? [lis string]
  "Is the string found inside the list?"
	  (cond
	    (= lis ()) false
	    (.contains (first lis) string) true
	    :else (recur (rest lis) string)))
    
(defn found-from? [list string]
  "Is anything from the list found in the string?"
  (cond
    (= list ()) false
    (.contains string (first list)) true
    :else (recur (rest list) string)))

(defn remove-from [lis toRemove]
  "Remove toRemove from the list."
  (loop [ll lis, col '()]
    (if (bad? ll) (rlist col)
      (recur (rest ll)
             (if (= (first ll) toRemove) col
               (conj col (first ll)))))))