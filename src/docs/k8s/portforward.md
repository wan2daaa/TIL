# 포트포워딩 

- 쿠버네티스 내 argo-cd 에 localhost로 접근할려고 포트포워딩 사용
`kubectl port-forward svc/argocd-server -n argo-cd 8080:443`
