;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname H01_Schmidt_Joachim) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
(define-struct binary-tree-node (value left right))

;; example tree 1
(define tree-small (make-binary-tree-node
 5
 (make-binary-tree-node 3 (make-binary-tree-node 1 '() '()) (make-binary-tree-node 4 '() '()))
 (make-binary-tree-node 9 (make-binary-tree-node 6 '() '()) (make-binary-tree-node 11 '() '()))))

;; example tree 2
(define tree-middle (make-binary-tree-node
 6
 (make-binary-tree-node 3 (make-binary-tree-node 1 '() '()) (make-binary-tree-node 4 '() '()))
 (make-binary-tree-node 12 (make-binary-tree-node 8 '() (make-binary-tree-node 9 '() '())) (make-binary-tree-node 14 '() '()))))

;; example tree 3
(define tree-large (make-binary-tree-node
 20
 (make-binary-tree-node
  19
  (make-binary-tree-node
   14
   (make-binary-tree-node 8 (make-binary-tree-node 2 '() '()) (make-binary-tree-node 12 (make-binary-tree-node 10 '() '()) '()))
   '())
  '())
 (make-binary-tree-node
  23
  '()
  (make-binary-tree-node
   24
   '()
   (make-binary-tree-node
    29
    '()
    (make-binary-tree-node
     44
     (make-binary-tree-node 31 '() (make-binary-tree-node 43 (make-binary-tree-node 33 '() '()) '()))
     (make-binary-tree-node 77 (make-binary-tree-node 47 '() '()) '())))))))


;; H1
;; Type: number binary-tree-node -> boolean
;; Returns: Whether the binary tree with root node btn
;; contains value
(define (binary-tree-contains? value btn)
  (
    cond
    ;; If the node we are looking at is equal to value,
    ;; voila, the btn contains value
    [(= value (binary-tree-node-value btn) )  #t]
    ;; If value we are looking for is smaller than btn.value, go to the left
    [(< value (binary-tree-node-value btn) ) (
      ;; Check if the value on the left even exists
      if (empty? (binary-tree-node-left btn))
        ;; At this point, we've reached the end of the binary tree
        ;; and it's safe to say value is not included
        #f
      ;; else
      (binary-tree-contains? value (binary-tree-node-left btn))
    )]
    [(> value (binary-tree-node-value btn) ) (
      ;; Check if the value on the right exists
      if (empty? (binary-tree-node-right btn))
         #f
      ;;else
      (binary-tree-contains? value (binary-tree-node-right btn))
    )]
    ;; If the value is wrong, we land here
    [else #f]
  )
)

;; Vorgegebene Tests
(check-expect (binary-tree-contains? 5 (make-binary-tree-node 3 empty (make-binary-tree-node 5 empty empty))) true)
(check-expect (binary-tree-contains? 12 tree-small) false)
(check-expect (binary-tree-contains? 14 tree-middle) true)

;; Eigene Tests
;; H1 T1: Searching for a value in an empty binary tree,
;;  Vertrag nicht ausgefüllt, also error
(check-error
  (binary-tree-contains? 42 '())
)
;; H1 T2: Searching for a value in a binary tree with 1 node, contains value
(check-expect
  (binary-tree-contains? 1331 (make-binary-tree-node 1331 '() '()))
  #t
)
;; H1 T3: Searching for a value in a larger binary tree, doesn't contain value
(check-expect
  (binary-tree-contains? 8831
    (
      make-binary-tree-node
      123
      (
        make-binary-tree-node
        45
        (
          make-binary-tree-node
          30
          '()
          '()
        )
        '()
      )
      (
        make-binary-tree-node
        234
        '()
        (
          make-binary-tree-node
          999
          '()
          '()
        )
      )
    )
  )
  #f
)

;; H2
;; Type: number binary-tree-node -> binary-tree-node
;; Returns: A new binary tree (root node) which contains the value
;; inserted at the correct location
(define (insert-into-binary-tree value btn)
  (
    cond
    ;; If btn == empty, then make a new binary tree with value
    [(empty? btn) (
      make-binary-tree-node value empty empty
    )]
    [(= value (binary-tree-node-value btn)) (
      ;; value is already inside btn
      ;; return the btn unchanged
      make-binary-tree-node
      (binary-tree-node-value btn)
      (binary-tree-node-left btn)
      (binary-tree-node-right btn)
    )]
    [(< value (binary-tree-node-value btn)) (
      ;; value is smaller than btn.value
      ;; which means: go left
      if (empty? (binary-tree-node-left btn)) (
        ;; Good, the tree doesn't contain value
        ;; Insert the value
        ;; Return the current node with the inserted value
        make-binary-tree-node
          (binary-tree-node-value btn) ;value stays unchanged
          (make-binary-tree-node value '() '()) ;insert new node at the left side
          (binary-tree-node-right btn) ;right side stays unchanged
      )
      ;;else
      (
        ;; Good, we need to keep searching
        ;; Return the current node with the inserted value somewhere
        make-binary-tree-node
          (binary-tree-node-value btn) ;value stays unchanged
          (insert-into-binary-tree value (binary-tree-node-left btn)) ;recursive function
          (binary-tree-node-right btn) ;right side stays unchanged
      )
    )]
    [(> value (binary-tree-node-value btn)) (
      ;; value > btn.value
      ;; go right
      if (empty? (binary-tree-node-right btn)) (
        make-binary-tree-node
          (binary-tree-node-value btn)
          (binary-tree-node-left btn)
          (make-binary-tree-node value '() '())
      )
      ;; else
      (
        make-binary-tree-node
          (binary-tree-node-value btn)
          (binary-tree-node-left btn)
          (insert-into-binary-tree value (binary-tree-node-right btn))
      )
    )]
    [else #f]
  )
)

;; Vorgegebene Tests
(check-expect (insert-into-binary-tree 100 empty) (make-binary-tree-node 100 empty empty))

(check-expect (insert-into-binary-tree 44 tree-small) (make-binary-tree-node
 5
 (make-binary-tree-node 3 (make-binary-tree-node 1 '() '()) (make-binary-tree-node 4 '() '()))
 (make-binary-tree-node 9 (make-binary-tree-node 6 '() '()) (make-binary-tree-node 11 '() (make-binary-tree-node 44 '() '()))))) 

(check-expect (insert-into-binary-tree 6 tree-large) (make-binary-tree-node
 20
 (make-binary-tree-node
  19
  (make-binary-tree-node
   14
   (make-binary-tree-node
    8
    (make-binary-tree-node 2 '() (make-binary-tree-node 6 '() '()))
    (make-binary-tree-node 12 (make-binary-tree-node 10 '() '()) '()))
   '())
  '())
 (make-binary-tree-node
  23
  '()
  (make-binary-tree-node
   24
   '()
   (make-binary-tree-node
    29
    '()
    (make-binary-tree-node
     44
     (make-binary-tree-node 31 '() (make-binary-tree-node 43 (make-binary-tree-node 33 '() '()) '()))
     (make-binary-tree-node 77 (make-binary-tree-node 47 '() '()) '())))))))

;; Eigene Tests
;; H2 T1: Insert into empty
(
  check-expect
  (insert-into-binary-tree 78482 '())
  (
    make-binary-tree-node
    78482
    '()
    '()
  )
)
;; H2 T2: Insert into binary tree, value is already there
(
  check-expect
  (insert-into-binary-tree 11235 (
    make-binary-tree-node
    42
    (
      make-binary-tree-node
      41
      '()
      '()
    )
    (
      make-binary-tree-node
      100
      '()
      (
        make-binary-tree-node
        11235
        '()
        '()
      )
    )
    )
  )
  (
    make-binary-tree-node
    42
    (
      make-binary-tree-node
      41
      '()
      '()
    )
    (
      make-binary-tree-node
      100
      '()
      (
        make-binary-tree-node
        11235
        '()
        '()
      )
    )
  )
)
;; H2 T3: Insert into binary tree, value is not there yet
(
  check-expect
  (
    insert-into-binary-tree 12
    (
      make-binary-tree-node
      15
      '()
      '()
    )
  )
  (
    make-binary-tree-node
    15
    (
      make-binary-tree-node
      12
      '()
      '()
    )
    '()
  )
)


;; H3
;; Type: (list of number) -> binary-tree-node || boolean
;; Returns: A binary tree which contains all the elements
;; of the list or false if the list is empty
(define (binary-tree-from-list lst)
  ;; Insert code here
  (
    cond
    ;; If the list is empty, return false
    [(empty? lst) #f]
    ;; If the list only contains one element,
    ;; create a new binary-tree-node with just this value
    [(empty? (rest lst)) (
      insert-into-binary-tree (first lst) '()
    )]
    ;;; Insert the first element into the rest of the list
    ;;; converted to btn
    ;[else (
    ;  insert-into-binary-tree
    ;    (first lst)
    ;    (binary-tree-from-list (rest lst))
    ;)]
    ;; Insert the rest of the list into the first element
    ;; converted to btn
    [else (
      foldl
        (lambda (x y) (insert-into-binary-tree x y)) ;Procedure: insert value into the binary tree
        (insert-into-binary-tree (first lst) '()) ;Init: the first item
        (rest lst) ;The list
    )]
  )
)

;; Vorgegebene Tests
(check-expect (binary-tree-from-list (list 9 12 13)) (make-binary-tree-node 9 empty (make-binary-tree-node 12 empty (make-binary-tree-node 13 empty empty))))
(check-expect (binary-tree-from-list (list 20 23 19 14 24 29 44 8 12 31 77 47 43 33 10 2)) tree-large)
(check-expect (binary-tree-from-list (list 5 9 3 11 6 4 1)) tree-small)

;; Eigene Tests
;; H3 T1: Empty list
(
  check-expect
  (binary-tree-from-list '())
  #f
)
;; H3 T2: Only one element
(
  check-expect
  (binary-tree-from-list (list 1983))
  (
    make-binary-tree-node
    1983
    '()
    '()
  )
)
;; H3 T3: List with duplicate entries
(
  check-expect
  (binary-tree-from-list (list 13 45 13 61))
  (
    make-binary-tree-node
    13
    '()
    (
      make-binary-tree-node
      45
      '()
      (
        make-binary-tree-node
        61
        '()
        '()
      )
    )
  )
)

;; H4
;; Type: number binary-tree-node -> binary-tree-node
;; Returns: A binary tree with the inserted value, if necessary
;; it adds duplicate values into a list instead of the value
(define (insert-into-binary-tree-duplicates value btn)
  (
    cond
    ;; If btn == empty, then make a new binary tree with value
    [(empty? btn) (
      make-binary-tree-node value empty empty
    )]
    [(list? (binary-tree-node-value btn)) (
      ;; value is a list
      ;; first check if the first value in the list equals value
      if (= value (first (binary-tree-node-value btn))) (
        ;; Yes, the list contains values equal to value
        ;; ACTION: add value to list
        make-binary-tree-node
        (cons value (binary-tree-node-value btn))
        (binary-tree-node-left btn)
        (binary-tree-node-right btn)
      )
      ;; else
      (
        ;; The list doesn't contain value
        ;; Now continue the search of the binary tree
        cond
        [(< value (binary-tree-node-value btn)) (
          ;; value is smaller than btn.value
          ;; which means: go left
          if (empty? (binary-tree-node-left btn)) (
            ;; Good, the tree doesn't contain value
            ;; Insert the value
            ;; Return the current node with the inserted value
            make-binary-tree-node
              (binary-tree-node-value btn) ;value stays unchanged
              (make-binary-tree-node value '() '()) ;insert new node at the left side
              (binary-tree-node-right btn) ;right side stays unchanged
          )
          ;;else
          (
            ;; Good, we need to keep searching
            ;; Return the current node with the inserted value somewhere
            make-binary-tree-node
              (binary-tree-node-value btn) ;value stays unchanged
              (insert-into-binary-tree-duplicates value (binary-tree-node-left btn)) ;recursive function
              (binary-tree-node-right btn) ;right side stays unchanged
          )
        )]
        [(> value (binary-tree-node-value btn)) (
          ;; value > btn.value
          ;; go right
          if (empty? (binary-tree-node-right btn)) (
            make-binary-tree-node
              (binary-tree-node-value btn)
              (binary-tree-node-left btn)
              (make-binary-tree-node value '() '())
          )
          ;; else
          (
            make-binary-tree-node
              (binary-tree-node-value btn)
              (binary-tree-node-left btn)
              (insert-into-binary-tree-duplicates value (binary-tree-node-right btn))
          )
        )]
      )
    )]
    ;; Value is not a list
    [(= value (binary-tree-node-value btn)) (
      ;; value is already inside btn
      ;; ACTION: replace value with a list with 2 x value
      make-binary-tree-node
        (list value value)
        (binary-tree-node-left btn)
        (binary-tree-node-right btn)
    )]
    ;; else
    [(< value (binary-tree-node-value btn)) (
      ;; value is smaller than btn.value
      ;; which means: go left
      if (empty? (binary-tree-node-left btn)) (
        ;; Good, the tree doesn't contain value
        ;; Insert the value
        ;; Return the current node with the inserted value
        make-binary-tree-node
          (binary-tree-node-value btn) ;value stays unchanged
          (make-binary-tree-node value '() '()) ;insert new node at the left side
          (binary-tree-node-right btn) ;right side stays unchanged
      )
      ;;else
      (
        ;; Good, we need to keep searching
        ;; Return the current node with the inserted value somewhere
        make-binary-tree-node
          (binary-tree-node-value btn) ;value stays unchanged
          (insert-into-binary-tree-duplicates value (binary-tree-node-left btn)) ;recursive function
          (binary-tree-node-right btn) ;right side stays unchanged
      )
    )]
    [(> value (binary-tree-node-value btn)) (
      ;; value > btn.value
      ;; go right
      if (empty? (binary-tree-node-right btn)) (
        make-binary-tree-node
          (binary-tree-node-value btn)
          (binary-tree-node-left btn)
          (make-binary-tree-node value '() '())
      )
      ;; else
      (
        make-binary-tree-node
          (binary-tree-node-value btn)
          (binary-tree-node-left btn)
          (insert-into-binary-tree-duplicates value (binary-tree-node-right btn))
      )
    )]
    [else #f]
  )
)


;; Vorgegebene Tests
(check-expect (insert-into-binary-tree-duplicates 5 (make-binary-tree-node 3 empty (make-binary-tree-node 5 empty empty))) (make-binary-tree-node 3 empty (make-binary-tree-node (list 5 5) empty empty)))

(check-expect (insert-into-binary-tree-duplicates 11 tree-small) (make-binary-tree-node
 5
 (make-binary-tree-node 3 (make-binary-tree-node 1 '() '()) (make-binary-tree-node 4 '() '()))
 (make-binary-tree-node 9 (make-binary-tree-node 6 '() '()) (make-binary-tree-node (list 11 11) '() '()))))

(check-expect (insert-into-binary-tree-duplicates 4 (insert-into-binary-tree-duplicates 4 tree-middle)) (make-binary-tree-node
 6
 (make-binary-tree-node 3 (make-binary-tree-node 1 '() '()) (make-binary-tree-node (list 4 4 4) '() '()))
 (make-binary-tree-node 12 (make-binary-tree-node 8 '() (make-binary-tree-node 9 '() '())) (make-binary-tree-node 14 '() '()))))

;; Eigene Tests
;; H4 T1: Empty binary tree
(
  check-expect
  (insert-into-binary-tree-duplicates 1234 '())
  (
    make-binary-tree-node
    1234
    '()
    '()
  )
)
;; H4 T2: One duplicate
(
  check-expect
  (insert-into-binary-tree-duplicates 101 (
    make-binary-tree-node
    151
    (
      make-binary-tree-node
      101
      '()
      '()
    )
    '()
  ))
  (
    make-binary-tree-node
    151
    (
      make-binary-tree-node
      (list 101 101)
      '()
      '()
    )
    '()
  )
)
;; H4 T3: With multiple duplicates
(
  check-expect
  (insert-into-binary-tree-duplicates 999 (
    make-binary-tree-node
    151
    '()
    (
      make-binary-tree-node
      (list 999 999 999)
      '()
      '()
    )
  ))
  (
    make-binary-tree-node
    151
    '()
    (
      make-binary-tree-node
      (list 999 999 999 999)
      '()
      '()
    )
  )
)

