REM -- подготовка информации о тестах проекта для sonarcloud.io
mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install -Dmaven.test.failure.ignore=false
