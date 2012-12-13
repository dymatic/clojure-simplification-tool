;LibFunctional
;specific implementations for other library functions, like bad?.
(ns lib.libFunctional)


;If condition is true, perform function 1 on the list. Else, call function 2 on the list.
(defn firstorest [condition fa fb lis]
  "If condition, get the first of the list. Else, the rest."
  (if condition (fa lis) (fb lis)))

;Logical implementation of xor. If at least one of a and b are true, but not both.
(defn xor [a b] "One, but not both," (and (not (and a b)) (or a b)))

;Do nothing, returning whatever is supplied as an argument.
(defn fake [& x] (first x))

;Check to see that a condition has not expired in a recursion.
(defn bad? [condition]
  "Is the condition 0, nil, false, (), (())"
  (or (orlist condition '(0, nil, false, ())) (empty? (flatten (list condition)))))


