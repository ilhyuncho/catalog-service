쿠버네티스 배포


=======================================================================
1. 로컬 클러스터에 Postgresql 배포 ( 데이터 서비스 )
=======================================================================

이 프로젝트가 아닌 polar-deployment 프록젝트에서 실행 함


=======================================================================
2. 서비스를 쿠버네티스에 배포, 아직 해당 서비스에 접근 불가
=======================================================================
매니페스트 파일 : deployment.yml
(카탈로그 서비스 루트 폴더에서)

#앱 서비스 이미지 생성
./gradlew bootBuildImage

#생성된 이미지를 수동으로 로컬 클러스터 로 로딩
minikube image load catalog-service --profile polar

#매니페스트로 배포 객체를 생성
kubectl apply -f k8s/deployment.yml

#어떤 객첵가 만들어졌는지 확인
kubectl get all -l app=catalog-service
#생성된 파드 객체 확인
kubectl get pods -l app=catalog-service

#배포 로그를 확인
kubectl logs deployment/catalog-service

=======================================================================
3. 애플리케이션을 catalog-service라는 DNS이름과 80 포트로 노출하도록 서비스 객체의 매니페스트를 정의 )
=======================================================================
매니페스트 파일 : service.yml
(카탈로그 서비스 루트 폴더에서)

#매니페스트 적용
kubectl apply -f k8s/service.yml

#서버스 확인
kubectl get svc -l app=catalog-service

#서비스 객체를 로컬 컴퓨터에 노출 ( 쿠버네티스 포트 전달 기능 - 일시적인 처림 )
kubectl port-forward service/catalog-service 9001:80

#
kubectl delete -f k8s
