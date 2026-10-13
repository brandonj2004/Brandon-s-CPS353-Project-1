## Computation

The system will calculate the Collatz sequence for the input number. If the current number is even, the system will divide it by 2. If the number is odd, the system will multiply it by 3 and add 1. The system will continue this process until the number reaches 1.

For example, if the input is `6`, the computation will be:

**Sample Input:**
`6`

**Sample Output:**
`6, 3, 10, 5, 16, 8, 4, 2, 1`

This computation can be CPU intensive for larger input values because the system may need to perform many repeated calculations before reaching 1.



## System Architecture
```mermaid
flowchart TD
    U["User / Initialization & I/O"]
    D["Data Storage"]
    C["Computation Engine"]
    O["Output / Destination"]

    subgraph N["Network API"]
        N1["User Job Request"]
        N2["Job Source / Job Destination"]
    end

    subgraph P["Process API"]
        P1["Data Stream Wrapper"]
        P2["Job and Input Management"]
    end

    subgraph CP["Conceptual API"]
        C1["Collatz Calculation"]
        C2["Even: n / 2"]
        C3["Odd: 3n + 1"]
        C4["Stop when n = 1"]
    end

    U -->|"Request: Submit Job"| D
    D -->|"Response: Job Data"| U

    U -->|"Request: Compute Sequence"| C
    C -->|"Response: Computed Sequence"| U

    D -->|"Request: Input Values"| C
    C -->|"Response: Calculation Result"| D

    U -->|"Request: Write Output"| O
    O -->|"Response: Output Status"| U

    %% Corrected API connection syntax
    U --> N1
    N1 --> N2
    D --> P1
    P1 --> P2
    C --> C1
    C1 --> C2
    C1 --> C3
    C1 --> C4
```
