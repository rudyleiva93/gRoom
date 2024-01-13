package com.groom.jenkins.pipelines

def build() {
    pipeline {
        options {
            checkoutToSubdirectory('groom')
            timestamps()
        }
        stages('Build gRoom Application') {
            stage('Parallel Build') {
                parallel {
                    stage('Build gRoom Server') {
                        dir('groomserver') {
                            bat '"C:\\Program Files\\Microsoft Visual Studio\\2022\\Community\\MSBuild\\Current\\Bin\\MSBuild.exe" groomserver.sln -t:Build'
                        }
                    }
                    stage('Build gRoom Client') {
                        dir('groomclient') {
                            bat '"C:\\Program Files\\Microsoft Visual Studio\\2022\\Community\\MSBuild\\Current\\Bin\\MSBuild.exe" groomclient.sln -t:Build'
                        }
                    }
                }
            }
        }
    }
}
