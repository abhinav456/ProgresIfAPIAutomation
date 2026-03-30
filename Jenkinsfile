pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-17'
        PATH = "${env.JAVA_HOME}\\bin;${env.PATH}"
    }

    stages {

        stage('Build') {
            steps {
                bat '"C:\\Users\\user\\apache-maven-3.9.14-bin\\apache-maven-3.9.14\\bin\\mvn.cmd" clean install'
            }
        }

        stage('Test') {
            steps {
                bat '"C:\\Users\\user\\apache-maven-3.9.14-bin\\apache-maven-3.9.14\\bin\\mvn.cmd" test'
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
        }
    }
}