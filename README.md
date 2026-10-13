## Computation

The system will calculate the Collatz sequence for the input number. If the current number is even, the system will divide it by 2. If the number is odd, the system will multiply it by 3 and add 1. The system will continue this process until the number reaches 1.

For example, if the input is `6`, the computation will be:

**Sample Input:**
`6`

**Sample Output:**
`6, 3, 10, 5, 16, 8, 4, 2, 1`

This computation can be CPU intensive for larger input values because the system may need to perform many repeated calculations before reaching 1.



## System Architecture


flowchart LR
    subgraph N["Network API"]
        U["User / Initialization & I/O"]
        J["UserJobRequest<br/>JobSource<br/>JobDestination"]
    end

    subgraph P["Process API"]
        D["Data Storage"]
        W["DataStreamWrapper"]
    end

    subgraph C["Conceptual API"]
        E["Computation Engine"]
        CC["Collatz Calculation<br/><br/>Even → n / 2<br/>Odd → 3n + 1<br/>Stop when n = 1"]
    end

    O["Output / Destination"]

    U -->|"Job / input request"| D
    D -->|"Input data response"| U

    U -->|"Compute request"| E
    E -->|"Computed sequence response"| U

    D -->|"Input values request"| E
    E -->|"Calculation result response"| D

    U --- J
    D --- W
    E --- CC

    U -->|"Write formatted output request"| O
    O -->|"Output status response"| U
