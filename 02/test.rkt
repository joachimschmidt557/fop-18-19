;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname test) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
;#lang racket
;(define (fib n) (
;    if (= n 0)
;        0
;    (
;        if (= n 1)
;        1
;        (+ (fib (- n 1)) (fib (- n 2)))
;    )
;))
;
;(fib 10)
;
;
;(define (fib n) (
;    (cond
;        [(= n 0) 0]
;        [(= n 1) 1]
;        [else
;            (+ (fib (- n 1)) fib (- n 2))
;        ]
;    )
;))
;
;(fib 10)

;(define list1 ( list 1 2 3 4 ))
;
;(define (sum list) (
;    if (empty? list)
;        0
;    ;else
;    (
;        +
;        (first list)
;        ( sum(rest list) )
;    )
;))
;
;(sum list1)

;(define (list-with-length len) (
;        if (= len 0)
;            empty
;        (
;            cons 0 (list-with-length (- len 1) )
;        )
;))
;
;(list-with-length 10)

;(filter (lambda (x) (> x 10) ) (list 0 5 10 15 20) )

;(map (lambda (x) (+ x 10)) (list 0 1 2 3) )

;(define (repeat fct times) (
;    
;))

(define-struct abc (a b))

;; Type: abc -> list
;; Returns: bla bla
(define (foo p) (
    ;(
        if (abc? p)
        (
            if (list? (abc-b p))
            ;(
                (list (abc-a p) (abc-b p))
            ;)
            ;else
            ;(
                #f
            ;)
        )
        ; else
        #f
    ;)
))
;
;Variante 2 - mit (and ... ...)
;
;Variante 3 - mit (cond [])
;
;
;;(define test (make-abc ("asdf" (list 1 2 3 4))))
;
;(foo (make-abc 123 (list 1 2 3 4)))
;
;(foo 123)
;
;(foo (make-abc 123 1234))
;

;(define (contains-x? liste x) (
;    cond
;        [(list? x) #f]
;        [(not (list? liste)) #f]
;        [(empty? liste) #f]
;        [
;            (= (first liste) x)
;            #t
;        ]
;        [
;            else
;            (contains-x? (rest liste) x)
;        ]
;    
;))
;
;(contains-x? (list 12) (list 12 5))


;(define (contains-x? liste x) (
;    cond
;        [(list? x) #f]
;        [(not (list? liste)) #f]
;        [(empty? liste) #f]
;        [
;            (= (first liste) x)
;            #t
;        ]
;        [
;            else
;            (contains-x? (rest liste) x)
;        ]
;    
;))
;
;(define (duplicate? lst) (
;    cond
;        [(not (list? lst)) #f]
;        [(empty? lst) #f]
;        [
;            (contains-x? (rest lst) (first lst))
;            #t
;        ]
;        [
;            else
;            (duplicate? (rest lst))
;        ]
;))
;
;(duplicate? (list 1 2 3 4 5 6 7 3))


;(define (contains-x? liste x) (
;    cond
;        [(list? x) #f]
;        [(not (list? liste)) #f]
;        [(empty? liste) #f]
;        [
;            (= (first liste) x)
;            #t
;        ]
;        [
;            else
;            (contains-x? (rest liste) x)
;        ]
;    
;))
;
;(define (duplicate? lst) (
;    cond
;        [(not (list? lst)) #f]
;        [(empty? lst) #f]
;        [
;            (contains-x? (rest lst) (first lst))
;            #t
;        ]
;        [
;            else
;            (duplicate? (rest lst))
;        ]
;))
;
;(define (collect lst oracle) (
;    cond
;        [(empty? lst) oracle]
;        [
;            else
;            (if (list? (first lst))
;            (collect (rest lst) (collect (first lst) oracle))
;            (
;                collect (rest lst) (cons (first lst) oracle)
;            ))
;        ]
;))
;
;(define (collect lst oracle) (
;    cond
;        [(empty? lst) oracle]
;        [(list? (first lst)) (
;            collect (first lst) (collect (rest list ) oracle)
;        )]
;        [
;            else
;                (collect (rest lst) (cons (first lst) oracle))
;        ]
;))
;
;(define (duplicates-deep? deep-lst) (
;    duplicates? (collect deep-lst '())
;))
;
;(collect (list 1 2 3 (list 1 4 6)) empty)
;
;;(define (duplicates-deep? deep-lst) (
;;
;;))
;
;
;;; Type: ( list of ANY ) -> number
;;; Returns: the sum of the fees of all student in the list
;( define ( sum-of-student-fees list )
;( my-fold
;( lambda ( x y ) ( + x y ) )
;0
;( my-map
;( lambda ( stud ) ( student-fee stud ) )
;( my-filter ( lambda ( x ) ( student? x ) ) list ) ) ) )


;(define asdf 10)
;(define aaa 20)
;
;(define (add x y) (+ x y))
;
;(check-expect (add asdf asdf) aaa)

;;; (define (euclid a b) (
;;;     cond
;;;     [(= b 0) a] ; Rekursionsanker
;;;     [(= a 0) b]
;;;     [(> a b) (
;;;         euclid (- a b) b
;;;     )]
;;;     [else (euclid a (- b a))]
;;; ))

;;; (euclid 50 5)

;;; (and #t #t)

;(define (bar1 lst) (
;    map foo (filter abc? lst)
;))
;
;(bar1 (list (make-abc 1 (list 2)) (make-abc 3 4) 1231342154))
;
(define (cartesian-prod list1 list2) (
    map
        (lambda (y) (map (lambda (x) (list y x)) list2))
        list1
))
;
;;(define (cartesian-prod list1 list2) (
;;    cond
;;    [(and (not (empty? list1)) (not (empty? list2)))
;;        cons (list (first list1) (first list2))
;;    ]
;;    
;;))
;
(cartesian-prod (list 1 2)(list 3 4))

;;;(define ( proof lst r g b ) (
;;;    cond
;;;    [(= 0 r g b) (map + (list 0 1) lst)]
;;;    [(= 255 r g b) (map + (list 1 0) lst)]
;;;    [else lst]
;;;))
;;;
;;;(define (count-black-white img) (
;;;    (count-black-white-of-list (image->color-list img))
;;;))
;;;
;;;(define (count-black-white-of-list lst) (
;;;    cond
;;;    [(empty? lst) (list 0 0)]
;;;   
;;;))

(define (count-black-white img) (
    list 
        (length (filter (lambda (x) (= 0 (color-red x) (color-green x) (color-blue x)))
            (image->color-list img)
        ))
        (length (filter (lambda (x) (= 255 (color-red x) (color-green x) (color-blue x)))
            (image->color-list img)
        ))
))


(define (negative-transformation img) (
    color-list->bitmap (
        map (lambda (x) (
            make-color
                (- 255 (color-red x))
                (- 255 (color-green x))
                (- 255 (color-blue x))
        ))
        (image->color-list img)
    )
    (image-width img)
    (image-height img)
))


;; V9

(define (passed ids points) (
    (map first (
        filter
        (
            lambda (entry) (
                and
                (>= (second entry) 50)
                (>= (third entry) 35)
                (>= (fourth entry) 50)
                (>= (
                    +
                    (second entry)
                    (third entry)
                    (fourth entry)
                ) 180)
            )
            (map cons ids points)
        )
    ))
))