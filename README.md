# telepresence-practice
To practice Telepresence with windows 10

The new docker for windows is exciting to work with, since it has built in kubernetes support. However, among the various tools for kubernetes, telepresence has gained alot of popularity since its easy to inject the local process into the running kube environment.

However, the telepresence does not work well with windows, The official Telepresence document recommends to use the Ubuntu (WSL). But i am going to try running the telepresence itself with in the docker as the ubuntu container so that we will utilize the full capabilty of the telepresence in windows.

The docker version i am using is 

Client:
 Version:           18.06.0-ce
 API version:       1.38
 Go version:        go1.10.3
 Git commit:        0ffa825
 Built:             Wed Jul 18 19:05:28 2018
 OS/Arch:           windows/amd64
 Experimental:      false

Server:
 Engine:
  Version:          18.06.0-ce
  API version:      1.38 (minimum version 1.12)
  Go version:       go1.10.3
  Git commit:       0ffa825
  Built:            Wed Jul 18 19:13:46 2018
  OS/Arch:          linux/amd64
  Experimental:     true
 Kubernetes:
  Version:          v1.10.3
  StackAPI:         v1beta2
  
I have enabled the kubernetes option, shared the drive & exposing the running deamon in the localhost:2375 port.


Lets get started!!!
  
