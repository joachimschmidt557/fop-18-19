;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname H10_2) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
;; tutor - represents an tutor for fop
;; name: string - the name of the tutor
;; salary: number - the total salary in € for the tutor
;; speed: number - the speed of the tutor between 1 (extremly lazy) and 100 (extremely dedicated)
;; correctness: number - a value describing as how sympathic the tutor is regarded, between 1 (not at all) and 100 (extremely sympathic)
(define-struct tutor (name salary speed correctness))

;; sample tutors
(define T1 (make-tutor "Anton" 1000 50 50))
(define T2 (make-tutor "Berta" 2123 89 22))
(define T3 (make-tutor "Caesar" 1365 45 6))
(define T4 (make-tutor "Dora" 2043 91 41))
(define T5 (make-tutor "Emil" 1982 92 79))
(define T6 (make-tutor "Friedrich" 1456 90 24))
(define T7 (make-tutor "Gustav" 1374 52 50))
(define T8 (make-tutor "Heinrich" 2222 89 17))
(define T9 (make-tutor "Anton" 1823 32 71))
(define T10 (make-tutor "Ida" 1358 44 49))
(define T11 (make-tutor "Julius" 1723 17 88))
(define T12 (make-tutor "Konrad" 1963 66 90))
(define T13 (make-tutor "Ludwig" 1384 74 32))
(define T14 (make-tutor "Martha" 1934 69 18))
(define T15 (make-tutor "Niklaus" 2092 100 46))

;; all items
(define all-tutors (list T1 T2 T3 T4 T5 T6 T7 T8 T9 T10 T11 T12 T13 T14 T15))

;; Type: (list of tutor) number (tutor -> number) -> (list of string)
;; Returns: the best combination of tutors that have a total salary less than the number passed in, regarding the given criterion
(define (choose-tutors possible-tutors max-budget criterion) (
   cond
   ;; If no tutors are there
   [(empty? possible-tutors) empty]
   ;; If all tutors can be afforded
   [(still-in-budget possible-tutors max-budget) (map tutor-name possible-tutors)]
   ;; We have to not employ some tutors
   [else (reverse (map tutor-name (find-optimal-tutor-group possible-tutors '() max-budget criterion)))]
 ))

;; Type: (list of tutor) (list of tutor) number (tutor -> number) -> (list of tutor)
;; Returns: the optimal tutor group which contains "currently-selected-tutors"
(define (find-optimal-tutor-group all-the-tutors currently-selected-tutors max-budget criterion) (
  cond
  [(empty? all-the-tutors) empty]
  [else (extract-maximum-quality
    ;;(filter
     ;; Select the combinations which are affordable
     ;;(lambda (x) (still-in-budget x max-budget))
     ;; All possible selections:
     ;; - Only the current selection
     ;; - The current selection + one more tutor
     (cons
      ;; The current selection
      currently-selected-tutors
      ;; The optimal solutions containing a variation of the
      ;; current selection
      (map (;; Type: (list of tutor) -> (list of tutor)
            ;; Returns: The optimal solutions containing a variation of the
            ;; current selection
            lambda (x) (find-optimal-tutor-group
                        ;;(filter
                 ;;(lambda (x) (not (member x currently-selected-tutors)))
                 all-the-tutors
                 ;;)
                        x
                        max-budget
                        criterion))
       ;; All the combinations of the current tutors +
       ;; one added remaining tutor
       ;; currently-selected-tutors -> (list of (list of tutor))
       (filter (;; Type: (list of tutor) -> boolean
                ;; Returns: whether the constellation of tutors is within the budget
                lambda (x) (still-in-budget x max-budget))
               (map
                (;; Type: tutor -> (list of tutor)
                 ;; Adds tutor x to the selection of tutors
                 lambda (x) (cons x currently-selected-tutors))
                ;; All tutors remaining
                #|
                (filter
                 (lambda (x) (not (member x currently-selected-tutors)))
                 all-the-tutors
                 ) ; end filter
                 |#

                (if (empty? currently-selected-tutors) all-the-tutors
                (rest (memv (first currently-selected-tutors) all-the-tutors)))
                ) ; end map
                
       )
    ;;) criterion)
    )) criterion)
  ]
))

(check-expect (find-optimal-tutor-group '() '() 100 tutor-speed) '())
(check-expect (find-optimal-tutor-group all-tutors (list T1) 1000 tutor-speed) (list T1))
(check-expect (find-optimal-tutor-group all-tutors (list T4) 4000 tutor-correctness) (list T11 T4))

;; Type: (list of (list of tutor)) (tutor -> number) -> (list of tutor)
;; Returns: The combination of tutor with the highest sum of criterion
(define (extract-maximum-quality tutors-options criterion) (
  cond
  [(empty? tutors-options) empty]
  [else (foldl
    ;; If option x is better, replace by x, else replace by y
    (;; Type: (list of tutor) (list of tutor) -> (list of tutor)
     ;; Returns: Whichever constellation of tutors is better
     lambda (x y) (
       if (> (criterion-sum x criterion) (criterion-sum y criterion))
       x
       y
    ))
    ;; Initial value: first element
    (first tutors-options)
    ;; List to iterate over
    (rest tutors-options))
  ]
))

(check-expect (extract-maximum-quality '() tutor-speed) '())
(check-expect (extract-maximum-quality (list (list T1)) tutor-correctness) (list T1))
(check-expect (extract-maximum-quality (list (list T1) (list T2 T3) (list T6 T9 T4)) tutor-speed) (list T6 T9 T4))

;; Type: (list of tutor) -> number
;; Returns: The budget consumption of this collection of tutors
(define (budget-consumption tutors)
  (criterion-sum tutors tutor-salary))

(check-expect (budget-consumption '()) 0)
(check-expect (budget-consumption (list T1)) 1000)
(check-expect (budget-consumption (list T7 T9)) 3197)

;; Type: (list of tutor) number -> boolean
;; Returns: Whether the budget consumption of this collection of tutors is still
;; within the maximum budget
(define (still-in-budget tutors max-budget)
  (<= (budget-consumption tutors) max-budget))

(check-expect (still-in-budget '() 0) #t)
(check-expect (still-in-budget (list T1) 400) #f)
(check-expect (still-in-budget (list T2 T4) 10000) #t)

;; Type: (list of tutor) (tutor -> number) -> number
;; Returns: The sum of properties of this collection of tutors
(define (criterion-sum tutors criterion)
  (apply + (map criterion tutors)))

(check-expect (criterion-sum '() tutor-speed) 0)
(check-expect (criterion-sum (list T8 T3) tutor-correctness) 23)
(check-expect (criterion-sum (list T8 T3) tutor-speed) 134)

;; Tests
(check-expect (choose-tutors all-tutors 2000 tutor-correctness) (list "Konrad"))
(check-expect (choose-tutors all-tutors 10000 tutor-speed) (list "Anton" "Dora" "Emil" "Friedrich" "Ludwig" "Niklaus"))
(check-expect (choose-tutors all-tutors 7777 tutor-correctness) (list "Emil" "Anton" "Julius" "Konrad"))

