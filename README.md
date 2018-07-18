# dm7
* dm7 프로젝트
* TBD

## Project Structure

### Technical Stack
* Java 8
* Spring Boot 2.0
* Gradle 4.8

### Package Structure
* TBD

### How to Run 
* `./gradlew bootRun` 

### Applications for development
* (required) SourceTree : https://www.getpostman.com/apps
  - Git GUI Client Tool
* (required) Postman : https://www.getpostman.com/apps
  - for HTTP Test
  - chrome 확장프로그램으로도 가능
* (option) Fiddler2 : https://www.telerik.com/support/whats-new/fiddler/release-history/fiddler-v2.x
  - client dbug tool 
  
### Information for test 

* 테스트용 User 정보에 대한 Map 기반 CRUD 작성
  - User 전체 조회 : \[GET\] http://localhost:8080/users/
  - User 단건 조회 : \[GET\] http://localhost:8080/user/{아이디}
  - User 삭제 : \[DELETE\] http://localhost:8080/user/{삭제할아이디}
  - User 등록 : \[PUT\] http://localhost:8080/user/
     ```
     {
         "id": "aaaa",
         "name": "테스트"
     }
    ```
  - 기본적으로 json 기반으로 동작하게 했음
  


  
