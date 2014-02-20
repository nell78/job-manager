import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

import static ch.qos.logback.classic.Level.*

// Logback의 그루비 설정파일은 class로 컴파일하지 않고 groovy 파일을 그대로 읽으므로
// classes 폴더에 소스코드 그대로 위치해야 한다.

appender("STDOUT", ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
	pattern = "%d{HH:mm:ss.SSS} [%.5thread] %.-1level %logger{36} - %msg%n%xEx"
  }
}

logger("org.hibernate", INFO)
logger("org.hibernate.SQL", DEBUG)
logger("org.hibernate.tool.hbm2ddl", DEBUG)
logger("org.springframework", INFO)

root(DEBUG, ["STDOUT"])
