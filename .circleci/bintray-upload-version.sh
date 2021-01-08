#! /bin/bash
set -e

# Parameters:
# 1.) VERSION

VERSION=$1

cp go-maven-poller-plugin.jar go-maven-poller-plugin-${VERSION}.jar
md5sum -b go-maven-poller-plugin-${VERSION}.jar > go-maven-poller-plugin-${VERSION}.jar.md5
sha1sum -b go-maven-poller-plugin-${VERSION}.jar > go-maven-poller-plugin-${VERSION}.jar.sha
for FILE in go-maven-poller-plugin-${VERSION}.jar go-maven-poller-plugin-${VERSION}.jar.md5 go-maven-poller-plugin-${VERSION}.jar.sha; do
    curl -T ${FILE} -u${JFROG_USER}:${JFROG_APIKEY} https://api.bintray.com/content/sfuhrm/go-maven-poller/plugin/${VERSION}/${FILE}
done    
curl -X POST -u${JFROG_USER}:${JFROG_APIKEY} https://api.bintray.com/content/sfuhrm/go-maven-poller/plugin/${VERSION}/publish
