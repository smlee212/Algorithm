# [Gold II] 뉴스 전하기 - 1135 

[문제 링크](https://www.acmicpc.net/problem/1135) 

### 성능 요약

메모리: 18436 KB, 시간: 188 ms

### 분류

다이나믹 프로그래밍, 그리디 알고리즘, 정렬, 트리, 트리에서의 다이나믹 프로그래밍

### 제출 일자

2025년 7월 29일 12:36:01

### 문제 설명

<p>민식이는 회사의 매니저이다. 그리고, 민식이는 회사의 중요한 뉴스를 모든 직원에게 빠르게 전달하려고 한다. 민식이의 회사는 트리 구조이다. 모든 직원은 정확하게 한 명의 직속 상사가 있다. 자기자신은 그들 자기 자신의 직접 또는 간접 상사가 아니고, 모든 직원은 민식이의 직접 또는 간접적인 부하이다.</p>

<p>민식이는 일단 자기 자신의 직속 부하에게 한 번에 한 사람씩 전화를 한다. 뉴스를 들은 후에, 각 부하는 그의 직속 부하에게 한 번에 한 사람씩 전화를 한다. 이 것은 모든 직원이 뉴스를 들을 때 까지 계속된다. 모든 사람은 자신의 직속 부하에게만 전화를 걸 수 있고, 전화는 정확하게 1분 걸린다. 이때 모든 직원이 소식을 듣는데 걸리는 시간의 최솟값을 구하는 프로그램을 작성하시오.</p>

<p>오민식의 사원 번호는 0이고, 다른 사원의 번호는 1부터 시작한다.</p>

### 입력 

 <p>첫째 줄에 직원의 수 N이 주어진다. 둘째 줄에는 0번 직원부터 그들의 상사의 번호가 주어진다. 0번 직원 (오민식)은 상사가 없기 때문에 -1이고, 나머지 직원 i의 상사 번호는 i보다 작거나 같은 음이 아닌 정수이다. N은 50보다 작거나 같은 자연수이다.</p>

### 출력 

 <p>첫째 줄에 모든 소식을 전하는데 걸리는 시간의 최솟값을 출력한다.</p>

