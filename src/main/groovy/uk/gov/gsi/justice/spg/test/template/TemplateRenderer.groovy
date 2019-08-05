package uk.gov.gsi.justice.spg.test.template

import groovy.text.Template
import groovy.text.markup.MarkupTemplateEngine
import groovy.text.markup.TemplateConfiguration
import groovy.transform.CompileStatic
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@CompileStatic
class TemplateRenderer {
    private final static Logger LOGGER = LoggerFactory.getLogger(TemplateRenderer)
    private final MarkupTemplateEngine engine

    TemplateRenderer() {
        final TemplateConfiguration config = new TemplateConfiguration()
        config.declarationEncoding = 'UTF-8'
        config.useDoubleQuotes = true
        config.autoIndent = true
        engine = new MarkupTemplateEngine(config)
    }

    String render(final URL resource, final Map<String, Object> data) {
        final Template template = engine.createTemplate(resource)

        final writable = template.make(data)

        final StringWriter result = new StringWriter()
        writable.writeTo(result)

        LOGGER.debug("Rendered content::: {}{}", System.lineSeparator(),result)

        return removeWindowsReturnCharacter(result.toString())
    }

    @SuppressWarnings("GrMethodMayBeStatic")
    private String removeWindowsReturnCharacter(String message) {
        return message.replaceAll("\\r\\n", "\n")
    }
}
