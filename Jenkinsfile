pipeline {
  agent any
  environment{
    DOCKER_CACHE_PATH = '/root/.cache/docker/build-tool/jdk8-maven-node16.tar'
  }
  stages {
    stage('检出') {
      steps {
        checkout([
          $class: 'GitSCM',
          branches: [[name: GIT_BUILD_REF]],
          userRemoteConfigs: [[
            url: GIT_REPO_URL,
            credentialsId: CREDENTIALS_ID
          ]]
        ])
      }
    }
    stage('加载构建环境容器缓存') {
      when { 
        expression { 
          fileExists(DOCKER_CACHE_PATH).equals(true) 
        } 
      }
      steps {
        sh 'docker load -i ${DOCKER_CACHE_PATH} || true'
        sh 'docker images'
      }
    }
    stage('构建项目') {
      agent {
        docker {
          reuseNode 'true'
          image 'ccr.ccs.tencentyun.com/everything/build-tool:jdk8-maven-node16'
          args '-v /var/run/docker.sock:/var/run/docker.sock -v /usr/bin/docker:/usr/bin/docker -v /root/.m2:/root/.m2 -v /root/.npm:/root/.npm'
        }
      }
      steps {
        sh 'java -version'
        sh 'mvn -v'
        sh 'node -v'
        sh './install.sh'
      }
    }
    stage('生成构建环境容器缓存') {
      when { 
        expression { 
          fileExists(DOCKER_CACHE_PATH).equals(false)
        }
      }
      steps {
        sh 'docker images'
        sh 'mkdir -p /root/.cache/docker/build-tool/'
        sh 'docker save -o ${DOCKER_CACHE_PATH} ccr.ccs.tencentyun.com/everything/build-tool:jdk8-maven-node16'
        sh 'ls /root/.cache/docker/build-tool/'
      }
    }
    stage('构建镜像') {
      steps {
        sh 'docker build -t ${DOCKER_NAME} -t ccr.ccs.tencentyun.com/everything/${DOCKER_NAME}:latest .'
        sh 'docker images'
      }
    }
    stage('推送镜像') {
      steps {
        script {
          try {
            withCredentials([usernamePassword(credentialsId: 'ec7cf668-af1f-4cd1-9587-5baf92285e76', usernameVariable: 'REGISTRY_USER', passwordVariable: 'REGISTRY_PASS')]) {
              sh "echo ${REGISTRY_PASS} | docker login -u ${REGISTRY_USER} --password-stdin ccr.ccs.tencentyun.com"
              sh "docker push ccr.ccs.tencentyun.com/everything/${DOCKER_NAME}:latest"
            }
          } catch(err) {
            echo err.getMessage()
          }
        }
      }
    }
  }
}