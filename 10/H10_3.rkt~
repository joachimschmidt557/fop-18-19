;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname H10_3) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
(define-struct queen (x y))

;; Type: queen (list of queen) -> boolean
;; Returns: true if a queen can be attacked by a list of other queens
;; example: (under-attack (make-queen 1 1) (list (make-queen 1 2))) is true
(define (under-attack new-queen queens)
  (cond
    [(empty? queens) false]
    [else
     (or
      ;; queens on same row
      (= (queen-x new-queen) (queen-x (first queens)))
      ;; queens on same col
      (= (queen-y new-queen) (queen-y (first queens)))
      ;; queens on same diagonal
      (= (abs (- (queen-x new-queen) (queen-x (first queens))))
         (abs (- (queen-y new-queen) (queen-y (first queens)))))
      ;; next queen
      (under-attack new-queen (rest queens)))]))


;; Type: number -> number
;; Returns: the number of solutions of the n-queens problem for board size nxn
(define (n-queens n) (
  cond
  [(> 1 n) 0]
  [(= 1 n) 1]
  [else (length (recursive-row-insert n 1 '() '()))]
))

;; Type: number -> (list of number)
;; Returns: a list counting from one to the number
;; example: 5 -> '(1 2 3 4 5)
(define (one-to-n n) (if (<= n 1) '(1) (append (one-to-n (- n 1)) (list n))))

;; Type: number number -> (list of queen)
;; Returns: All possible queens on this row
(define (make-new-queens-at-row n row) (
  map (lambda (x) (make-queen row x)) (one-to-n n)
))

;; Type: number number (list of queen)
;; Returns: All valid queens on this row
(define (valid-queens-at-row n row queens) (
  filter (lambda (x) (not (under-attack x queens))) (make-new-queens-at-row n row)
))

(define (recursive-row-insert n row queens-placed solutions) (
  cond
  ;; We managed to finish with all queens not interfering!
  ;; Return all queens placed to achieve this constellation
  ;;;;;[(< n row) queens-placed]
  ;;;;;[(< n row) (+ 1 solutions)]
  [(< n row) (cons queens-placed solutions)]
  ;; This is a Sackgasse, no more queens possible at this row
  ;; Return false so that the calling method realizes there is no more way
  [(> 1 (length (valid-queens-at-row n row queens-placed))) #f]
  ;; If there are viable options to place queens, proceed with recursion
  ;; Filter out the non-correct options
  [else (apply append (filter (lambda (x) (not (false? x)))
                (map
                 ;; What we want here is to go further from all valid queens
                 ;; For each valid queen, we now get a list of solutions originating
                 ;;  fromt that position
                 (lambda (x) (recursive-row-insert n (+ row 1) (cons x queens-placed) solutions))
                 ;; All the valid queens on this row
                 (valid-queens-at-row n row queens-placed))))]
  
))

#|
(define (recursive-search n queens-placed-so-far possible-solutions-so-far) (
  cond
  ;; Place the first queen
  ;; Try all possible fields'of the first row
  [(empty? queens-placed-so-far) (
    (make-queen 1)
  )]
  ;; We are finished
  [(= (length queens-placed-so-far) n) possible-solutions-so-far]
  (filter (lambda (x) (not (under-attack x))) queens-placed-so-far)
))
|#


(check-expect (n-queens 1) 1)
(check-expect (n-queens 2) 0)
(check-expect (n-queens 3) 0)
(check-expect (n-queens 4) 2)
(check-expect (n-queens 8) 92)
(check-expect (n-queens 9) 352)
(check-expect (n-queens 10) 724)
