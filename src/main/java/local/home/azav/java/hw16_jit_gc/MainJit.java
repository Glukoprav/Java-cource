package local.home.azav.java.hw16_jit_gc;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainJit {
    private static final Logger LOG = Logger.getLogger(MainJit.class.getName());

    public static void main(String[] args) {
        // наполняем
        Map<Integer, String> mapJit = new HashMap<>();
        for (int i = 0; i < 100000  ; i++) {
            mapJit.put(i,"value"+i);
        }
        // задача для примера
        int sum = 0;
        for (String val : mapJit.values()) {
            sum +=Integer.valueOf(val.substring(5));
        }
        LOG.log(Level.INFO,"Результат > {0}",sum);
    }
}

//  Результаты запусков:

//"C:\Program Files\Java\jdk1.8.0_152\bin\java" -XX:+PrintCompilation "-javaagent:D:\Program\IntelliJ IDEA Community Edition 2018.1\lib\idea_rt.jar=4727:D:\Program\IntelliJ IDEA Community Edition 2018.1\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_152\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\ext\access-bridge.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\rt.jar;C:\Documents and Settings\andreyz\IdeaProjects\firstproject\target\classes;C:\Documents and Settings\andreyz\.m2\repository\org\springframework\spring-context-support\4.2.2.RELEASE\spring-context-support-4.2.2.RELEASE.jar;C:\Documents and Settings\andreyz\.m2\repository\org\springframework\spring-beans\4.2.2.RELEASE\spring-beans-4.2.2.RELEASE.jar;C:\Documents and Settings\andreyz\.m2\repository\org\springframework\spring-context\4.2.2.RELEASE\spring-context-4.2.2.RELEASE.jar;C:\Documents and Settings\andreyz\.m2\repository\org\springframework\spring-aop\4.2.2.RELEASE\spring-aop-4.2.2.RELEASE.jar;C:\Documents and Settings\andreyz\.m2\repository\aopalliance\aopalliance\1.0\aopalliance-1.0.jar;C:\Documents and Settings\andreyz\.m2\repository\org\springframework\spring-expression\4.2.2.RELEASE\spring-expression-4.2.2.RELEASE.jar;C:\Documents and Settings\andreyz\.m2\repository\org\springframework\spring-core\4.2.2.RELEASE\spring-core-4.2.2.RELEASE.jar;C:\Documents and Settings\andreyz\.m2\repository\commons-logging\commons-logging\1.2\commons-logging-1.2.jar;C:\Documents and Settings\andreyz\.m2\repository\com\sun\mail\javax.mail\1.5.4\javax.mail-1.5.4.jar;C:\Documents and Settings\andreyz\.m2\repository\javax\activation\activation\1.1\activation-1.1.jar" local.home.azav.java.hw16_jit_gc.MainJit
//        698    1             java.lang.String::equals (81 bytes)
//        699    2             java.lang.String::hashCode (55 bytes)
//        700    3             java.lang.String::startsWith (72 bytes)
//        701    4             java.lang.String::indexOf (70 bytes)
//        702    5             java.lang.String::replace (127 bytes)
//        703    6             sun.nio.cs.UTF_8$Encoder::encode (359 bytes)
//        704    7             java.lang.String::indexOf (166 bytes)
//        704    8  s          java.lang.StringBuffer::append (13 bytes)
//        705    9             java.lang.String::charAt (29 bytes)
//        705   10             java.io.WinNTFileSystem::isSlash (18 bytes)
//        708   11             java.lang.String::lastIndexOf (52 bytes)
//        709   12             java.lang.Object::<init> (1 bytes)
//        709   15     n       java.lang.System::arraycopy (native)   (static)
//        709   13             java.lang.String::<init> (82 bytes)
//        709   14             java.lang.AbstractStringBuilder::ensureCapacityInternal (27 bytes)
//        710   16             java.util.HashMap::resize (359 bytes)
//        711   17             java.lang.String::length (6 bytes)
//        711   18             java.lang.Math::min (11 bytes)
//        711   19             java.lang.String::getChars (62 bytes)
//        712   20             java.lang.Integer::stringSize (21 bytes)
//        712   21             java.lang.Integer::getChars (131 bytes)
//        712   22             java.util.HashMap::hash (20 bytes)
//        712   23             java.lang.AbstractStringBuilder::append (50 bytes)
//        713   24             java.util.Arrays::copyOfRange (63 bytes)
//        715   25             java.lang.StringBuilder::append (8 bytes)
//        716   26             java.util.HashMap::putVal (300 bytes)
//        717   27             java.util.HashMap::put (13 bytes)
//        717   28             java.util.HashMap$Node::<init> (26 bytes)
//        717   29             java.lang.AbstractStringBuilder::<init> (12 bytes)
//        717   30             java.util.HashMap::newNode (13 bytes)
//        717   31             java.util.HashMap::afterNodeInsertion (1 bytes)
//        718   32             java.lang.StringBuilder::toString (17 bytes)
//        718   33             java.lang.StringBuilder::<init> (7 bytes)
//        718   34             java.lang.Number::<init> (5 bytes)
//        718   35             java.lang.Integer::<init> (10 bytes)
//        718   36             java.lang.Integer::valueOf (32 bytes)
//        718   37             java.lang.Integer::hashCode (8 bytes)
//        718   38             java.lang.Integer::hashCode (2 bytes)
//        719   39             java.lang.StringBuilder::append (8 bytes)
//        719   40             java.lang.AbstractStringBuilder::append (62 bytes)
//        726   41 %           local.home.azav.java.hw16_jit_gc.MainJit::main @ 10 (53 bytes)
//        797   42             java.io.WinNTFileSystem::normalize (143 bytes)
//        798   43             java.util.Properties$LineReader::readLine (468 bytes)
//        800   44             java.util.Properties::loadConvert (505 bytes)


