REM -- ���������� ���������� � ������ ������� ��� sonarcloud.io
mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install -Dmaven.test.failure.ignore=false

REM -- ���������� ���������� � ������� ��� sonarcloud.io
mvn sonar:sonar -Dsonar.organization=glukoprav-github -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=a69bbf869265db5af2c91c798b37b16bc0ba0920
