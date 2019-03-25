#### nPuzzle


## Parancssor argumentumainak száma
  - változó
  
## Kapcsolók
   1 -  input <FILE>: a kezdeti állapotot tartalmazó állomány neve. Ha a kapcsoló hiányzik, a standard bemenetről olvassa be a kezdeti állapotot.

## Példa hivásra és futtatásra: 
    Lépések:
    - 1.) javac *.java
    - 2.) java Program -input "input.txt" (ha a bemeneti fájl neve input.txt)  
    
   # 
    - kapcsoló nélkül: 
      - 1.) lépés (ugyanaz mint az előbbiekben)
      - 2.) lépés: java Program ---> ebben az esetben a standard bemenetről olvassunk(alapméretezetten 9 állapotot(3*3 puzzle))

   Eredmények:
    
   -bemenet:        7  2  4           -kimenet: 0  1  2
                    5  0  6                     3  4  5
                    8  3  1                     6  7  8
    
 ##  2 - solseq: a standard kimenetre írja a teljes megoldási szekvenciát.
   
   Eredmények:
   
   -bemenet:    1  4  2  
                0  3  5                
                6  7  8
               
   - I lépés:   1  4  2
                3  0  5
                6  7  8
                
   - II lépés :  1  0  2      
                 3  4  5                                
                 6  7  8                                
   
   - III lépés(végeredmény): 0  1  2
                             3  4  5
                             6  7  8

  ## 3 - –pcost: a standard kimenetre írja a megoldás költségét 
   
   Eredmények:
   
   -bemenet: 7  2  4        
             5  0  6                 
             8  3  1   
             
   -kimenet: 0  1  2
             3  4  5
             6  7  8
             
    - megoldás költsége: 45832
    
    
  ## 4 - –nvisited: a standard kimenetre írja a meglátogatott csomópontok számát
  
  Eredmények:
   
   -bemenet: 7  2  4        
             5  0  6                 
             8  3  1   
             
   -kimenet: 0  1  2
             3  4  5
             6  7  8
             
    - a meglátogatott csomópontok száma: 171786
    

 ## 5 –h <H>: a heurisztika típusa. Ha H=1, használja a „rossz helyen levő csempék száma” heurisztikát. Ha
        Ha H=2, használja a Manhattan heurisztikát.
