;libFsoc
;Perform actions on fSoc lists.
(ns lib.fsoc
  (use 'lib.libFunctional)
  (use 'lib.libLists))


(defn fsoc [lis car side]
"Association list lookup If side is true, look in the car."
(loop [ll lis]
	(cond ;((a b))
		(= (firstorest side first (fn [ll] (first (rest ll))) (first ll) ) car) (first ll)
		(= ll ()) nil
	:else (recur (rest ll)))))

(defn fsoc! [inps outs]
"Makes an fsoc list based on inps and outs"
	(loop [linps inps, louts outs, ll ()]
		(if (or (= () linps) (= () louts)) (rlist ll)
			(recur
				(rest linps)
				(rest louts)
				(conj ll (list (first linps) (first louts)))))))

