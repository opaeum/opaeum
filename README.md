OPAEUM: Object and Process Applications through Executable Unified Models


This project aimed to establish a model development and execution environment based primarily on UML that would 
allow for the modeling of complex, end-to-end applications, all the way from user interfaces, to business processes
and the database design. The modeling environment is based on the Eclipse Papyrus project, and the execution environment
leverages Hibernate, CDI and the Eclipse Remote Application Platform.


The previous version of the runtime (branch 1.0.0.20111125) targeted jBPM 5 for the generation of processes representing
statemachines and activities, but we experienced challenges aligning the execution semantics of UML Activities with jBPM. In the current version, UML activities are generated directly to Java code, but eventually this version was abandoned 
because ultimately UML is not ideal  to model human behavior. UML is simply too abstract for business users, the complexity of the Eclipse based modeling platform also presented some serious challenges to our business users.


We have decided to start from scratch in a new Github project, [Pavanecce](https://github.com/ifu-lobuntu/pavanecce/). 
Pavanecce uses more appropriate modeling languages such as VDML and CMMN, and integrates much more tightly into jBPM. We have also decided to focus more on a 100% web based development offering the leverages the Uberfire platform rather than Eclipse.
