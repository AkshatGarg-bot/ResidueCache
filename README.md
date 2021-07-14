# ResidueCache
**A low-energy low-area L2 cache architecture via compression and partial hits.**

This is the implementation of residue cache architecture described in https://ieeexplore.ieee.org/document/7851491 (.pdf also attached).

**How to Run ?** 

**1)**	Decompress the trace files (provided in trace_files) , on which you want to run . Or if it is user provided trace file , place it in trace_files directory.

**2)**	**Command to compile :-** 

    javac *.java
    
**3)**	**Command to run :-**
    
    java Residue

**4)**	**Input :-** numberOfFiles file1.trace file2.trace …filen.trace.
                For Example :– 
                
    2 sha_new.trace Basicmath_new.trace

**5)**	**Output:-** Output is generated in **Results.txt** file.
