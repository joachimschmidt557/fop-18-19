;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname H02_Schmidt_Joachim) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
(require 2htdp/image)

;; Reference matrices: http://biecoll.ub.uni-bielefeld.de/volltexte/2007/52/pdf/ICVS2007-6.pdf
(define m-rgb-to-lms (list (list 17.8824 43.5161 4.1193) (list 3.4557 27.1554 3.8671) (list 0.02996 0.18431 1.4670)))
(define m-lms-to-lms-protanopia (list (list 0 2.02344 -2.52581) (list 0 1 0) (list 0 0 1)))
(define m-lms-to-rgb (list (list 0.0809 -0.1305 0.1167) (list -0.0102 0.0540 -0.1136) (list -0.0003 -0.0041 0.6935)))

;; Hilfsmethode: Skalarprodukt
;; Type: (list of number) (list of number) -> number
;; Returns: the Skalarprodukt of the two lists
;; Precondition: The lists have the same length
(define (vec-mult list1 list2) (
   foldr + 0 (map * list1 list2)
))

;; Eigene Tests
(check-error (vec-mult 2 4))
(check-expect (vec-mult '(1) '(1)) 1)
(check-expect (vec-mult '(1 2 3) '(4 5 6)) 32)

;; H1
;; Type: (list of (list of number)) (list of (list of number)) -> (list of (list of number))
;; Returns: The multiplied matrix
;; Precondition: width of m1 equals height of m2
(define (matrix-multiplication m1 m2) (
  map
  (
    ;; Type: (list of number) -> (list of number)
    ;; Returns: A row of the finished matrix
    lambda (x) (
      map
      (
        ;; Type: (list of number) -> number
        ;; Returns: The Skalarprodukt of the current
        ;; row x and column y
        lambda (y) (
          vec-mult x y
        )
      )
      ;; The columns of the second matrix
      (
        apply map list m2
      )
    )
  )
  ;; The rows of the first matrix
  m1
))

;; Vorgegebene Tests
(check-expect (matrix-multiplication (list (list 4 3) (list 6 3)) (list (list 1 2 3) (list 4 5 6))) (list (list 16 23 30) (list 18 27 36)))
(check-expect (matrix-multiplication (list (list 1 -5 8)  (list 1 -2 1)  (list 2 -1 -5)) (list (list 1 -5 8)  (list 1 -2 1)  (list 2 -1 -5))) (list (list 12 -3 -37) (list 1 -2 1) (list -9 -3 40)))

