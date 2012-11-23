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
  (loop [ll lis]
    (cond
      (= (first ll) condition) true
      (= ll ()) false
      :else (recur (rest ll)))))

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
    (if (or (and state (.contains (first rf) d2)) (bad? rf))
      (rlist col)
      (recur
        (rest rf)
        (if state (conj col (first rf)) col)
        (or state (.contains (first rf) d1))))))

