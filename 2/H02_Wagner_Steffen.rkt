;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname H02_Wagner_Steffen) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
(require 2htdp/image)

;; Reference matrices: http://biecoll.ub.uni-bielefeld.de/volltexte/2007/52/pdf/ICVS2007-6.pdf
(define m-rgb-to-lms (list (list 17.8824 43.5161 4.1193) (list 3.4557 27.1554 3.8671) (list 0.02996 0.18431 1.4670)))
(define m-lms-to-lms-protanopia (list (list 0 2.02344 -2.52581) (list 0 1 0) (list 0 0 1)))
(define m-lms-to-rgb (list (list 0.0809 -0.1305 0.1167) (list -0.0102 0.0540 -0.1136) (list -0.0003 -0.0041 0.6935)))

;;(define -struct color (red green blue alpha))

;; H1
;; Type: matrix matrix -> matrix
;; Returns: matrixmultiplication of m1 and m2

(define (matrix-multiplication m1 m2) 
  (map ( ; lambda seperates m1 in 2 lists of numbers 
        lambda (d1) (foldl (;lambda makes the addition in the matrix
                            lambda(c1 result)(map + c1 result)) (first (map (;lambda seperates d1 in the numbers and m2 in lists of numbers
                                                                             lambda(b1 b2)( map (;lambda seperates b2 and makes the multiplikation
                                                                                                 lambda(a1)(round(* a1 b1)) ) b2))  d1 m2)) (rest (map (;lambda seperates d1 in the numbers and m2 in lists of numbers
                                                                                                                                                 lambda(b1 b2)( map (;lambda seperates d1 in the numbers and m2 in lists of numbers
                                                                                                                                                                     lambda(a1)(round(* a1 b1) )) b2))  d1 m2)))) m1)
  )  

(check-expect (matrix-multiplication (list (list 4 3) (list 6 3)) (list (list 1 2 3) (list 4 5 6))) (list (list 16 23 30) (list 18 27 36)))
(check-expect (matrix-multiplication (list (list 1 -5 8)  (list 1 -2 1)  (list 2 -1 -5)) (list (list 1 -5 8)  (list 1 -2 1)  (list 2 -1 -5))) (list (list 12 -3 -37) (list 1 -2 1) (list -9 -3 40)))

;; H2
;; Type:color -> vector
;; Returns: the color as vector
(define (color->vector clr)
  (list (list (color-red clr)) (list (color-green clr)) (list (color-blue clr)))
)
(check-expect (color->vector (make-color 1 2 3 4)) (list (list 1) (list 2) (list 3)))
(check-expect (color->vector (make-color 255 254 253 4)) (list (list 255) (list 254) (list 253)))

;; Type: vector -> color
;; Returns:the color as color struct
(define (vector->color vec)
  (make-color (first (first vec)) (first (second vec)) (if (> (first (third vec)) 255) 255 (first (third vec))) 255)
  )

(check-expect (vector->color (list (list 1) (list 2) (list 3))) (make-color 1 2 3 255))
(check-expect (vector->color (list (list 255) (list 254) (list 253))) (make-color 255 254 253 255)) 

;; H3
;; Type: rgb-vector
;; Returns:
(define (rgb-vector-protanopia vec)
  (matrix-multiplication m-lms-to-rgb (matrix-multiplication m-lms-to-lms-protanopia (matrix-multiplication m-rgb-to-lms vec)))
  )

;; H4
;; Type: bitmap -> bitmap
;; Returns: returns a bitmap with protanopia
(define (protanopia-recursive img)
  (color-list->bitmap (protanopia-recursive-colorlist(image->color-list img)) (image-width img) (image-height img))
  )

(define (protanopia-recursive-colorlist lst)
  (append (list (vector->color(rgb-vector-protanopia(color->vector(first lst))))) (if (empty? (rest lst)) '() (protanopia-recursive-colorlist (rest lst))))
  )

(define input (bitmap/file "rainbow_colors.png"))

;; H5
;; Type: bitmap -> bitmap
;; Returns: returns a bitmap with protanopia 
(define (protanopia-map img)
  (color-list->bitmap (map (lambda (x) (vector->color(rgb-vector-protanopia(color->vector x)))) (image->color-list img)) (image-width img) (image-height img))
)


(define protanopia (protanopia-map input))
(define solution (bitmap/file "solution_rainbow_colors.png"))
(check-expect protanopia solution)
(save-image protanopia "out_rainbow_colors.png")

;; H6
;; Types:
;; Returns:
(define (image-similarity img1 img2 max-difference)
  #f
  )
