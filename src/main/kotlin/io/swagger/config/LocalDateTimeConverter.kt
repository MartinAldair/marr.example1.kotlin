package io.swagger.config

import org.springframework.core.convert.converter.Converter;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

class LocalDateTimeConverter : Converter<String, LocalDateTime> {

    private var formatter: DateTimeFormatter? = null

    fun LocalDateTimeConverter(dateFormat: String?) {
        formatter = DateTimeFormatter.ofPattern(dateFormat)
    }

    override fun convert(source: String): LocalDateTime? {
                return if (source == null || source.isEmpty()) {
            null
        } else LocalDateTime.parse(source, formatter)
    }
}