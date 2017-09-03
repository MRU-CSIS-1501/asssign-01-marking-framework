mkdir ~\.sonarlint\conf
cp global.json ~\.sonarlint\conf

rm -rf .sonarlint
rm -rf bin
mkdir bin

javac -d bin -cp "bin:." solution/*.java
javac -d bin -cp "jars/*:bin:." step_definitions/*.java

java -cp "jars/*:bin:" cucumber.api.cli.Main -p html:feature_results --snippets camelcase -g step_definitions features

.\sonarlint\bin\sonarlint -Dsonar.java.source=1.8 -Dsonar.java.libraries="jars/*" --src "**/solution/**" --charset "ISO-8859-1" -u