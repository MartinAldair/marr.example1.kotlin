package io.swagger.config

import org.springframework.core.convert.converter.Converter;
import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

class LocalDateConverter:Converter<String, LocalDate> {

    private var formatter: DateTimeFormatter? = null

    fun LocalDateConverter(dateFormat: String?) {
        formatter = DateTimeFormatter.ofPattern(dateFormat)
    }

    override fun convert(source: String): LocalDate? {
        return if (source == null || source.isEmpty()) {
            null
        } else LocalDate.parse(source, formatter)
    }
}