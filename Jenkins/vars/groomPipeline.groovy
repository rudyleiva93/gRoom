def call() {
    pipeline {
        agent any
        options {
            checkoutToSubdirectory('groom')
            timestamps()
        }
        stages('Build gRoom Application') {
            stage('Parallel Build') {
                parallel {
                    stage('Build gRoom Server') {
                        steps {
                            dir('groom\\groomserver') {
                                bat '"C:\\Program Files\\Microsoft Visual Studio\\2022\\Community\\MSBuild\\Current\\Bin\\MSBuild.exe" groomserver.sln -restore -t:Build'
                            }
                        }
                    }
                    stage('Build gRoom Client') {
                        steps {
                            dir('groom\\groomclient') {
                                bat '"C:\\Program Files\\Microsoft Visual Studio\\2022\\Community\\MSBuild\\Current\\Bin\\MSBuild.exe" groomclient.sln -restore -t:Build'
                            }
                        }
                    }
                }
            }
        }
    }
}

return this;