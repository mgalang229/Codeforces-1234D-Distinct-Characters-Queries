# Codeforces-1234D-Distinct-Characters-Queries
Link: https://codeforces.com/problemset/problem/1234/D
## Data structure: Binary Indexed Tree (BIT) / Fenwick Tree
![image](https://github.com/mgalang229/Codeforces-1234D-Distinct-Characters-Queries/assets/51401355/fb5a367a-24f2-402f-b053-800e9fd30241)
## Analysis
```
abacaba
5

2 1 4
abacaba
ans: 3

1 4 b
abababa

1 5 b
ababbba

2 4 6
ababbba
ans: 1

2 1 7
ababbba
ans: 2

int freq[n][26] -> main data structure

(before queries)
if i > 0
	freq[i][j] += freq[i-1][j], where 0 <= j < 26
freq[i][s[i]-‘a’] += 1

result:
abacaba
1: 
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
2: 
1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
3: 
2 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
4: 
2 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
5: 
3 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
6: 
3 2 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
7: 
4 2 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0

what about substring s[l:r]?
(during queries)
query 1:
let pos - 1 <= j < n,
	freq[j][s[pos-1]-'a'] -= 1
	freq[j][c-'a'] += 1
s[pos-1] = c
*i think this is slow
*find a way to update it in a faster time complexity
*look up Binary Indexed Tree (BIT) / Fenwick Tree

test binary indexed tree:

values:
9
0 1 1 2 3 2 -1 -2 1

prefix sum:
0 1 2 4 7 9 8 6 7

query 2:
0 <= j < 26
if l =1
	curFreq = freq[r-1][j]
else
	curFreq = freq[r-1][j] - freq[l-2][j]

if curFreq > 0
	counter += 1

————————————

dfcbbcfeeedbaea
15

1 6 e
dfcbbefeeedbaea

1 4 b
dfcbbefeeedbaea

2 6 14
dfcbbefeeedbaea
ans: 5

1 7 b
dfcbbebeeedbaea

1 12 c
dfcbbebeeedcaea

2 6 8
dfcbbebeeedcaea
ans: 2

2 1 6
dfcbbebeeedcaea
ans: 5

1 7 c
dfcbbeceeedcaea

1 2 f
dfcbbeceeedcaea

1 10 a
dfcbbeceeadcaea

2 7 9
dfcbbeceeadcaea
ans: 2

1 10 a
dfcbbeceeadcaea

1 14 b
dfcbbeceeadcaea

1 1 f
ffcbbeceeadcaea

2 1 11
ffcbbeceeadcaea
ans: 6


```
