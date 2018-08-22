# telepresence-practice
To practice Telepresence with windows 10 &  Docker for windows installation

The new docker for windows is exciting to work with, since it has built in kubernetes support. However, among the various tools for kubernetes, telepresence has gained alot of popularity since its easy to inject the local process into the running kube environment.

However, the telepresence does not work well with windows (only inject-tcp method is supported), The official Telepresence document recommends to use the Ubuntu (WSL). But i am going to try running the telepresence itself with in the docker as the ubuntu container so that we will utilize the full capabilty of the telepresence in windows.

The docker version i am using is </br>

<b>Client:</b>
 Version:           18.06.0-ce</br>
 API version:       1.38</br>
 Go version:        go1.10.3</br>
 Git commit:        0ffa825</br>
 Built:             Wed Jul 18 19:05:28 2018</br>
 OS/Arch:           windows/amd64</br>
 Experimental:      false</br>

<b>Server:</b></br>
 Engine:</br>
  Version:          18.06.0-ce</br>
  API version:      1.38 (minimum version 1.12)</br>
  Go version:       go1.10.3</br>
  Git commit:       0ffa825</br>
  Built:            Wed Jul 18 19:13:46 2018</br>
  OS/Arch:          linux/amd64</br>
  Experimental:     true</br>
 <b>Kubernetes:<b></br>
  Version:          v1.10.3</br>
  StackAPI:         v1beta2</br>
  
I have enabled the kubernetes option in docker, shared the drive & exposing the running deamon in the localhost:2375 port.

Lets get started!!!

Steps:

1) Run the telepresence image build from the folder telepresence in the repo.</br>
  <b>docker build -t telepresence .</b></br></br>
2) Run the telepresence container with the host network & host volume mounted.</br>
  <b>docker run --net=host --ipc=host --uts=host --pid=host -it --security-opt=seccomp=unconfined --privileged --rm -v /:/host -v /var/run/docker.sock:/var/run/docker.sock telepresence /bin/bash </b></br>
     
3) Test if the docker daemon is running and pointing to the host machine by running docker version. by running the below command in the telepresence container bash terminal</br>
<b>docker version</b></br>
   expected output:</br>
   root@linuxkit-00155d2e4146:/# docker version</br>
<i>Client:</br>
 Version:           18.06.1-ce</br>
 API version:       1.38</br>
 Go version:        go1.10.3</br>
 Git commit:        e68fc7a</br>
 Built:             Tue Aug 21 17:24:51 2018</br>
 OS/Arch:           linux/amd64</br>
 Experimental:      false</br>
Server:</br>
 Engine:</br>
  Version:          18.06.0-ce</br>
  API version:      1.38 (minimum version 1.12)</br>
  Go version:       go1.10.3</br>
  Git commit:       0ffa825</br>
  Built:            Wed Jul 18 19:13:46 2018</br>
  OS/Arch:          linux/amd64</br>
  Experimental:     true</br></i>
  
  4) Copy the kube config from the host machine to run the kube commands from the telepresence container.</br>
  <b>mkdir ~/.kube</br>
  cp /host/etc/kubernetes/admin.conf ~/.kube/config</br></b>
  
  5) check the kubectl by running the below command.</br>
  <b>kubectl version</b></br>
  <i>expected output:</br>
  root@linuxkit-00155d2e4146:/# kubectl version</br>
Client Version: version.Info{Major:"1", Minor:"11", GitVersion:"v1.11.2", GitCommit:"bb9ffb1654d4a729bb4cec18ff088eacc153c239", GitTreeState:"clean", BuildDate:"2018-08-07T23:17:28Z", GoVersion:"go1.10.3", Compiler:"gc", Platform:"linux/amd64"}</br>
Server Version: version.Info{Major:"1", Minor:"10", GitVersion:"v1.10.3", GitCommit:"2bba0127d85d5a46ab4b778548be28623b32d0b0", GitTreeState:"clean", BuildDate:"2018-05-21T09:05:37Z", GoVersion:"go1.9.3", Compiler:"gc", Platform:"linux/amd64"}</br></i>

  6) Test the telepresence installation by running below command.</br>
    <b>telepresence --version</b></br>
 <i> expected output:</br>
 root@linuxkit-00155d2e4146:/# telepresence --version</br>
 0.92</br></i>

  7) Now do the kubernetes deployment of the application of your choice (in this case the springboot applications available in repo).</br>
  <b>kubectl run service-a --image=saranraj76/service-a:1.0 --port=8080</br>
  kubectl run service-b --image=saranraj76/service-b:1.0 --port=8080</br></b>
  
  8) Now you can modify the micro service in the local environment (host machine) & do the docker build</br>
  <b> cd service-a</b>
  <b> mvn clean install docker:build</b></br>
  <i>This step is your usual build of any application. The build has to be performed in the windows 10 machine. If you want to build it from the telepresence container, change dircetory to the "/host" you should see your windows 10 shared drive. you can cd to your project directory and build. However the binaries required to build your project has to be installed seperately.</i></br>
  
  9) Now run the container with the telepresence command in the telepresence container.</br>
  <b> telepresence --method container --swap-deployment service-a --docker-run service-a</b></br>
  
This should start the docker container within the telepresence container and injected to the kubernetes instance. If you kill your telepresence process your pod will be restored automatically.

Thank You!!!
  
  
     
  


  
  
  
  
  
  
  
  
  
