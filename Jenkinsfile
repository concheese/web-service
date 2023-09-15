pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Configure') {
            steps {
                sh '''
                    touch src/main/resources/env.properties
                    echo "DB_DATABASE=$DB_DATABASE" >> src/main/resources/env.properties
                    echo "DB_USER=$DB_USER" >> src/main/resources/env.properties
                    echo "DB_PASSWORD=$DB_PASSWORD" >> src/main/resources/env.properties
                '''
            }
        }
        stage('Build') {
            steps {
                sh './gradlew clean build'
            }
        }
    }
}
