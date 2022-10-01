# 로또
## 진행 방법
* 로또 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## Step1 : 문자열 계산기

### 문자열 사칙 연산 계산기 구현

- 이번 미션의 핵심은 내가 구현하는 코드에 단위 테스트를 추가하는 경험을 하는 것이다.
- 모든 예외 상황을 처리하기 위해 너무 복잡하게 접근하지 않아도 된다.

### 기능 요구사항

- 사용자가 입력한 문자열 값에 따라 사칙연산을 수행할 수 있는 계산기를 구현해야 한다.
- 입력 문자열의 숫자와 사칙 연산 사이에는 반드시 빈 공백 문자열이 있다고 가정한다.
- 나눗셈의 경우 결과 값을 정수로 떨어지는 값으로 한정한다.
- 문자열 계산기는 사칙연산의 계산 우선순위가 아닌 입력 값에 따라 계산 순서가 결정된다. 즉, 수학에서는 곱셈, 나눗셈이 덧셈, 뺄셈 보다 먼저 계산해야 하지만 이를 무시한다.
- 예를 들어 2 + 3 * 4 / 2와 같은 문자열을 입력할 경우 2 + 3 * 4 / 2 실행 결과인 10을 출력해야 한다.

### 프로그래밍 요구사항

- indent(들여쓰기) depth를 2단계에서 1단계로 줄여라.
  - depth의 경우 if문을 사용하는 경우 1단계의 depth가 증가한다. if문 안에 while문을 사용한다면 depth가 2단계가 된다.
- 메소드의 크기가 최대 10라인을 넘지 않도록 구현한다.
  - method가 한 가지 일만 하도록 최대한 작게 만들어라.
- else를 사용하지 마라. 

### 기능 분리 힌트

- 테스트할 수 있는 단위로 나누어 구현 목록을 만든다. 
  - 덧셈
  - 뺄셈
  - 곱셈
  - 나눗셈
  - 입력 값이 null이거나 빈 공백 문자일 경우 IllegalArgumentException throw
  - 사칙연산 기호가 아닌 경우 IllegalArgumentException throw
  - 사칙 연산을 모두 포함하는 기능 구현
- 공백 문자열을 빈 공백 문자로 분리하려면 String 클래스의 split(" ") 메소드를 활용한다.
- 반복적인 패턴을 찾아 반복문으로 구현한다.

### 기능 구현 진행사항

- 문자열 계산식 입력 담당 객체 (`Expression`)
  - [X] 입력 문자열이 빈 값 또는 null 값인 경우 익셉션 처리한다.
  - [ ] 문자열로 입력받은 계산식을 리스트 형식으로 변환한다.
- 문자열 계산식 목록 담당 객체 (`Expressions`)
  - [X] 완전한 계산식이 아닌 경우 익셉션 처리한다.
    - [X] 숫자와 연산자가 빈 칸 구분없이 연속으로 나오는 경우
    - [X] 연산식으로 끝나는 경우
    - [X] 빈 값으로 끝나는 경우
- 연산자 목록 담당 객체 (`Operation`)
  - [X] 입력 문자열의 사칙 연산을 계산한다.
  - [X] +, -, *, / 만 연산 처리한다.
  - [X] 0으로 나누면 익셉션 처리를 한다.
  - [X] 입력 문자열의 숫자와 사칙 연산 사이에는 반드시 빈 공백 문자열이 없는 경우 익셉션 처리한다.
  - [X] 지원하지 않는 연산자에 대해서 익셉션 처리한다.
  - [X] 사칙연산자가 아닌 경우 익셉션 처리한다.
  - [X] 입력 연산식이 빈 공간이 없는 경우 익셉션 처리한다.
- 연산 결과 담당 객체 (`CalculationResult`)
  - [X] 계산식의 결과를 구한다.