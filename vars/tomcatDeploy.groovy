void call(Map opts) {            
  deploy adapters: [tomcat9(credentialsId: "${opts.credentialsId}", path: " ", url: "${opts.url}")], contextPath: "${opts.contextPath}", war: "${opts.war}"
}
