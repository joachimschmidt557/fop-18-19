;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname V09) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
(define (passed ids points)
  (map first ;; use only student ID
       (filter
        ;; returns true if passed
        (lambda (entry)
          (and (>= (second entry) 50)
               (>= (third entry) 35)
               (>= (fourth entry) 50)
               (>= (+ (second entry) (third entry) (fourth entry)) 180)))
        (map cons ids points))))

(check-expect (passed '(1 2) '((51 34 99) (70 40 70))) '(2))
(check-expect (passed '(1 2 3) '((51 34 99) (70 40 70) (50 35 50))) '(2))