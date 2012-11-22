;libFsoc
;Perform actions on fSoc lists.
(ns lib.fsoc)
(import lib)

(defn fsoc [lis car side]
"Association list lookup If side is true, look in the car."
(loop [ll lis]
	(cond ;((a b))
		(= (firstorest side first (fn [ll] (first (rest ll))) (first ll) ) car) (first ll)
		(bad? ll) nil
	:else (recur (rest ll)))))

(defn fsoc! [inps outs]
"Makes an fsoc list based on inps and outs"
	(loop [linps inps, louts outs, ll ()]
		(if (or (bad? linps) (bad? louts)) (rlist ll)
			(recur
				(rest linps)
				(rest louts)
				(conj ll (list (first linps) (first louts)))))))

(defn fsoc->map [fsoclist side]
"Turn an fsoc list into a map where the car is the lookup key. Side true is for the car to be first."
(loop [map {}, ll fsoclist]
	(if (bad? ll) 
		map 
		(recur 
			(assoc map (keyword (firstorest side first second (first ll))) (firstorest (not side) first second (first ll)))
			(rest ll)))))
