;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname H10_1) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define-struct node (name neighbors))

(define A (make-node "A" (list "B" "C")) )
(define B (make-node "B" (list "E")) )
(define C (make-node "C" (list "B" "D" "E")) )
(define D (make-node "D" (list "F")) )
(define E (make-node "E" (list "F")) )
(define F (make-node "F" (list "G")) )
(define G (make-node "G" empty) )

(define-struct graph (name nodes))

(define G1 (make-graph "G1" (list A B C D E F G)))

;; Type: node node graph -> (list of String)
;; Returns: a path from origin to dest in the given graph
;; Precondition: cycle-free graph
(define (find-route origin dest graph) (
  cond
  ;; What if we get some empty arguments?
  [(empty? origin) #f]
  [(empty? dest) #f]
  [(empty? graph) #f]
  ;; What if origin or dest aren't even in the graph?
  [(not (member origin (graph-nodes graph))) #f]
  [(not (member dest (graph-nodes graph))) #f]
  ;; If there is no possible path, return false
  [(not (leads-to-dest? origin dest graph)) #f]
  ;; Recursively search the graph
  [else (recursive-search origin dest graph empty)]
))

;; Type: node node graph -> boolean
;; Returns: True when a path from origin to destination exists, false otherwise
;; Precondition: cycle-free graph
(define (leads-to-dest? node dest graph) (
  cond
  [(equal? node dest) #t]
  [(empty? node) #f]
  [(empty? (get-neighbors node graph)) #f]
  [(< 0 (length (filter (lambda (x) (equal? x dest)) (get-neighbors node graph)))) #t]
  [else (ormap (lambda (x) (leads-to-dest? x dest graph)) (get-neighbors node graph))]
  ))

;; Type: node node graph (list of string) -> (list of string)
;; Returns: A possible path encoded in a list of string
(define (recursive-search node dest graph current-path) (
  cond
  [(not (leads-to-dest? node dest graph)) #f]
  ;;[(empty? (get-neighbors node graph)) #f]
  [(equal? node dest) (append current-path (list (node-name node)))]
  #|
  [(< 0 (length (filter (lambda (x) (equal? x dest)) (get-neighbors node graph))))
   ;; We have reached the destination, return the current path + the destination
   (append current-path (list node dest))
  ]
  |#
  [else
   ;; Continue searching from the first element that leads to the destination
   (recursive-search (first (filter (lambda (x) (leads-to-dest? x dest graph)) (get-neighbors node graph)))
                     dest
                     graph
                     (append current-path (list (node-name node))))
  ]
  ))

;; Type: string graph -> node
;; Returns: the node in graph with the given name
;; Precondition: the names of the nodes are unique
(define (get-node-in-graph name graph)
  (first
    (filter (lambda (x) (string=? (node-name x) name)) (graph-nodes graph))))

;; Type: node graph -> (list of node)
;; Returns: a list of neighbors of the given node
(define (get-neighbors node graph)
  (map (lambda (x) (get-node-in-graph x graph)) (node-neighbors node)))

;; Tests
(check-expect (find-route B F G1) (list "B" "E" "F"))
(check-expect (find-route G A G1) false)
(check-expect (member (find-route A G G1) (list
                                           (list "A" "B" "E" "F" "G")
                                           (list "A" "C" "E" "F" "G")
                                           (list "A" "C" "D" "F" "G")
                                           ))
              
              true)
(check-expect (find-route B B G1) (list "B"))
