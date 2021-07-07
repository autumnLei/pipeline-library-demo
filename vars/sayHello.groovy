#!/usr/bin/env groovy

def call(String name = 'human') {
  echo "Hello, ${name}."
  parallel codeScan: {
    sh "echo this is codeScan"
  }, apkScan: {
    sh "echo this is apkScan"
    sh "touch a"
  }, failFast: true

  archiveArtifacts artifacts: 'a', fingerprint: true, onlyIfSuccessful: true, allowEmptyArchive: true

  try {
    sh "exit 1"
  } catch (exc) {
    error "${exc}"
  }
}

