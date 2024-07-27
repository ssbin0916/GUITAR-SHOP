# GUITAR SHOP
2024.05.01 ~ 

기타 판매 온라인 쇼핑몰 백엔드 API 서버 구축 프로젝트

## 프로젝트 목표
프로젝트의 주요 목표는 연관 관계 테이블을 설계하고, 객체 지향적 설계를 통해 유지 보수와 확장이 용이한 시스템을 구현하는 것이었습니다. 데이터 매핑 기술로 JPA를 선택한 이유는 기본적인 CRUD 기능이 자동화되어 서비스 로직에 집중할 수 있고, 지연 로딩 전략을 통해 성능 최적화가 가능하며, 객체 지향적 데이터 모델링에 유리하기 때문입니다.

* [분산 락을 활용한 재고 감소 동시성 이슈 해결](https://velog.io/@ssbin0916/Redis-%EB%B6%84%EC%82%B0-%EB%9D%BD%EC%9D%84-%ED%99%9C%EC%9A%A9%ED%95%9C-%EC%9E%AC%EA%B3%A0-%EA%B0%90%EC%86%8C-%EB%8F%99%EC%8B%9C%EC%84%B1-%EC%9D%B4%EC%8A%88-%ED%95%B4%EA%B2%B0)

* [Incr 명령어를 활용한 게시글 조회 동시성 이슈 해결](https://velog.io/@ssbin0916/Redis%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%9C-%EA%B2%8C%EC%8B%9C%EA%B8%80-%EC%A1%B0%ED%9A%8C-%EB%8F%99%EC%8B%9C%EC%84%B1-%EC%9D%B4%EC%8A%88-%ED%95%B4%EA%B2%B0)

* [캐싱을 통한 게시글 조회 대용량 트래픽 처리 속도 향상](https://velog.io/@ssbin0916/Redis-%EC%BA%90%EC%8B%B1%EC%9D%84-%ED%86%B5%ED%95%9C-%EB%8C%80%EC%9A%A9%EB%9F%89-%ED%8A%B8%EB%9E%98%ED%94%BD-%EC%B2%98%EB%A6%AC)

* [Redis Sorted Set을 활용한 선착순 쿠폰 이벤트 처리](https://velog.io/@ssbin0916/Redis%EC%9D%98-Sorted-Set%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%9C-%EC%84%A0%EC%B0%A9%EC%88%9C-%EC%BF%A0%ED%8F%B0-%EC%9D%B4%EB%B2%A4%ED%8A%B8)
  
* [스프링 시큐리티와 JWT를 활용한 보안 강화](https://velog.io/@ssbin0916/Spring-Security%EC%99%80-JWT)
  
* 객체 불변성 유지

* 주문 생성 API의 재고 부족 예외 처리 개선
  
* [AWS EC2 배포](https://velog.io/@ssbin0916/AWS-EC2-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EB%B0%B0%ED%8F%AC)


## 배포 서버
http://43.201.107.128:8080/


## 사용 기술

* JAVA17, Spring Boot, Spring Security JWT, MySQL, JPA, Querydsl, Redis



<br>

### [Redis 분산 락을 활용한 재고 감소 동시성 이슈 해결](https://velog.io/@ssbin0916/Redis-%EB%B6%84%EC%82%B0-%EB%9D%BD%EC%9D%84-%ED%99%9C%EC%9A%A9%ED%95%9C-%EC%9E%AC%EA%B3%A0-%EA%B0%90%EC%86%8C-%EB%8F%99%EC%8B%9C%EC%84%B1-%EC%9D%B4%EC%8A%88-%ED%95%B4%EA%B2%B0)

- **문제**
    - **Junit** 테스트에서 **100개의 스레드**를 생성하여 **동시에 재고 감소** 로직을 실행한 결과, 실제로는 재고가 **4개**만 감소하는 것을 확인했습니다. 이를 통해 **Race Condition**이 발생하였음을 확인할 수 있었습니다.
- **해결**
    - 동시성 문제를 해결하기 위해 redis의 분산락을 사용하기로 결정하고 클라이언트로 **Redisson**과 **Lettuce** 중에서 고민했습니다. 최종적으로 Redisson을 선택한 이유는 **데드락 방지**와 **자동 잠금 해제**를 제공하기 때문입니다.
- **결과**
    - Redisson을 사용하여 분산 락을 적용한 결과, 100개의 스레드가 동시에 재고 감소 로직을 실행했을 때 재고가 100개 모두 제대로 감소하는 결과를 얻을 수 있었습니다.

<br>

### [Redis의 Sorted Set을 활용한 선착순 쿠폰 이벤트 처리](https://velog.io/@ssbin0916/Redis%EC%9D%98-Sorted-Set%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%9C-%EC%84%A0%EC%B0%A9%EC%88%9C-%EC%BF%A0%ED%8F%B0-%EC%9D%B4%EB%B2%A4%ED%8A%B8)

- **문제**
    - 선착순 **30명**에게 쿠폰을 발급할 때, 사용자들이 과도하게 몰려 서버가 처리하지 못할 상황이 발생할 수 있습니다. 이 문제를 예방하기 위해, **JUnit** 테스트에서 **100개**의 스레드를 생성하여 서버 부하를 줄이기 위한 방안으로 **10명**씩 나눠서 쿠폰을 발급하는 시나리오를 테스트했습니다.
- **해결**
    - **Redis Sorted Set**을 활용한 대기큐 개념을 도입하여 **번호표 발급**, **대기열 생성**, **대기 순번 고지** 등의 기능을 구현했습니다.
    - **ZADD**를 사용하여 사용자들을 참여한 순서대로 정렬합니다.
    - **ZRANK**를 활용하여 대기 중인 사용자의 현재 대기 순번을 제공합니다.
    - **ZRANGE**를 통해 일정 수 만큼의 리스트를 조회합니다.
- **결과**
    - **JUnit** 테스트 결과, 100명의 사용자가 **대기열에 등록**되었고, 등록된 **순서대로** 처음에 미리 설정해 둔 **10개씩** 쿠폰이 발급되었습니다. 그 이후에는 **순차적**으로 다음 사용자들에게 자신의 **대기 순번**이 부여되었음을 확인했습니다.

<br>

### [스프링 캐시를 활용한 게시글 조회 대용량 트래픽 처리 속도 향상](https://velog.io/@ssbin0916/Redis-%EC%BA%90%EC%8B%B1%EC%9D%84-%ED%86%B5%ED%95%9C-%EB%8C%80%EC%9A%A9%EB%9F%89-%ED%8A%B8%EB%9E%98%ED%94%BD-%EC%B2%98%EB%A6%AC)

- **문제**
    - **JMeter**를 ****사용하여 **20만 건**의 트래픽으로 인기글 **조회**를 했을 때, 대략 **20초**가 소요되는 상황을 확인했습니다. 대규모 트래픽 상황에서는 **성능 부하**와 **처리 속도 저하** 문제가 발생할 수 있음을 인지했습니다.
- **해결**
    - 인기글 조회 시 조회수 누락과 같은 동시성 문제는 **Redis**의 **Incr** 명령어를 활용하여 해결했습니다.
    - 단일 서버 환경이기 때문에 캐시 정합성 문제를 걱정할 필요 없이 **@Cacheable** 어노테이션을 사용하여 자주 조회되는 데이터를 미리 **캐싱**했습니다.
- **결과**
    - **JMeter**로 성능 테스트한 결과, **20만 건**의 트래픽에서 처리 속도가 초당 **1만 건**에서 **4만 건**으로 향상되어, 처리 시간이 **20초**에서 **5초**로 감소된 것을 확인했습니다.

<br>

### 스프링 시큐리티와 JWT를 활용한 보안 강화

- **문제**
    - 회원 서비스나 주문 서비스와 같이 권한이 필요한 요청이 발생할 때, 클라이언트와 서버 간의 요청 횟수가 증가하면 토큰이 탈취될 가능성이 높아질 수 있습니다.
- **해결**
    - 자주 호출되는 서비스에서는 **Access 토큰**을 사용합니다. Access 토큰은 짧은 생명 주기를 가지므로, 탈취되어도 짧은 시간 내에 무용지물이 됩니다.
    - Access 토큰의 만료 시 **Refresh** 토큰을 사용하여 새로운 Access 토큰을 발급받습니다.
- **결과**
    - Access 토큰의 짧은 생명 주기를 통해 보안을 강화했으며, Refresh 토큰을 사용하여 자주 로그인해야 하는 번거로움을 해결했습니다.

<br>

### 객체 불변성 유지

- **문제**
    - 객체의 불변성이 유지가 되지 않으면 예상치 못한 객체 내부 상태 변경이 발생할 수 있으며, **다중 스레드** 환경에서 안전하지 않습니다.
- **해결**
    - 엔티티 내에 **Setter** 메서드를 사용하지 않고, 데이터 변경이 필요한 경우 **생성자**나 **빌더 패턴**을 사용했습니다.
    - 각 서비스의 요구사항에 맞게 **DTO**를 **Record** 클래스로 설계하여 엔티티와 DTO 사이를 편리하게 처리했습니다.
- **결과**
    - 엔티티와 DTO 간의 변환을 통해 코드 가독성을 높이고, 유지보수성을 개선하며 단일 책임 원칙을 준수한 객체 지향적 설계를 이루었습니다.

<br>

### **주문 생성 API의 재고 부족 예외 처리 개선**

- **문제**
    - 주문 생성 API에서 주문할 수량보다 남은 상품의 재고가 적을 때도 주문이 정상적으로 처리되는 문제가 발생했습니다.
- **해결**
    - 주문 생성 메서드를 **JUnit**을 이용해 **단위 테스트**했습니다. 재고가 부족한 상황에서도 **예외**가 발생하도록 유효성 검사 로직을 구현하였습니다.
    - **Postman**을 이용해 API 엔드포인트에서 실제 HTTP 요청을 보내어, 재고보다 많은 수의 주문을 시도했을 때 오류가 올바르게 처리되는지 확인했습니다.
- **결과**
    - JUnit 테스트에서 재고가 부족한 경우 예외가 발생하여 처리되는 것을 확인했습니다.
    - API 요청 시 재고보다 많은 수의 주문을 시도했을 때, 서버에서 올바르게 예외 처리되어 클라이언트에게 재고 부족 메시지를 반환하도록 수정했습니다.
 
## ERD

<img width="982" alt="스크린샷 2024-07-27 오후 7 05 13" src="https://github.com/user-attachments/assets/a87f633c-3397-489b-ba28-41c26d2b9e01">
