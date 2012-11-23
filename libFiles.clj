;LibFiles
;perform actions on on files, writing or extracting information.
(ns lib.libFiles)
(import lib)

;Split a file in half when the delimiter is found.
(defn file-split [filename spliton]
  "Split a file on a delimiter"
  (loop [ll (lines filename), col '(), buff '()]
    (if
      (bad? ll) (conj col buff)
      (recur (rest ll) 
             (if (.contains (first ll) spliton) (conj col buff) col)
             (if (not (.contains (first ll) spliton)) (conj buff (first ll)) '())))))

;Splits every line on toSplit
;This will create nested lists
(defn gfile-split [file toSplit]
  "Splits the entire file on toSplit, making fsoc lists."
  (loop [lines (.split (slurp file) "\n"), ll ()]
    (if (bad? lines) ll (recur
                          (rest lines)
                          (conj ll (.split (first lines) toSplit))))))

;Get a specific numbered line from a file
(defn getline [file line]
  "Gets the line number line from file."
  (nth (.split (slurp file) "\n") line))

;Get the complete list of lines from a file.
(defn lines [filename]
  "Get the lines of a file in a list."
  (.split (slurp filename) "\n"))

;Break a file in half at a specified point. Then, end at the endpos if specified.
(defn subfile [filename pos & endpos]
  "Breaks a file in half at a specified point or endpos"
  (sniplist (lines filename) pos (first endpos)))

;Gets the lines in between d1 and d2, starting at pos where pos is the line to start.
(defn lines-between [file pos d1 d2]
  "Get the lines between d1 and d2 in a file"
  (between (lines file)
           pos
           d1
           d2))