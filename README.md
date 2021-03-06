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

    
     bemeneti állapot :  7  2  4   5  0  6    8  3  1      
     kimeneti állapot :  0  1  2   3  4  5    6  7  8
    
 ##  2 - solseq: a standard kimenetre írja a teljes megoldási szekvenciát.
   
   Eredmények:
   
    -bemenet:    1  4  2   0  3  5   6  7  8
               
    -I lépés:   1  4  2   3  0  5   6  7  8
                
    -II lépés :  1  0  2    3  4  5   6  7  8                                
   
    -III lépés(végeredmény): 0  1  2    3  4  5   6  7  8

  ## 3 - –pcost: a standard kimenetre írja a megoldás költségét 
   
   Eredmények:
   
    -bemenet: 7  2  4    5  0  6   8  3  1   
             
    -kimenet: 0  1  2    3  4  5    6  7  8
             
    - megoldás költsége: 45832
    
    
  ## 4 - –nvisited: a standard kimenetre írja a meglátogatott csomópontok számát
  
  Eredmények:
   
    -bemenet: 7  2  4     5  0  6     8  3  1   
             
    -kimenet: 0  1  2     3  4  5     6  7  8
             
    - a meglátogatott csomópontok száma: 171786
    

 ## 5 –h <H>: a heurisztika típusa. Ha H=1, használja a „rossz helyen levő csempék száma” heurisztikát. Ha H=2, használja a Manhattan heurisztikát.
  
  Eredmények:
   -ha H=1, akkor a "rossz helyen levő csempék száma" heurusztika hívódik meg;
   - ha H=2, akkor a Manhattan heurisztika hívódik meg;
   - a heurisztikák, a kapcsolókkal való együttes használata során íródnak ki a meglátogatott csomópontok száma, a költség;
   - ha H különbőzik 1-től vagy 2-től, akkor hiba üzenet íródik ki,hogy nincs ilyen heursiztika;
  
## 6  –rand <N> <M> egy véletlenszerű, N méretű állapotot írjon ki a standard kimenetre. M a véletlenszerű tologatások számát jelenti.
  
   - ha M=3 és N=4, a bemenet : 7  2  4  5  0  6  8  3  1
        - random eltolási irányok:
            - eltolás jobbra : 7  2  4  5  6  0  8  3  1
            - eltolás fel: 7  2  0  5  6  4  8  3  1
            - eltolás le: 7  2  4  5  6  0  8  3  1
            - eltolás le: 7  2  4  5  6  1  8  3  0
            
## Eredmények összehasonlítása:
    - a két heurisztika költségeinek összehasonlítása: 
        - a költségek fenti bemenetre: Manhattan távolság esetén: 26
                                       "rossz helyen levő csempék száma" : 26
        - meglátogatott csomópontok száma: "rossz helyen levő csempék száma" : 128846
                                            Manhattan távolság esetén: 174172
