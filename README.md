## 점심식사 추천 봇
- Dooray 슬래시 커맨드로 호출
- Notion을 DB로 사용
- H2 DB로 데이터 동기화, JPA 사용
- 그 외 그냥 간단하게 해보고 싶은것들 추가

### 배포

아직 배포환경이 제대로 정립되진 않아서 임시로 ngrok 사용하여 터널링하여 사용중

1. ngrok 설치 후 프로젝트 루트에서 실행
2. `ngrok http 서버포트번호(8088)` 커맨드 실행
3. 화면의 url을 dooray 슬래시커맨드의 requestUrl로 등록
4. 호출

### 지원하는 커맨드

```text
#dooray에서 대화방에 슬래시커맨드 추가하고 아래 커맨드로 호출
/점심 : 전체 목록에서 랜덤추천
/점심 {카테고리} : 지정한 카테고리에서 랜덤추천
```