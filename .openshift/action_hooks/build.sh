#!/bin/bash
export JAVA_HOME=$OPENSHIFT_DATA_DIR/jdk1.8.0_65
export PATH=$JAVA_HOME/bin:$PATH

cd $OPENSHIFT_DATA_DIR

if [ ! -d apache-maven-3.3.3 ]; then
  wget http://www.eu.apache.org/dist/maven/maven-3/3.3.3/binaries/apache-maven-3.3.3-bin.tar.gz
  tar -zxf apache-maven-3.3.3-bin.tar.gz
fi

$OPENSHIFT_DATA_DIR/apache-maven-3.3.3/bin/mvn -f $OPENSHIFT_REPO_DIR/pom.xml clean package -s $OPENSHIFT_REPO_DIR/.openshift/settings.xml