;; Eigene Tests
(check-error (matrix-multiplication "cause-error"))
(check-expect (matrix-multiplication '((10 20 30) (40 50 60)) '((1 2) (3 4) (5 6))) '((220 280) (490 640)))
(check-expect (matrix-multiplication '((10 20 30) (40 50 60)) '((98) (99) (100))) '((5960) (14870)))

;; H2
;; Type: color -> (list of (list of int))
;; Returns: A vector with the elements of the R, G, B fields
;; of the color struct
(define (color->vector clr) (
  list
    (list (color-red clr))
    (list (color-green clr))
    (list (color-blue clr))
))

;; Eigene Tests
(check-expect (color->vector (make-color 120 55 30)) (list (list 120) (list 55) (list 30)))
(check-expect (color->vector (make-color 0 0 0)) (list (list 0) (list 0) (list 0)))
(check-error (color->vector "cause-error"))

;; Hilfsmethode: confine-to-range
;; Type: number number number -> number
;; Returns: number confined to the interval [min,max]
(define (confine-to-range number min max) (
  cond
    [(< number min) min]
    [(> number max) max]
    [else number]
))

;; Eigene Tests
(check-expect (confine-to-range 25 0 10) 10)
(check-expect (confine-to-range -45 0 10) 0)
(check-expect (confine-to-range 3 0 10) 3)

;; Type: (list of (list of int)) -> color
;; Returns: A color struct consisting of the vector fields
;; for R, G, B
;; Precondition: list has exactly 3 lists of length 1
(define (vector->color vec) (
  make-color
    (floor (confine-to-range (first (first vec)) 0 255))
    (floor (confine-to-range (first (second vec)) 0 255))
    (floor (confine-to-range (first (third vec)) 0 255))
))

;; Eigene Tests
(check-expect (vector->color '((13) (44) (145))) (make-color 13 44 145))
(check-expect (vector->color '((-13) (-14) (300))) (make-color 0 0 255))
(check-expect (vector->color '((13.6) (6.7) (145.7))) (make-color 13 6 145))

;; H3
;; Type: (list of (list of int)) -> (list of (list of int))
;; Returns: The simulated protanopia
;; Precondition: list is a valid non-empty color vector
(define (rgb-vector-protanopia vec) (
  ;; Finally convert to RGB again
  matrix-multiplication
    m-lms-to-rgb
    (
      ;; Then apply Protanopia
      matrix-multiplication
        m-lms-to-lms-protanopia
        ;; First convert to LMS
        (matrix-multiplication m-rgb-to-lms vec)
    )
))

;; Eigene Tests
(check-error (rgb-vector-protanopia "cause-error"))
(check-expect (rgb-vector-protanopia '((123) (344) (66))) '((318.31983305029892) (319.93700102112424) (66.62295409626836)))
(check-expect (rgb-vector-protanopia '((255) (0) (0))) '((28.5831552082818) (28.7266973066196) (1.1561372319594)))


;; H4
;; Type: image -> image | boolean
;; Returns: Protanopia simulated over the image or false if the image is empty
(define (protanopia-recursive img) (
  cond
    [(image? img) (
      if (empty? (image->color-list img)) (#f) (
        color-list->bitmap
          (protanopia-recursive (image->color-list img))
          (image-width img)
          (image-height img)
      )
    )]
    [(list? img) (
      if (empty? (rest img)) (
        ;; The image only contains one element
        list (vector->color (
          rgb-vector-protanopia (
            color->vector (first img)
          )
        ))
      )
      ;else
      (
        cons
          (vector->color (
            rgb-vector-protanopia (
              color->vector (first img)
            )
          ))
          (protanopia-recursive (rest img))
      )
    )]
    [else #f]
))

;; Keine Tests nötig

;; H5
;; Type: image -> image
;; Returns: Protanopia simulated over the image
(define (protanopia-map img) (
  color-list->bitmap
    (
      map 
        ;; Type: color -> color
        ;; Returns: The color with simulated
        ;; protanopia
        (lambda (x) (
          vector->color (
            rgb-vector-protanopia (
              color->vector x
            )
          )
        ))
        (image->color-list img)
    )
    (image-width img)
    (image-height img)
))

;; Keine Tests nötig

;; H6
;; Types: image image number -> number
;; Returns: The percentage of pixels unchanged or changed only slightly
(define (image-similarity img1 img2 max-difference) (
  ;; Get the percentage of changed pixels
  * 100 (
    ;; Fraction of pixels changed
    / (
      ;; Number of changed pixels
      length (
        ;; Remove all pixels which are changed
        ;; more than max-difference
        filter
          ;; Prädikat: change must be smaller
          ;; than max-difference
          (
            ;; Type: (list of color) -> boolean
            ;; Returns: Whether the pixel is changed less than
            ;; max-difference
            lambda (x) (
              >= max-difference
              (
                + 
                (abs (- (color-red (first x)) (color-red (second x))))
                (abs (- (color-green (first x)) (color-green (second x))))
                (abs (- (color-blue (first x)) (color-blue (second x))))
              )
            )
          )
          ;; 
          (map list (image->color-list img1) (image->color-list img2)
        )
      )
    )
    ;; Number of all pixels
    (length (image->color-list img1))
  )
))

;; Keine Tests nötig

;(define input (bitmap/file "rainbow_colors.png"))
;(define protanopia (protanopia-map input))
;(define solution (bitmap/file "solution_rainbow_colors.png"))
;(check-expect (image->color-list protanopia) (image->color-list solution))
;(save-image protanopia "out_rainbow_colors.png")
;
;(image-similarity input solution 5)
