#!/bin/bash
export JAVA_HOME=$OPENSHIFT_DATA_DIR/jdk1.8.0_65
export PATH=$JAVA_HOME/bin:$PATH

$OPENSHIFT_DATA_DIR/apache-maven-3.3.3/bin/mvn -f $OPENSHIFT_REPO_DIR/pom.xml clean package -s $OPENSHIFT_REPO_DIR/.openshift/settings.xml