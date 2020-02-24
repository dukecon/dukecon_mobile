@Library('jenkins-library@master') _

pipeline {
    agent {
        node {
            label 'docker'
        }
    }

    options {
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '5', daysToKeepStr: '5'))
    }

    triggers {
        pollSCM('*/3 * * * *')
    }

    stages {
        stage('Generate Static Pages') {
            steps {
                sh("./gradlew clean buildDocs")
            }
        }
        stage('Publish Static Pages locally') {
            steps {
                publishHTML(target: [allowMissing         : true,
                                     alwaysLinkToLastBuild: false,
                                     keepAll              : true,
                                     reportDir            : 'build/docs/html5/site/',
                                     reportFiles          : 'index.html',
                                     reportName           : 'Published Static Pages'])
            }
        }
        stage('Publish Static Pages to dukecon.org') {
            when {
                branch 'develop'
            }
            steps {
                sh "rsync --delete -avH build/docs/html5/site/. /var/www/html"
            }
        }
    }

    post {
        always {
            sendNotification currentBuild.result
        }
        failure {
            // notify users when the Pipeline fails
            mail to: 'gerd@aschemann.net, ralf.d.mueller@docs-as-co.de',
                    subject: "Failed DukeCon WebHome Pipeline: ${currentBuild.fullDisplayName}",
                    body: "Something is wrong with ${env.BUILD_URL}"
        }
    }
}
