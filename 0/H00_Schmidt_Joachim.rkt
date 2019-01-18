;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname H00_Schmidt_Joachim) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
;; Type: int string string -> string
;; Returns: A correct filename for the given Übung, ln (last name) and fn (first name)
(define (generate-filename nr ln fn)
  ;; Append String
  (
    string-append "H"   ; The initial character H
    (
      if (< nr 10)          ;If the number is from 0-9, add a trailing zero
      (
        string-append "0" (number->string nr)
      )
      ;else                 Else, just add the number
      (
        number->string nr
      )
    )
    "_"                 ; Underscore for separation
    ln                  ; Nachname
    "_"                 ; Underscore for separation
    fn                  ; Vorname
    ".rkt"              ; File extension 
  )
  )

; Test 3 times
; Test with correct input and Übung < 10
(check-expect (generate-filename 0 "Schmidt" "Joachim") "H00_Schmidt_Joachim.rkt")
; Test with string instead of int
(check-error (generate-filename "asdf" "Schmidt" "Joachim"))
; Test with correct input and Übung > 10
(check-expect (generate-filename 12 "Fritzgerald" "Heinz-Müller") "H12_Fritzgerald_Heinz-Müller.rkt")
