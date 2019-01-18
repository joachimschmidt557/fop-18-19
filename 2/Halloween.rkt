;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname Halloween) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
;; (map (lambda (x) (+ 5 x)) (list 1 2 (3))
(define (zip list1 list2) (
   map list list1 list2
))

(define (vec-mult list1 list2) (
   foldr + 0 (map * list1 list2)
))

;(list "a" "b")
;(list 1 2)
(vec-mult (list 1 2 3) (list 4 5 6))


;; number -> (number -> number)
(define (z x)
;; number -> number
(lambda (y) (* x y)))

((lambda (y) (* 3 y)) 4)
((z 3) 4)



(cond [ (= 5 7) (#t) ] [  ])
