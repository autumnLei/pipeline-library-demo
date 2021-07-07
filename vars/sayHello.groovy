#!/usr/bin/env groovy

def call(String name = 'human') {
  echo "Hello, ${name}."
  echo "BUILD_NUMBER: ${BUILD_NUMBER}"
  echo "env.BUILD_NUMBER: ${env.BUILD_NUMBER}"
  echo "gitlabSourceBranch: ${gitlabSourceBranch}"
  echo "env.gitlabSourceBranch: ${env.gitlabSourceBranch}"
  echo "email: ${email}"
  echo "env.email: ${env.email}"
  parallel codeScan: {
    sh "echo this is codeScan"
  }, apkScan: {
    sh "echo this is apkScan"
    sh "touch a.txt"
  }, failFast: true

  archiveArtifacts artifacts: 'a.txt', fingerprint: true, onlyIfSuccessful: false, allowEmptyArchive: true

  try {
    sh "exit 1"
  } catch (exc) {
    error "${exc}"
  }
}

