;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname UE02-V10) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
(require 2htdp/image)

;; count-black-white

(define rkt_img (bitmap/file "Racket.png"))

;; Type: image -> (list of number)
(define (count-black-white img)
  (list (length (filter (lambda (x) (= 0 (color-red x) ) ) (image->color-list img))) (length (filter (lambda (x) (= 255 (color-red x) ) ) (image->color-list img))))
  )

(count-black-white rkt_img)

;; negative-transformation

(define train_img (bitmap/file "train.png"))

;; Type: image -> image
(define (negative-transformation img)
  (color-list->bitmap (map (lambda (x) (make-color (- 255 (color-red x)) (- 255 (color-green x)) (- 255 (color-blue x)) 255) ) (image->color-list img) )
                      (image-width train_img) (image-height train_img))
  )

(save-image (negative-transformation train_img) "train_neg.png")