//"C:\Program Files\Java\jdk1.8.0_152\bin\java" -XX:+PrintCompilation -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining "-javaagent:D:\Program\IntelliJ IDEA Community Edition 2018.1\lib\idea_rt.jar=4748:D:\Program\IntelliJ IDEA Community Edition 2018.1\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_152\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\ext\access-bridge.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_152\jre\lib\rt.jar;C:\Documents and Settings\andreyz\IdeaProjects\firstproject\target\classes;C:\Documents and Settings\andreyz\.m2\repository\org\springframework\spring-context-support\4.2.2.RELEASE\spring-context-support-4.2.2.RELEASE.jar;C:\Documents and Settings\andreyz\.m2\repository\org\springframework\spring-beans\4.2.2.RELEASE\spring-beans-4.2.2.RELEASE.jar;C:\Documents and Settings\andreyz\.m2\repository\org\springframework\spring-context\4.2.2.RELEASE\spring-context-4.2.2.RELEASE.jar;C:\Documents and Settings\andreyz\.m2\repository\org\springframework\spring-aop\4.2.2.RELEASE\spring-aop-4.2.2.RELEASE.jar;C:\Documents and Settings\andreyz\.m2\repository\aopalliance\aopalliance\1.0\aopalliance-1.0.jar;C:\Documents and Settings\andreyz\.m2\repository\org\springframework\spring-expression\4.2.2.RELEASE\spring-expression-4.2.2.RELEASE.jar;C:\Documents and Settings\andreyz\.m2\repository\org\springframework\spring-core\4.2.2.RELEASE\spring-core-4.2.2.RELEASE.jar;C:\Documents and Settings\andreyz\.m2\repository\commons-logging\commons-logging\1.2\commons-logging-1.2.jar;C:\Documents and Settings\andreyz\.m2\repository\com\sun\mail\javax.mail\1.5.4\javax.mail-1.5.4.jar;C:\Documents and Settings\andreyz\.m2\repository\javax\activation\activation\1.1\activation-1.1.jar" local.home.azav.java.hw16_jit_gc.MainJit
//        191    1             java.lang.String::equals (81 bytes)
//        192    2             java.lang.String::hashCode (55 bytes)
//        194    3             java.lang.String::startsWith (72 bytes)
//        195    4             java.lang.String::indexOf (70 bytes)
//@ 66   java.lang.String::indexOfSupplementary (71 bytes)   callee is too large
//        196    5             java.lang.String::replace (127 bytes)
//@ 121   java.lang.String::<init> (10 bytes)
//@ 1   java.lang.Object::<init> (1 bytes)
//        200    6             sun.nio.cs.UTF_8$Encoder::encode (359 bytes)
//@ 14   java.lang.Math::min (11 bytes)
//@ 139   java.lang.Character::isSurrogate (18 bytes)
//@ 157  sun/nio/cs/Surrogate$Parser::<init> (not loaded)   not inlineable
//@ 175  sun/nio/cs/Surrogate$Parser::parse (not loaded)   not inlineable
//@ 186   java.nio.charset.CharsetEncoder::malformedInputAction (5 bytes)
//        202    7             java.lang.String::indexOf (166 bytes)
//        202    8             java.lang.String::charAt (29 bytes)
//@ 18  java/lang/StringIndexOutOfBoundsException::<init> (not loaded)   not inlineable
//        203    9  s          java.lang.StringBuffer::append (13 bytes)
//@ 7   java.lang.AbstractStringBuilder::append (29 bytes)
//@ 7   java.lang.AbstractStringBuilder::ensureCapacityInternal (27 bytes)
//@ 17   java.lang.AbstractStringBuilder::newCapacity (39 bytes)   callee is too large
//@ 20   java.util.Arrays::copyOf (19 bytes)
//@ 11   java.lang.Math::min (11 bytes)
//@ 14   java.lang.System::arraycopy (0 bytes)   intrinsic
//        203   10             java.io.WinNTFileSystem::isSlash (18 bytes)
//        205   11             java.io.WinNTFileSystem::normalize (143 bytes)
//@ 1   java.lang.String::length (6 bytes)
//@ 31   java.lang.String::charAt (29 bytes)
//@ 18  java/lang/StringIndexOutOfBoundsException::<init> (not loaded)   not inlineable
//@ 61   java.io.WinNTFileSystem::normalize (231 bytes)   callee is too large
//@ 90   java.io.WinNTFileSystem::normalize (231 bytes)   callee is too large
//@ 111   java.io.WinNTFileSystem::normalize (231 bytes)   callee is too large
//@ 137   java.io.WinNTFileSystem::normalize (231 bytes)   callee is too large
//        206   12             java.lang.String::lastIndexOf (52 bytes)
//@ 16   java.lang.Math::min (11 bytes)
//@ 48   java.lang.String::lastIndexOfSupplementary (70 bytes)   callee is too large
//        206   13             java.lang.Object::<init> (1 bytes)
//        207   14             java.lang.String::<init> (82 bytes)
//@ 1   java.lang.Object::<init> (1 bytes)
//@ 13  java/lang/StringIndexOutOfBoundsException::<init> (not loaded)   not inlineable
//        207   16     n       java.lang.System::arraycopy (native)   (static)
//@ 30  java/lang/StringIndexOutOfBoundsException::<init> (not loaded)   not inlineable
//@ 65  java/lang/StringIndexOutOfBoundsException::<init> (not loaded)   not inlineable
//@ 75   java.util.Arrays::copyOfRange (63 bytes)   callee is too large
//        208   15             java.lang.AbstractStringBuilder::ensureCapacityInternal (27 bytes)
//@ 17   java.lang.AbstractStringBuilder::newCapacity (39 bytes)   callee is too large
//@ 20   java.util.Arrays::copyOf (19 bytes)
//@ 11   java.lang.Math::min (11 bytes)
//@ 14   java.lang.System::arraycopy (0 bytes)   intrinsic