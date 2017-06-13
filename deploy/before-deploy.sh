#!/usr/bin/env bash
if [ "$TRAVIS_BRANCH" = 'master' ] && [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
    openssl aes-256-cbc -K $encrypted_67d30b645c9a_key -iv $encrypted_67d30b645c9a_iv -in codesigning.asc.enc -out codesigning.asc -d
    gpg --fast-import deploy/signingkey.asc
	mvn deploy --settings deploy/settings.xml -DperformRelease=true -DskipTests=true;
fi
