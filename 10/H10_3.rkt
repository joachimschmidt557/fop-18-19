;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname H10_3) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
(define-struct queen (x y))

;; Type: queen (list of queen) -> boolean
;; Returns: true if a queen can be attacked by a list of other queens
;; example: (under-attack (make-queen 1 1) (list (make-queen 1 2))) is true
(define (under-attack new-queen queens)
  (cond
    ;; Modified to throw false on empty queen
    [(null? new-queen) false]
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

(check-expect (under-attack '() '()) false)
(check-expect (under-attack (make-queen 3 4) '()) false)
(check-expect (under-attack (make-queen 3 4) (list (make-queen 2 4))) true)

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

(check-expect (one-to-n 0) '(1))
(check-expect (one-to-n 4) '(1 2 3 4))
(check-expect (one-to-n 7) '(1 2 3 4 5 6 7))

;; Type: number number -> (list of queen)
;; Returns: All possible queens on this row
(define (make-new-queens-at-row n row) (
  map (;; Type: number -> queen
       ;; Returns: a new queen at row row and column x
       lambda (x) (make-queen row x)) (one-to-n n)
))

(check-expect (make-new-queens-at-row 1 1) (list (make-queen 1 1)))
(check-expect (make-new-queens-at-row 2 2) (list (make-queen 2 1) (make-queen 2 2)))
(check-expect (make-new-queens-at-row 3 3) (list (make-queen 3 1) (make-queen 3 2) (make-queen 3 3)))

;; Type: number number (list of queen)
;; Returns: All valid queens on this row
(define (valid-queens-at-row n row queens) (
  filter (;; Type: queen -> boolean
          ;; Returns: Whether this queen is under attack
          lambda (x) (not (under-attack x queens))) (make-new-queens-at-row n row)
))

(check-expect (valid-queens-at-row 1 1 '()) (list (make-queen 1 1)))
(check-expect (valid-queens-at-row 1 1 (list (make-queen 1 1))) '())
(check-expect (valid-queens-at-row 2 2 (list (make-queen 1 1))) '())

;; Type: number number (list of queen) (list of (list of queen)) -> (list of (list of queen))
;; Returns: All possible solutions for inserting queens on this row and the rows below this row
(define (recursive-row-insert n row queens-placed solutions) (
  cond
  ;; We managed to finish with all queens not interfering!
  ;; Return all queens placed to achieve this constellation
  [(< n row) (cons queens-placed solutions)]
  ;; This is a Sackgasse, no more queens possible at this row
  ;; Return false so that the calling method realizes there is no more way
  [(> 1 (length (valid-queens-at-row n row queens-placed))) #f]
  ;; If there are viable options to place queens, proceed with recursion
  ;; Filter out the non-correct options
  [else (apply append (filter (;; Type: (list of (list of queen)) | boolean -> boolean
                               ;; Returns: Whether this is a real option and not #f
                               lambda (x) (not (false? x)))
                (map
                 ;; What we want here is to go further from all valid queens
                 ;; For each valid queen, we now get a list of solutions originating
                 ;;  from that position

                 ;; Type: (list of queen) -> (list of (list of queen))
                 ;; Returns: The possible solutions when inserting a row below
                 (lambda (x) (recursive-row-insert n (+ row 1) (cons x queens-placed) solutions))
                 ;; All the valid queens on this row
                 (valid-queens-at-row n row queens-placed))))]
  
))

(check-expect (recursive-row-insert 1 1 '() '()) (list (list (make-queen 1 1))))
(check-expect (recursive-row-insert 2 1 '() '()) '())
(check-expect (recursive-row-insert 4 4 (list (make-queen 1 3) (make-queen 2 1) (make-queen 3 4)) '())
              (list (list (make-queen 4 2) (make-queen 1 3) (make-queen 2 1) (make-queen 3 4)))) 

(check-expect (n-queens 1) 1)
(check-expect (n-queens 2) 0)
(check-expect (n-queens 3) 0)
(check-expect (n-queens 4) 2)
(check-expect (n-queens 8) 92)
(check-expect (n-queens 9) 352)
(check-expect (n-queens 10) 724)
