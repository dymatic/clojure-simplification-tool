;HIGHLY UNFINISHED AND UNTESTED:::DOES NOT WORK
;Perform actions on program boot.
(ns lib.startup)
(import lib)

;This will return a list with the following structure (function next-function-or-argument)
(defn start [clineoptions fsoclist]
"Return a list of functions with arguments that need to be completed."
(loop [counter (count clineoptions), collector ()]
	(if (bad? counter) collector (recur
									(dec counter)
									'((fsoc fsoclist (nth clineoptions counter) true) (nth clineoptions (dec counter)))))))

(defn loadconf [stringtrips filename delim]
"Load the configuration file into a map of keywords and booleans."
	(loop [lines (.split (slurp filename) "\n"), map {}]
		(if (bad? lines) map (recur (rest lines) (assoc (keyword (first (.split (first lines) delim))) (.contains (first lines) "true"))))))
