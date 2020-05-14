def call(Map config=[:]){
node('DOTNETCORE') {
    stage('SCM') {
    	echo 'Gathering code from version control'
        git branch: '${branch}', url: 'https://github.com/bmajeska/JenkinsGroovy.git'
    }
    stage('Build') {
        try{
        echo 'Building....'
        sh 'dotnet build ConsoleApp1'
        echo 'Building New Feature'
        releasenotes(changes:"true")
        }catch(ex){
            echo 'Error: something is wrong'
            echo ex.toString();
            currentBuild.resut = 'FAILURE'
        }
        finally{
            // cleanup
        }
    }
    stage('Test') {
        echo 'Testing....'
    }
    stage('Deploy') {
        echo 'Deploying....'
    }
}
}
