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

;; Type: (list of tutor) number (tutor -> number) -> (list of tutor)
;; Returns: the best combination of tutors that have a total salary less than the number passed in, regarding the given criterion
(define (choose-tutors possible-tutors max-budget criterion) (
   cond
   ;; If no tutors are there
   [(empty? possible-tutors) empty]
   ;; If there is only one tutor
   [(empty? (rest possible-tutors))
    (if (still-in-budget possible-tutors)
      possible-tutors
      empty
   )]
   [else empty]
 ))



(define (budget-consumption tutors)
  (criterion-sum tutors tutor-salary))

(define (still-in-budget tutors max-budget)
  (<= (budget-consumption tutors) max-budget))

(define (criterion-sum tutors criterion)
  (apply + (map criterion tutors)))

;; Tests
(check-expect (choose-tutors all-tutors 2000 tutor-correctness) (list "Konrad"))
(check-expect (choose-tutors all-tutors 10000 tutor-speed) (list "Anton" "Dora" "Emil" "Friedrich" "Ludwig" "Niklaus"))
(check-expect (choose-tutors all-tutors 7777 tutor-correctness) (list "Emil" "Anton" "Julius" "Konrad"))