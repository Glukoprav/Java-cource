REM -- ���������� ���������� � ������ ������� ��� sonarcloud.io
mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install -Dmaven.test.failure.ignore=false
