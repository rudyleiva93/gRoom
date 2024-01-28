def call() {
    pipeline {
        agent {label 'Docker-Cloud'}
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
                                bat '"C:\\Program Files\\Microsoft Visual Studio\\2022\\Community\\MSBuild\\Current\\Bin\\MSBuild.exe" groomserver.sln -restore -p:RestoreConfigFile="..\\NuGet.config" -t:Build'
                            }
                        }
                    }
                    stage('Build gRoom Client') {
                        steps {
                            dir('groom\\groomclient') {
                                bat '"C:\\Program Files\\Microsoft Visual Studio\\2022\\Community\\MSBuild\\Current\\Bin\\MSBuild.exe" groomclient.sln -restore -p:RestoreConfigFile="..\\NuGet.config" -t:Build'
                            }
                        }
                    }
                }
            }
        }
    }
}

return this;